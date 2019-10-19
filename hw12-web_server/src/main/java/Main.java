
import auth.AuthorizationFilter;
import org.eclipse.jetty.servlet.FilterHolder;
import servlet.DBServlet;
import servlet.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Main {
    private final static int PORT = 8080;

    public static void main(String[] args) throws Exception {
        new Main().start();
    }

    private void start() throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new LoginServlet("1","1")), "/login");
        context.addServlet(new ServletHolder(new DBServlet()),"/db");
        context.addFilter(new FilterHolder(new AuthorizationFilter()), "/db", null);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(context));

        server.start();
        server.join();
    }
}
