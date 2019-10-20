package servlet;

import auth.AuthorizationFilter;
import dao.DAOUser;
import dao.DAOUserImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Start {

    private final static int PORT = 8080;
    private final static String DB_PAGE_TEMPLATE = "db.html";
    private final static String LOGIN_PAGE_TEMPLATE = "login.html";
    private final static String LOGIN = "1";
    private final static String PASSWORD = "1";
    private final static String STATIC = "/static";
    private final static DAOUser db = new DAOUserImpl<>();

    public void start() throws Exception {
        resources();
        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(STATIC);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new LoginServlet(LOGIN, PASSWORD, LOGIN_PAGE_TEMPLATE)), "/login");
        context.addServlet(new ServletHolder(new DBServlet(DB_PAGE_TEMPLATE, db)), "/db");
        context.addFilter(new FilterHolder(new AuthorizationFilter()), "/db", null);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler,context));

        server.start();
        server.join();
    }

    private void resources() {
        URL url = Start.class.getResource(STATIC + "/index.html"); //local path starts with '/'
        System.out.println(url);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
