package servlet;

import dao.DAOUser;
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
    private final DAOUser db;
    private final String page;
    private final TemplateProcessor templateProcessor;



    public DBServlet(String page,DAOUser db) {
        this.templateProcessor = new TemplateProcessor();
        this.db = db;
        this.page = page;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List list = new ArrayList();

        resp.setContentType("text/html;charset=utf-8");
        String form = req.getParameter("formshow");
        if (form==null) {
            list = new ArrayList();
        }else if (form.equals("view")){
            list = db.readAll();
        }

        if (list.size() == 0) {
            resp.getWriter().println(templateProcessor.getPage(page, null));
        } else {
            Map<String, Object> map = new HashMap();
            map.put("listUser", list);
            resp.getWriter().println(templateProcessor.getPage(page, map));
        }
        resp.setStatus(HttpServletResponse.SC_OK);












    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String form = req.getParameter("form");


        if (form.equals("add")) {
            User tempUser = new User(req.getParameter("name"),Integer.valueOf(req.getParameter("age")),req.getParameter("address"),req.getParameter("phone"));

            db.save(tempUser);

            doGet(req, resp);
        }
    }


}
