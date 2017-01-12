package api.get;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import api.server.ApiServer;
import bredah.httpclient.HttpRequest;
import bredah.httpclient.Method;

public class TestGetExample {

	private String urlBase;
	private static ApiServer server;
	private static int port;
	private HttpRequest request;

	@BeforeClass
	public static void beforeClass() throws Exception {
		// Set port number
		port = 5555;
		// Initialize API server
		server = new ApiServer(port);
		server.start();
	}

	@AfterClass
	public static void afterClass() {
		// Turn off API server
		server.stop();
	}

	@Before
	public void before() throws Exception {
		// Set URL base
		urlBase = "http://127.0.0.1:" + port;
		// Clear HTTP Request
		request = null;
	}

	@Test
	public void testHello() throws ClientProtocolException, IOException, URISyntaxException {
		request = new HttpRequest(Method.GET, urlBase + "/get/hello");
		request.execute();
		// Check response status code
		Assert.assertEquals(200, request.getResponseStatusCode());
		// Check response body
		Assert.assertEquals(new GetExample().helloWorld(), IOUtils.toString(request.getResponseBody(), "UTF-8"));
	}

	@Test
	public void testHelloName() throws ClientProtocolException, IOException, URISyntaxException {
		String name = "BredaH";
		request = new HttpRequest(Method.GET, urlBase + "/get/param?name=" + name);
		request.execute();
		// Check response status code
		Assert.assertEquals(200, request.getResponseStatusCode());
		// Check response body
		Assert.assertEquals(new GetExample().helloName(name), IOUtils.toString(request.getResponseBody(), "UTF-8"));
	}

	@Test
	public void testGetStatusCode2xx() throws ClientProtocolException, IOException, URISyntaxException {
		request = new HttpRequest(Method.GET, urlBase + "/get/response?id=2");
		request.execute();
		// Check response status code
		Assert.assertEquals(200, request.getResponseStatusCode());
	}

	@Test
	public void testGetStatusCode3xx() throws ClientProtocolException, IOException, URISyntaxException {
		request = new HttpRequest(Method.GET, urlBase + "/get/response?id=3");
		request.execute();
		// Check response status code
		Assert.assertEquals(307, request.getResponseStatusCode());
	}

	@Test
	public void testGetStatusCode4xx() throws ClientProtocolException, IOException, URISyntaxException {
		request = new HttpRequest(Method.GET, urlBase + "/get/response?id=4");
		request.execute();
		// Check response status code
		Assert.assertEquals(404, request.getResponseStatusCode());
	}

	@Test
	public void testGetStatusCode5xx() throws ClientProtocolException, IOException, URISyntaxException {
		request = new HttpRequest(Method.GET, urlBase + "/get/response?id=5");
		request.execute();
		// Check response status code
		Assert.assertEquals(500, request.getResponseStatusCode());
	}

}
