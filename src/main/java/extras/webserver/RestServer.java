package extras.webserver;

import domain.IGameService;
import extras.webserver.controller.MainController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class RestServer implements IGameService {

    static RestServer instance = new RestServer();
    Thread thread = null;

    public static RestServer getInstance() {
        return instance;
    }

    @Override
    public void onExtensionInit() {
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                context.setContextPath("/");
                int port = JettyProperties.getInstance().getInt("jetty.port");
                Server jettyServer = new Server(port);
                jettyServer.setHandler(context);

                ServletHolder jerseyServlet = context.addServlet(
                        org.glassfish.jersey.servlet.ServletContainer.class, "/*");
                jerseyServlet.setInitOrder(0);

                // Tells the Jersey Servlet which REST service/class to load.
                jerseyServlet.setInitParameter(
                        "jersey.config.server.provider.classnames",
                        MainController.class.getCanonicalName());

                try {
                    //jettyServer.setDumpAfterStart(false);
                    jettyServer.start();
                    jettyServer.join();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jettyServer.destroy();
                }
            }
        });
        this.thread.start();
    }

    @Override
    public void onExtensionDestroy() {
        if ( this.thread != null ) {
        }
    }
}

