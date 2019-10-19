package servlet;

import dao.DAOUser;
import dao.DAOUserImpl;
import model.User;
import template.TemplateProcessor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBServlet extends HttpServlet {
    private final DAOUser db = new DAOUserImpl<>();
    private static final String TIMER_PAGE_TEMPLATE = "db.html";

    private final TemplateProcessor templateProcessor;

    private List list=new ArrayList();




    public DBServlet()  {
        this.templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        resp.setContentType("text/html;charset=utf-8");


        if (list.size()==0){
                resp.getWriter().println(templateProcessor.getPage(TIMER_PAGE_TEMPLATE,null));
        }else {
            Map <String, Object> map=new HashMap();
            map.put("listUser",list);
            resp.getWriter().println(templateProcessor.getPage(TIMER_PAGE_TEMPLATE,map));
        }


        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String form = req.getParameter("form");

        if (form.equals("view")){
            list=db.readAll();
            doGet( req, resp);
        }
        if (form.equals("add")){
        User tempUser =new User();

        tempUser.setName(req.getParameter("name"));
        tempUser.setAge(Integer.valueOf(req.getParameter("age")));
        tempUser.setPhone(req.getParameter("phone"));
        tempUser.setAddress(req.getParameter("address"));

        db.save(tempUser);

        doGet( req, resp);
        }
    }


}
