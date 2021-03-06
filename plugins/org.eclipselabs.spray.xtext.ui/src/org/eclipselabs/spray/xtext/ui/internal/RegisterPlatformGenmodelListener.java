/** ****************************************************************************
 * Copyright (c)  The Spray Project.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Spray Dev Team - initial API and implementation
 **************************************************************************** */
package org.eclipselabs.spray.xtext.ui.internal;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.EcoreUtil2;

import javax.inject.Inject;

class RegisterPlatformGenmodelListener implements IResourceChangeListener {
    private static final Log      LOG          = LogFactory.getLog(RegisterPlatformGenmodelListener.class);

    @Inject
    MweGenModelHelperExt          genModelHelper;
    private IResourceDeltaVisitor deltaVisitor = new IResourceDeltaVisitor() {
                                                   @Override
                                                   public boolean visit(IResourceDelta delta) throws CoreException {
                                                       if (delta.getResource() != null && delta.getResource().isAccessible()) {
                                                           IResource resource = delta.getResource();
                                                           handleResource(resource, delta.getKind());
                                                           return true;
                                                       } else {
                                                           return false;
                                                       }
                                                   }
                                               };

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
                if (event.getDelta() != null) {
                    if (event.getDelta().getResource() instanceof IWorkspaceRoot) {
                        for (IResourceDelta delta : event.getDelta().getAffectedChildren()) {
                            delta.accept(deltaVisitor);
                        }
                    } else {
                        event.getDelta().accept(deltaVisitor);
                    }
                } else if (event.getResource() != null) {
                }
            } else if (event.getType() == IResourceChangeEvent.PRE_CLOSE || event.getType() == IResourceChangeEvent.PRE_DELETE) {
                event.getResource().accept(new IResourceVisitor() {

                    @Override
                    public boolean visit(IResource resource) throws CoreException {
                        handleResource(resource, IResourceDelta.REMOVED);
                        return true;
                    }
                });
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }

    }

    public void initWorkspace() throws CoreException {
        ResourcesPlugin.getWorkspace().getRoot().accept(new IResourceVisitor() {
            @Override
            public boolean visit(IResource resource) throws CoreException {
                handleResource(resource, IResourceDelta.ADDED);
                return true;
            }
        });
    }

    protected void handleResource(IResource resource, int deltaKind) {
        IFile file = (IFile) resource.getAdapter(IFile.class);
        if (file != null && "genmodel".equals(file.getFileExtension()) && !file.getProjectRelativePath().toPortableString().contains("target")) {
            if (deltaKind == IResourceDelta.ADDED) {
                registerGenmodel(file);
            } else if (deltaKind == IResourceDelta.REMOVED) {
                unregisterGenmodel(file);
            }
        }
        if (file != null && "ecore".equals(file.getFileExtension()) && !file.getProjectRelativePath().toPortableString().contains("target")) {
            if (deltaKind == IResourceDelta.ADDED) {
                registerEPackage(file);
            } else if (deltaKind == IResourceDelta.REMOVED) {
                unregisterEPackage(file);
            }
        }
    }

    protected void registerGenmodel(IFile file) {
        ResourceSet rs = new ResourceSetImpl();
        Resource emfResource = rs.createResource(URI.createPlatformResourceURI((file.getFullPath().toPortableString()), true));
        try {
            emfResource.load(Collections.EMPTY_MAP);
            genModelHelper.registerGenModel(emfResource.getResourceSet(), emfResource.getURI());
            emfResource.unload();
        } catch (Exception e) {
            SprayActivatorExt.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipselabs.spray.xtext.ui", "Error loading genmodel " + emfResource.getURI().toString(), e));
        }
    }

    protected void unregisterGenmodel(IFile file) {
        ResourceSet rs = new ResourceSetImpl();
        Resource emfResource = rs.createResource(URI.createPlatformResourceURI((file.getFullPath().toPortableString()), true));
        try {
            emfResource.load(Collections.EMPTY_MAP);
            genModelHelper.unregisterGenModel(emfResource.getResourceSet(), emfResource.getURI());
            emfResource.unload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unregisterEPackage(IFile file) {
        ResourceSet rs = new ResourceSetImpl();
        Resource emfResource = rs.createResource(URI.createPlatformResourceURI((file.getFullPath().toPortableString()), true));
        try {
            emfResource.load(Collections.EMPTY_MAP);
            EPackage.Registry registry = EPackage.Registry.INSTANCE;
            for (EPackage pck : EcoreUtil2.eAllOfType(emfResource.getContents().get(0), EPackage.class)) {
                if (registry.remove(pck.getNsURI()) != null) {
                    LOG.info("Deregistered EPackage " + pck.getNsURI());
                }
            }
            emfResource.unload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerEPackage(IFile file) {
        ResourceSet rs = new ResourceSetImpl();
        Resource emfResource = rs.createResource(URI.createPlatformResourceURI((file.getFullPath().toPortableString()), true));
        try {
            emfResource.load(Collections.EMPTY_MAP);
            EPackage.Registry registry = EPackage.Registry.INSTANCE;
            for (EPackage pck : EcoreUtil2.eAllOfType(emfResource.getContents().get(0), EPackage.class)) {
                if (!registry.containsKey(pck.getNsURI())) {
                    registry.put(pck.getNsURI(), pck);
                    LOG.info("Registered EPackage " + pck.getNsURI());
                }
            }
            emfResource.unload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
