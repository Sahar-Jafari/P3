package presentation;

import bussiness.RealCustomerBusinessLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dotin school 6 on 3/16/2015.
 */
public class RealCustomerRemoverServlet extends HttpServlet {
    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        try {
            String id = request.getParameter("data");
            System.out.println(id);
            if (!id.isEmpty() && !id.equals(null)) {
                RealCustomerBusinessLogic realCustomerBusinessLogic=new RealCustomerBusinessLogic();
                realCustomerBusinessLogic.deleteRealCustomer(id);
                response.getWriter().write("success");
            } else response.getWriter().write("error");
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
