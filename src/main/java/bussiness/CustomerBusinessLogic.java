package bussiness;

import model.crud.CustomerCRUD;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class CustomerBusinessLogic {

    public long addCustomer() {
        long customerID = 0;
        try {
            long maxCustomerNumber=CustomerCRUD.maxCustomerNumber();
            CustomerCRUD.insert(maxCustomerNumber+1);
            customerID=CustomerCRUD.lastCustomerID();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerID;
    }

    public long selectCustomer(String customerNumber)
    {
        long customerId=0;
        try
        {
            customerId=CustomerCRUD.select(customerNumber);
        }catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return customerId;
    }
}
