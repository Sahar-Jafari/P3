package presentation;

import bussiness.RealCustomerBusinessLogic;
import model.entity.RealCustomer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class RealCustomerRegistrarServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String data = request.getParameter("data");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonObject = (JSONObject) obj;

            RealCustomerBusinessLogic realCustomerBusinessLogic = new RealCustomerBusinessLogic();
            RealCustomer realCustomer = new RealCustomer();
            realCustomer.setName((String) jsonObject.get("name"));
            realCustomer.setFamily((String) jsonObject.get("family"));
            realCustomer.setNameOfFather((String) jsonObject.get("fatherName"));
            realCustomer.setDateOfBirth((String) jsonObject.get("date"));
            realCustomer.setIdCardNumber((String) jsonObject.get("id"));
            if (realCustomerBusinessLogic.hasId(realCustomer.getIdCardNumber()))
            {
                response.getWriter().write("errorId");
            }
            else {
                realCustomerBusinessLogic.addRealCustomer(realCustomer);
                response.getWriter().write("success");
            }
        } catch (ParseException e) {
            response.getWriter().print("error");
        }
    }


}
