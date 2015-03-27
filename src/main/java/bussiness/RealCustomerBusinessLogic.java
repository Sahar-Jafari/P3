package bussiness;

import model.crud.CustomerCRUD;
import model.crud.RealCustomerCRUD;
import model.entity.RealCustomer;

import java.util.HashMap;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class RealCustomerBusinessLogic {
    public void addRealCustomer(RealCustomer realCustomer) {
        try {
            CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();
            long addedId = customerBusinessLogic.addCustomer();
            realCustomer.setId(addedId);
            RealCustomerCRUD.insert(realCustomer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public boolean hasId(String idCardNumber) {
        try {
            boolean exist = RealCustomerCRUD.isIn(idCardNumber);
            return exist;
        } catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
    }

    public HashMap<Long, RealCustomer> selectRealCustomer(RealCustomer realCustomer, long customerNumber) {
        HashMap<Long, RealCustomer> realCustomers = new HashMap<Long, RealCustomer>();
        try {
            realCustomers = RealCustomerCRUD.select(realCustomer, customerNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return realCustomers;
    }

    public void deleteRealCustomer(String customerNumber) {
        try {
             long customerId = CustomerCRUD.select(customerNumber);
            RealCustomerCRUD.delete(customerId);
            CustomerCRUD.delete(customerId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateRealCustomer(RealCustomer realCustomer) {
        try {
            RealCustomerCRUD.update(realCustomer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
