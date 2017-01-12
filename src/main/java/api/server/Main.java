package api.server;

public class Main {

	public static int SERVER_PORT = 2222;

	public static void main(String[] args) {
		ApiServer server = new ApiServer(SERVER_PORT);

		// ResourceConfig config = new ResourceConfig();
		// config.packages("api");
		// ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		// // Instance a new server and set the port number
		// Server server = new Server(SERVER_PORT);
		// // Add context handler for all path in the new server
		// ServletContextHandler context = new ServletContextHandler(server, "/*");
		// context.addServlet(servlet, "/*");
		try {
			server.start();
			// server.start();
			// server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.stop();
		}
	}
}
