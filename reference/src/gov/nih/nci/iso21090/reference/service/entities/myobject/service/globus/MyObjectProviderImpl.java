package gov.nih.nci.iso21090.reference.service.entities.myobject.service.globus;

import gov.nih.nci.iso21090.reference.service.entities.myobject.service.MyObjectImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the ISO21090ReferenceServiceImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correclty mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class MyObjectProviderImpl{
	
	MyObjectImpl impl;
	
	public MyObjectProviderImpl() throws RemoteException {
		impl = new MyObjectImpl();
	}
	

    public gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.CreateResponse create(gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.CreateRequest params) throws RemoteException {
    gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.CreateResponse boxedResult = new gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.CreateResponse();
    boxedResult.setId(impl.create(params.getMyObject().getMyObject()));
    return boxedResult;
  }

    public gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.UpdateResponse update(gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.UpdateRequest params) throws RemoteException {
    gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.UpdateResponse boxedResult = new gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.UpdateResponse();
    impl.update(params.getMyObject().getMyObject());
    return boxedResult;
  }

    public gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.DeleteResponse delete(gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.DeleteRequest params) throws RemoteException {
    gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.DeleteResponse boxedResult = new gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.DeleteResponse();
    impl.delete(params.getMyObject().getMyObject());
    return boxedResult;
  }

    public gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.GetByIdResponse getById(gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.GetByIdRequest params) throws RemoteException {
    gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.GetByIdResponse boxedResult = new gov.nih.nci.iso21090.reference.service.entities.myobject.stubs.GetByIdResponse();
    boxedResult.setMyObject(impl.getById(params.getId().getId()));
    return boxedResult;
  }

}