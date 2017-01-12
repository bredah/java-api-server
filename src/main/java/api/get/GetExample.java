package api.get;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("get") // The path where the resource is exposed
public class GetExample {

	@Context
	UriInfo uri;

	/**
	 * Recovery phrase "Hello World!"
	 * 
	 * <pre>
	 * {@code
	 * http://localhost:PORT/get/hello
	 * }
	 * </pre>
	 * 
	 * @return Hello World!
	 */
	@GET // Method handling HTTP GET requests
	@Path("hello") // The path where the resource is exposed
	@Produces(MediaType.TEXT_PLAIN) // Specify the MIME media type a resource can produce and send back to the client
	public String helloWorld() {
		return "Hello world!";
	}

	/**
	 * Recovery phrase with the name informed
	 * 
	 * <pre>
	 * {@code
	 * http://localhost:PORT/get/param?name=NAME
	 * }
	 * </pre>
	 * 
	 * @see api.server.Main
	 * @param name
	 *            Any name
	 * @return Phrase with name
	 */
	@GET
	@Path("param")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloName(@QueryParam("name") String name) {
		return "Hi " + name;
	}

	/**
	 * Return a text with MAPPED response entity
	 * 
	 * <pre>
	 * {@code
	 * http://localhost:PORT/get/response
	 * http://localhost:PORT/get/response?id=2
	 * http://localhost:PORT/get/response?id=3
	 * http://localhost:PORT/get/response?id=4
	 * http://localhost:PORT/get/response?id=5
	 * }
	 * </pre>
	 * 
	 * @return Response with status and message
	 * @throws URISyntaxException
	 */
	@GET
	@Path("response")
	@Produces(MediaType.TEXT_PLAIN)
	public Response response(@QueryParam("id") int id) throws URISyntaxException {
		switch (id) {
		case 2: // Response code: 200
			return Response.ok("STATUS 200").build();
		case 3: // Response code: 302
			return Response.temporaryRedirect(new URI(uri.getBaseUri() + "/get/response")).status(302).build();
		// return Response.temporaryRedirect(new URI("http://127.0.0.1:" + Main.SERVER_PORT + "/get/response"))
		// .status(302).build();
		case 4: // Response code: 404
			return Response.status(404).entity("STATUS 404").build();
		case 5: // Response code: 500
			return Response.serverError().build();
		default: // Default response without ID
			String response = "Send ID with:\n\t2- Status Code 200\n\t3- Status Code 400\n\t4- Status Code 400\n\t5- Status Code 500";
			return Response.ok(response).build();
		}

	}

}
