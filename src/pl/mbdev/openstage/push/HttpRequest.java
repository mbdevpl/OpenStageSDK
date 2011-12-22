package pl.mbdev.openstage.push;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Enables easier formulation and execution of HTTP requests.
 * 
 * <pre>
 * Copyright 2011 Mateusz Bysiek,
 *     mb@mbdev.pl, http://mbdev.pl/
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </pre>
 * 
 * @author Mateusz Bysiek
 */
public abstract class HttpRequest {
	
	/**
	 * Parameters parameters of this request.
	 */
	private final RequestParameters parameters;
	
	/**
	 * HTTP connection.
	 */
	private HttpURLConnection connection;
	
	/**
	 * Output stream.
	 */
	protected OutputStream out;
	
	/**
	 * Writer for the output stream.
	 */
	private OutputStreamWriter writer;
	
	/**
	 * Input stream.
	 */
	private InputStream in;
	
	/**
	 * Reader for the input stream.
	 */
	private Reader reader;
	
	/**
	 * Buffer for the reader of the input stream.
	 */
	private BufferedReader bufferedReader;
	
	/**
	 * Creates new generic HTTP request with given parameters.
	 * 
	 * @param parameters
	 *           parameters of this request
	 */
	public HttpRequest(RequestParameters parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Returns the request parameters.
	 * 
	 * @return request parameters
	 */
	protected final RequestParameters getParameters() {
		return parameters;
	}
	
	/**
	 * @param targetAddress
	 *           target address
	 * @param method
	 *           HTTP method: GET, POST, ...
	 * @throws IOException
	 *            when connection cannot be established
	 */
	protected void connectTo(URL targetAddress, String method) throws IOException {
		connection = (HttpURLConnection) targetAddress.openConnection();
		
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		// urlc.setAllowUserInteraction(false);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestMethod(method);
	}
	
	/**
	 * @param encoding
	 *           character encoding, for example 'UTF-8'
	 * @return content writer for this HTTP request
	 * @throws UnsupportedEncodingException
	 *            when given encoding is not supported by the server.
	 * @throws IOException
	 *            when output streams cannot be opened
	 */
	protected OutputStreamWriter startOutput(String encoding)
			throws UnsupportedEncodingException, IOException {
		if (writer != null)
			throw new RuntimeException("output already started");
		out = connection.getOutputStream();
		writer = new OutputStreamWriter(out, encoding);
		return writer;
	}
	
	/**
	 * @throws IOException
	 *            when output streams cannot be closed
	 */
	protected void endOutput() throws IOException {
		if (writer != null)
			writer.close();
		writer = null;
		if (out != null)
			out.close();
		out = null;
	}
	
	/**
	 * Starts input, opens input stream.
	 * 
	 * @return buffered reader for the input
	 * @throws IOException
	 *            when the input stream cannot be opened
	 */
	protected BufferedReader startInput() throws IOException {
		in = connection.getInputStream();
		reader = new InputStreamReader(in);
		bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}
	
	/**
	 * Closes the input stream.
	 * 
	 * @throws IOException
	 *            when the input stream cannot be closed
	 */
	protected void endInput() throws IOException {
		if (bufferedReader != null)
			bufferedReader.close();
		bufferedReader = null;
		if (reader != null)
			reader.close();
		reader = null;
		if (in != null)
			in.close();
		in = null;
	}
	
	/**
	 * Disconnects from the remote server.
	 */
	public void disconnect() {
		if (connection != null)
			connection.disconnect();
	}
	
	/**
	 * Provides default implementation for HTTP request execution.
	 * 
	 * @param targetAddress
	 *           URL of the target to which this request will be sent
	 * @return response from the target address
	 * @throws IOException
	 *            thrown when the given URL caused a connection error, there was a protocol
	 *            exception or data transfer was interrupted
	 */
	protected abstract String getResponse(URL targetAddress) throws IOException;
	
	/**
	 * Sends the push to a given URL.
	 * 
	 * @param targetAddress
	 *           URL of the target to which this request will be sent
	 * @return true if the request was successfully sent
	 * @throws IOException
	 *            thrown when the given URL caused a connection error, there was a protocol
	 *            exception or data transfer was interrupted
	 */
	protected abstract boolean sendTo(URL targetAddress) throws IOException;
	
}
