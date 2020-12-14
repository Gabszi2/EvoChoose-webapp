package logic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
        (
                name = "selectkitchenservlet",
                urlPatterns="/SelectKitchen"
        )
public class KitchenServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selctedKitchen=req.getParameter("Type");

        String kitchen=Kitchens.valueOf(selctedKitchen.toUpperCase()).name();
        req.setAttribute("selectedKitchen",kitchen);

        kitchen=kitchen.toLowerCase();
        ServletContext application = getServletContext();
        String filepath = application.getRealPath(kitchen+"_questions.json");

        req.setAttribute("path",filepath);

        RequestDispatcher view=req.getRequestDispatcher("questions.jsp");
        view.forward(req,resp);
    }

}
