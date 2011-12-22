package pl.mbdev.openstage.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import pl.mbdev.openstage.IppCommand;
import pl.mbdev.openstage.IppForm;
import pl.mbdev.openstage.IppItem;
import pl.mbdev.openstage.IppStringItem;
import pl.mbdev.openstage.IppTextField;

/**
 * Uses Google's "define:" keyword to find definition of an entered word.
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
public class HttpConnectionTest extends OpenStageSDK_Test {
	
	public static void main(String[] args) {
		new HttpConnectionTest();
	}
	
	@Override
	protected void writeXml(PrintWriter out) {
		IppForm fo = new IppForm("Google define tool", "localhost",
				IppForm.Proportion.L25_R75);
		
		IppItem i1 = new IppItem();
		i1.add(new IppTextField("Word", "orthogonal", "word"));
		i1.add(new IppCommand("Find definition", IppCommand.Type.SELECT, null, null,
				IppCommand.DisplayOn.LISTITEM, false));
		fo.add(i1);
		
		IppStringItem si = null;
		String definition = "";
		try {
			// setting up the connection
			URL google = new URL("http://www.google.com/search?hl=en&q=define%3A"
					+ "orthogonal");
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					InetAddress.getByName("88.220.37.150"), 8080));
			URLConnection c = google.openConnection(proxy);
			System.setProperty("http.agent", "");
			c.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			
			// reading page
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			StringBuffer input = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				input.append(inputLine);
			in.close();
			
			// parsing content
			int start = input.indexOf("<body");
			int end = input.indexOf("Dictionary.com", start);
			definition = input.substring(start, end);
			definition = definition.substring(definition.indexOf("<em>"));
			definition = definition.substring(definition.indexOf("<div class=s>"))
					.substring(18);
			definition = definition.substring(0, definition.indexOf("<a class=fl"));
			definition = definition.substring(0, definition.length() - 12).replaceAll(
					"\\<.*?>", "");
			
		} catch (MalformedURLException e) {
			si = new IppStringItem("Error", "your word contains forbidden characters");
		} catch (IOException e) {
			si = new IppStringItem("Error", "could not connect to google.com");
		} catch (Exception e) {
			si = new IppStringItem("Error", "Java exception: " + e.getClass());
		}
		
		IppItem i2 = new IppItem();
		i2.add(new IppStringItem("Definition", definition));
		i2.add(new IppCommand("Exit", IppCommand.Type.EXIT, null, null,
				IppCommand.DisplayOn.LISTITEM, false));
		fo.add(i2);
		
		if (si != null) {
			fo.add(si);
		}
		
		fo.sendTo(out);
	}
	
}
