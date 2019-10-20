package servlet;

import template.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private String name;
    private String password;

    private final String page;

    private final TemplateProcessor templateProcessor;
    
    

    public LoginServlet(String name, String password,String page)  {
        this.name = name;
        this.password = password;
        this.page=page;
        this.templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(templateProcessor.getPage(page,null));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");

        if (name.equals(userName) && password.equals(userPassword)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30);
            response.sendRedirect(request.getContextPath() + "/db");
        } else {
            response.setStatus(403);
        }
    }

}