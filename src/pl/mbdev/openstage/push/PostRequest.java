package pl.mbdev.openstage.push;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * Defines a HTTP POST request that is a parent of the push request formed for OpenStage
 * device.
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
public abstract class PostRequest extends HttpRequest {
	
	/**
	 * Creates new HTTP POST request.
	 * 
	 * @param parameters
	 *           parameters of this request
	 */
	public PostRequest(RequestParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected String getResponse(URL targetAddress) throws IOException {
		this.connectTo(targetAddress, "POST");
		
		OutputStreamWriter out = this.startOutput("UTF-8");
		String str = this.getParameters().toString().substring(1);
		out.write(str);
		out.flush();
		this.endOutput();
		
		BufferedReader reader = this.startInput();
		String response = reader.readLine();
		this.endInput();
		
		this.disconnect();
		return response;
	}
	
	@Override
	protected boolean sendTo(URL targetAddress) throws IOException {
		this.connectTo(targetAddress, "POST");
		
		OutputStreamWriter out = this.startOutput("UTF-8");
		String str = this.getParameters().toString().substring(1);
		out.write(str);
		out.flush();
		this.endOutput();
		
		this.disconnect();
		return true;
	}
	
}
