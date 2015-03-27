package bussiness;


import model.crud.CustomerCRUD;
import model.crud.LegalCustomerCRUD;
import model.entity.LegalCustomer;

import java.util.HashMap;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class LegalCustomerBusinessLogic {
    public void addLegalCustomer(LegalCustomer legalCustomer) {
        try {
            CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();
            long addedId = customerBusinessLogic.addCustomer();
            legalCustomer.setId(addedId);
            LegalCustomerCRUD.insert(legalCustomer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public HashMap<Long,LegalCustomer> selectRealCustomer(LegalCustomer legalCustomer, long costumerNumber) {
        HashMap<Long,LegalCustomer> legalCustomers = new HashMap<Long, LegalCustomer>();
        try {
            legalCustomers=LegalCustomerCRUD.select(legalCustomer,costumerNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return legalCustomers;
    }

    public void deleteLegalCustomer(String legalIdNumber)
    {
          try {
              long customerId=LegalCustomerCRUD.selectCustomerId(legalIdNumber);
              LegalCustomerCRUD.delete(legalIdNumber);
              CustomerCRUD.delete(customerId);
          }catch (Exception ex)
          {
              ex.printStackTrace();
          }
    }

    public void updateLegalCustomer()
    {
        try{

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
