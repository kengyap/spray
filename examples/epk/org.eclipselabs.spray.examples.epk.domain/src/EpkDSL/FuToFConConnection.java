/**
 */
package EpkDSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fu To FCon Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link EpkDSL.FuToFConConnection#getStart <em>Start</em>}</li>
 *   <li>{@link EpkDSL.FuToFConConnection#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @see EpkDSL.EpkDSLPackage#getFuToFConConnection()
 * @model
 * @generated
 */
public interface FuToFConConnection extends DefaultConnection {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' reference.
	 * @see #setStart(Function)
	 * @see EpkDSL.EpkDSLPackage#getFuToFConConnection_Start()
	 * @model required="true"
	 * @generated
	 */
	Function getStart();

	/**
	 * Sets the value of the '{@link EpkDSL.FuToFConConnection#getStart <em>Start</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Function value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' reference.
	 * @see #setEnd(FConnector)
	 * @see EpkDSL.EpkDSLPackage#getFuToFConConnection_End()
	 * @model required="true"
	 * @generated
	 */
	FConnector getEnd();

	/**
	 * Sets the value of the '{@link EpkDSL.FuToFConConnection#getEnd <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(FConnector value);

} // FuToFConConnection
