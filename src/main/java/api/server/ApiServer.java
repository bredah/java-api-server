package api.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ApiServer {

	private Server server;
	private int port;

	/**
	 * Create a API server
	 * 
	 * @param port
	 *            Server port
	 */
	public ApiServer(int port) {
		this.port = port;
	}

	/**
	 * Start api server
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		// Configure API resource
		ResourceConfig config = new ResourceConfig();
		config.packages("api");
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		// Instance a new server and set the port number
		Server server = new Server(port);
		// Add context handler for all path in the new server
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");
		// Start server
		server.start();
		// server.join();
	}

	/**
	 * Stop api server
	 * 
	 */
	public void stop() {
		if (server != null) {
			server.destroy();
		}
	}

}
