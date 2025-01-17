import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting server on port 9876");

        Server server = new Server(9876);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        logger.info("Servlet context path set to /");

        ResourceConfig config = new ResourceConfig();
        config.packages("Graphs", "Commands");
        config.property("jersey.config.server.provider.packages", "Graphs, Commands");
        logger.info("ResourceConfig configured with packages and properties");

        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        jerseyServlet.setInitOrder(0);
        context.addServlet(jerseyServlet, "/api/*");
        logger.info("Jersey servlet added to context with path /api/*");

        server.setHandler(context);
        logger.info("Context handler set on server");

        logger.info("Server configured, starting...");
        server.start();
        logger.info("Server started successfully");
        server.join();
    }
}