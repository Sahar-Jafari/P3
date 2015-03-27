package presentation;

import com.google.gson.Gson;
import bussiness.RealCustomerBusinessLogic;
import model.entity.RealCustomer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class RealCustomerSelectorServlet extends HttpServlet {
    long customerNumber;
    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        try {
            RealCustomerBusinessLogic realCustomerBusinessLogic = new RealCustomerBusinessLogic();
            RealCustomer realCustomer = new RealCustomer();

            String data = request.getParameter("data");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonObject = (JSONObject) obj;

            realCustomer.setName((String) jsonObject.get("name"));
            realCustomer.setFamily((String) jsonObject.get("family"));
            realCustomer.setIdCardNumber((String) jsonObject.get("id"));

            if (((String) jsonObject.get("customerNumber")) != null && !((String) jsonObject.get("customerNumber")).isEmpty()) {
                customerNumber = Long.parseLong((String) jsonObject.get("customerNumber"));
            } else customerNumber = 0;
            HashMap<Long,RealCustomer> customers = realCustomerBusinessLogic.selectRealCustomer(realCustomer, customerNumber);


            //JSONArray jsonArray=new JSONArray();
            Gson gson = new Gson();
            String json = gson.toJson(customers);
            response.getWriter().print(json);
        } catch (Exception ex) {
            response.getWriter().print("error");
        }
    }
}
