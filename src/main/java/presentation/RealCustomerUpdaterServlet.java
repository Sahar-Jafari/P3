package presentation;

import bussiness.CustomerBusinessLogic;
import bussiness.RealCustomerBusinessLogic;
import model.entity.RealCustomer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class RealCustomerUpdaterServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            long customerId = 0;
            String customerNumber = null;
            RealCustomerBusinessLogic realCustomerBusinessLogic = new RealCustomerBusinessLogic();
            RealCustomer realCustomer = new RealCustomer();
            CustomerBusinessLogic customerBusinessLogic = new CustomerBusinessLogic();

            String data = request.getParameter("data");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonObject = (JSONObject) obj;

            realCustomer.setName((String) jsonObject.get("name"));
            realCustomer.setFamily((String) jsonObject.get("family"));
            realCustomer.setIdCardNumber((String) jsonObject.get("nationalCode"));
            realCustomer.setNameOfFather((String) jsonObject.get("nameOfFather"));
            realCustomer.setDateOfBirth((String) jsonObject.get("dateOfBirth"));
            customerNumber = (String) jsonObject.get("customerNumber");
            customerId = customerBusinessLogic.selectCustomer(customerNumber);
            if (customerId==0)
            {
                response.getWriter().print("error");
            }
            else
            {
                realCustomer.setId(customerId);
                realCustomerBusinessLogic.updateRealCustomer(realCustomer);
                response.getWriter().print("success");
            }
        } catch (Exception ex) {
            response.getWriter().print("error");
        }
    }
}