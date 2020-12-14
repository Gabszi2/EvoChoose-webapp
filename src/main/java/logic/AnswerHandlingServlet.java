package logic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet
        (
                name = "givenanswerservlet",
                urlPatterns="/GivenAnswer"
        )
public class AnswerHandlingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String questionPath=(String)req.getSession().getAttribute("questionpath");


        int size=(int) req.getSession().getAttribute("size");
        boolean[] answers=new boolean[size];

        for (int i=0;i<size;i++) {
            String name="Answer"+i;

             String answer = req.getParameter(name);


            switch (answer){
                case "true":
                {
                    answers[i]=true;
                    break;
                }
                case "false":
                {
                    answers[i]=false;
                    break;
                }
            }

        }

        String kitchen=(String)req.getSession().getAttribute("kitchen");
        kitchen=kitchen.toLowerCase();
        ServletContext application = getServletContext();
        String filepath = application.getRealPath(kitchen+"_answers.json");

        req.setAttribute("answers",answers);
        req.setAttribute("size",size);
        req.setAttribute("path",filepath);

        RequestDispatcher view=req.getRequestDispatcher("resoult.jsp");
        view.forward(req,resp);

    }

}
