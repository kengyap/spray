/**
 * <copyright>
 * </copyright>
 *

 */
package org.eclipselabs.spray2.xtext.spray2.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipselabs.spray2.xtext.spray2.CompartmentPropertyAssignment;
import org.eclipselabs.spray2.xtext.spray2.NodeFigureSection;
import org.eclipselabs.spray2.xtext.spray2.Spray2Package;
import org.eclipselabs.spray2.xtext.spray2.TextPropertyAssignment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Figure Section</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.spray2.xtext.spray2.impl.NodeFigureSectionImpl#getShapeRef <em>Shape Ref</em>}</li>
 *   <li>{@link org.eclipselabs.spray2.xtext.spray2.impl.NodeFigureSectionImpl#getTextProperties <em>Text Properties</em>}</li>
 *   <li>{@link org.eclipselabs.spray2.xtext.spray2.impl.NodeFigureSectionImpl#getCompartmentProperties <em>Compartment Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeFigureSectionImpl extends MinimalEObjectImpl.Container implements NodeFigureSection
{
  /**
   * The cached value of the '{@link #getShapeRef() <em>Shape Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShapeRef()
   * @generated
   * @ordered
   */
  protected JvmTypeReference shapeRef;

  /**
   * The cached value of the '{@link #getTextProperties() <em>Text Properties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTextProperties()
   * @generated
   * @ordered
   */
  protected EList<TextPropertyAssignment> textProperties;

  /**
   * The cached value of the '{@link #getCompartmentProperties() <em>Compartment Properties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompartmentProperties()
   * @generated
   * @ordered
   */
  protected EList<CompartmentPropertyAssignment> compartmentProperties;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeFigureSectionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return Spray2Package.Literals.NODE_FIGURE_SECTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getShapeRef()
  {
    return shapeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetShapeRef(JvmTypeReference newShapeRef, NotificationChain msgs)
  {
    JvmTypeReference oldShapeRef = shapeRef;
    shapeRef = newShapeRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF, oldShapeRef, newShapeRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShapeRef(JvmTypeReference newShapeRef)
  {
    if (newShapeRef != shapeRef)
    {
      NotificationChain msgs = null;
      if (shapeRef != null)
        msgs = ((InternalEObject)shapeRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF, null, msgs);
      if (newShapeRef != null)
        msgs = ((InternalEObject)newShapeRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF, null, msgs);
      msgs = basicSetShapeRef(newShapeRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF, newShapeRef, newShapeRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TextPropertyAssignment> getTextProperties()
  {
    if (textProperties == null)
    {
      textProperties = new EObjectContainmentEList<TextPropertyAssignment>(TextPropertyAssignment.class, this, Spray2Package.NODE_FIGURE_SECTION__TEXT_PROPERTIES);
    }
    return textProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<CompartmentPropertyAssignment> getCompartmentProperties()
  {
    if (compartmentProperties == null)
    {
      compartmentProperties = new EObjectContainmentEList<CompartmentPropertyAssignment>(CompartmentPropertyAssignment.class, this, Spray2Package.NODE_FIGURE_SECTION__COMPARTMENT_PROPERTIES);
    }
    return compartmentProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF:
        return basicSetShapeRef(null, msgs);
      case Spray2Package.NODE_FIGURE_SECTION__TEXT_PROPERTIES:
        return ((InternalEList<?>)getTextProperties()).basicRemove(otherEnd, msgs);
      case Spray2Package.NODE_FIGURE_SECTION__COMPARTMENT_PROPERTIES:
        return ((InternalEList<?>)getCompartmentProperties()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF:
        return getShapeRef();
      case Spray2Package.NODE_FIGURE_SECTION__TEXT_PROPERTIES:
        return getTextProperties();
      case Spray2Package.NODE_FIGURE_SECTION__COMPARTMENT_PROPERTIES:
        return getCompartmentProperties();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF:
        setShapeRef((JvmTypeReference)newValue);
        return;
      case Spray2Package.NODE_FIGURE_SECTION__TEXT_PROPERTIES:
        getTextProperties().clear();
        getTextProperties().addAll((Collection<? extends TextPropertyAssignment>)newValue);
        return;
      case Spray2Package.NODE_FIGURE_SECTION__COMPARTMENT_PROPERTIES:
        getCompartmentProperties().clear();
        getCompartmentProperties().addAll((Collection<? extends CompartmentPropertyAssignment>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF:
        setShapeRef((JvmTypeReference)null);
        return;
      case Spray2Package.NODE_FIGURE_SECTION__TEXT_PROPERTIES:
        getTextProperties().clear();
        return;
      case Spray2Package.NODE_FIGURE_SECTION__COMPARTMENT_PROPERTIES:
        getCompartmentProperties().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case Spray2Package.NODE_FIGURE_SECTION__SHAPE_REF:
        return shapeRef != null;
      case Spray2Package.NODE_FIGURE_SECTION__TEXT_PROPERTIES:
        return textProperties != null && !textProperties.isEmpty();
      case Spray2Package.NODE_FIGURE_SECTION__COMPARTMENT_PROPERTIES:
        return compartmentProperties != null && !compartmentProperties.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //NodeFigureSectionImpl
