package pl.mbdev.openstage.push;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Push capability of the OpenStage phone can be utilised with use of this class. Push
 * enables remote launching of the application that was previously defined in the XML
 * Applications list of a given OpenStage phone.
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
public class Push extends PostRequest {
	
	/**
	 * Default port at which the OpenStage phone listens for push requests.
	 */
	public static final String PHONE_PORT = "8085";
	
	/**
	 * Default location at which the push-response application can be found in the
	 * OpenStage device.
	 */
	public static final String PHONE_PUSH_SCRIPT = "/server_push.html/ServerPush";
	
	/**
	 * Type of the push request that will be sent to the OpenStage phone.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum RequestType {
		/**
		 * Application will be launched if phone is not locked, and then it will be brought
		 * to foreground if there is no other application running.
		 */
		ACTIVE,
		/**
		 * 
		 */
		QUEUE,
		/**
		 * 
		 */
		INDICATE,
		/**
		 * It will force the launch of the appliaction, and bring it to foreground. Unless
		 * the phone is locked.
		 */
		FORCE;
	}
	
	/**
	 * Full URL to the application at the remote server.
	 */
	private final URL appAddr;
	
	/**
	 * Constructs new definition of push, in which all parameters are set directly.
	 * 
	 * @param serverAddress
	 *           IP address or domain, without port number
	 * @param serverPort
	 *           port number
	 * @param programName
	 *           path on the server that leads to the XML application
	 * @param requestType
	 *           type of the request, value from {@link Push.RequestType}
	 * @param midletName
	 *           program name, as defined in the OpenStage device
	 * @param serverProtocol
	 *           protocol used by the serever, usually 'http' or 'https'
	 * @param serverContextKey
	 *           key from key-value pair, which is sent back from the phone to the
	 *           application in the response to the push request
	 * @param serverContextValue
	 *           value from key-value pair, which is sent back from the phone to the
	 *           application in the response to the push request
	 * @throws MalformedURLException
	 *            when given parameters do not describe a correct URL
	 */
	public Push(String serverAddress, int serverPort, String programName,
			RequestType requestType, String midletName, String serverProtocol,
			String serverContextKey, String serverContextValue) throws MalformedURLException {
		this(new StringBuffer(serverProtocol).append("://").append(serverAddress)
				.append(':').append(serverPort).append(programName).toString(), midletName,
				requestType, serverContextKey, serverContextValue);
	}
	
	/**
	 * Creates a new push request, with quite convenient way of passing the arguments.
	 * 
	 * @param applicationAddress
	 *           full address of the application on the remote server
	 * @param midletName
	 *           program name, as defined in the OpenStage device
	 * @param requestType
	 *           type of the request, value from {@link Push.RequestType}
	 * @param serverContextKey
	 *           key from key-value pair, which is sent back from the phone to the
	 *           application in the response to the push request
	 * @param serverContextValue
	 *           value from key-value pair, which is sent back from the phone to the
	 *           application in the response to the push request
	 * @throws MalformedURLException
	 *            when given address does not describe a correct URL
	 */
	public Push(String applicationAddress, String midletName, RequestType requestType,
			String serverContextKey, String serverContextValue) throws MalformedURLException {
		this(new URL(applicationAddress), midletName, requestType, serverContextKey,
				serverContextValue);
	}
	
	/**
	 * Creates a new push request, with the most convenient way of passing the arguments.
	 * 
	 * @param appAddr
	 *           full URL of the application on the remote server
	 * @param midletName
	 *           program name, as defined in the OpenStage device
	 * @param requestType
	 *           type of the request, value from {@link Push.RequestType}
	 * @param serverContextKey
	 *           key from key-value pair, which is sent back from the phone to the
	 *           application in the response to the push request
	 * @param serverContextValue
	 *           value from key-value pair, which is sent back from the phone to the
	 *           application in the response to the push request
	 */
	public Push(URL appAddr, String midletName, RequestType requestType,
			String serverContextKey, String serverContextValue) {
		super(new RequestParameters(new String[] { "ServerAddr", "ServerPort",
				"ProgramName", "RequestType", "MidletName", "ServerProtocol",
				"ServerContextKey", "ServerContextValue" }, new Object[] { appAddr.getHost(),
				appAddr.getPort(), appAddr.getPath(), requestType, midletName,
				appAddr.getProtocol(), serverContextKey, serverContextValue }));
		
		this.appAddr = appAddr;
	}
	
	/**
	 * Returns the full address to the application at the remote server.
	 * 
	 * @return full address to the application at the remote server
	 */
	public URL getAppAddr() {
		return appAddr;
	}
	
	/**
	 * Sends the push request to the given OpenStage phone and reads the response.
	 * 
	 * @param phoneIP
	 *           IP address of the OpenStage phone that will be pushed
	 * @return response of the phone
	 * @throws IOException
	 *            thrown when the given URL caused a connection error, there was a protocol
	 *            exception or data transfer was interrupted
	 * @throws MalformedURLException
	 *            when the provided phone IP is not a valid IP
	 */
	protected String getResponse(String phoneIP) throws MalformedURLException, IOException {
		return this.getResponse(new URL("http://" + phoneIP + ":" + PHONE_PORT
				+ PHONE_PUSH_SCRIPT));
	}
	
	/**
	 * Sends the push request to the given OpenStage phone.
	 * 
	 * @param phoneIP
	 *           IP address of the OpenStage phone that will be pushed
	 * @return true if the push request was sent successfully
	 */
	public boolean sendTo(String phoneIP) {
		try {
			this.getResponse(phoneIP);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
