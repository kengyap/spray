/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package BusinessDomainDsl.impl;

import BusinessDomainDsl.IBusinessDomainDslPackage;
import BusinessDomainDsl.IEnumeration;
import BusinessDomainDsl.IEnumerationLiteral;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BusinessDomainDsl.impl.EnumerationLiteralImpl#getPersistedValue <em>Persisted Value</em>}</li>
 *   <li>{@link BusinessDomainDsl.impl.EnumerationLiteralImpl#getEnumeration <em>Enumeration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationLiteralImpl extends ModelElementImpl implements IEnumerationLiteral {
    /**
     * The default value of the '{@link #getPersistedValue() <em>Persisted Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPersistedValue()
     * @generated
     * @ordered
     */
    protected static final int PERSISTED_VALUE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPersistedValue() <em>Persisted Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPersistedValue()
     * @generated
     * @ordered
     */
    protected int persistedValue = PERSISTED_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EnumerationLiteralImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IBusinessDomainDslPackage.Literals.ENUMERATION_LITERAL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPersistedValue() {
        return persistedValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPersistedValue(int newPersistedValue) {
        int oldPersistedValue = persistedValue;
        persistedValue = newPersistedValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IBusinessDomainDslPackage.ENUMERATION_LITERAL__PERSISTED_VALUE, oldPersistedValue, persistedValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IEnumeration getEnumeration() {
        if (eContainerFeatureID() != IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION) return null;
        return (IEnumeration)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEnumeration(IEnumeration newEnumeration, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newEnumeration, IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEnumeration(IEnumeration newEnumeration) {
        if (newEnumeration != eInternalContainer() || (eContainerFeatureID() != IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION && newEnumeration != null)) {
            if (EcoreUtil.isAncestor(this, newEnumeration))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newEnumeration != null)
                msgs = ((InternalEObject)newEnumeration).eInverseAdd(this, IBusinessDomainDslPackage.ENUMERATION__ENUMERATION_LITERALS, IEnumeration.class, msgs);
            msgs = basicSetEnumeration(newEnumeration, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION, newEnumeration, newEnumeration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetEnumeration((IEnumeration)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                return basicSetEnumeration(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                return eInternalContainer().eInverseRemove(this, IBusinessDomainDslPackage.ENUMERATION__ENUMERATION_LITERALS, IEnumeration.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__PERSISTED_VALUE:
                return getPersistedValue();
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                return getEnumeration();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__PERSISTED_VALUE:
                setPersistedValue((Integer)newValue);
                return;
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                setEnumeration((IEnumeration)newValue);
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
    public void eUnset(int featureID) {
        switch (featureID) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__PERSISTED_VALUE:
                setPersistedValue(PERSISTED_VALUE_EDEFAULT);
                return;
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                setEnumeration((IEnumeration)null);
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
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__PERSISTED_VALUE:
                return persistedValue != PERSISTED_VALUE_EDEFAULT;
            case IBusinessDomainDslPackage.ENUMERATION_LITERAL__ENUMERATION:
                return getEnumeration() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (persistedValue: ");
        result.append(persistedValue);
        result.append(')');
        return result.toString();
    }

} //EnumerationLiteralImpl
