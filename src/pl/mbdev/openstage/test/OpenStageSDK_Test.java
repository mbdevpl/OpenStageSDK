package pl.mbdev.openstage.test;

import java.io.PrintWriter;

import pl.mbdev.openstage.Xml;

/**
 * This class is used to test behaviour of various methods from SDK. It sends character
 * streams to console instead of network, therefore it is very easy to debug the SDK.<br />
 * <br />
 * Subclasses should define the main() method, and only invoke own constructor there. This
 * will start SDK test, which should be defined in the writeXml(PrintWriter) method of a
 * subclass. Test is meant to be generating sample XML output via
 * {@link Xml#sendTo(PrintWriter out)} method. This output is then redirected by this
 * class to System.out, and all exceptions are also printed out to console.
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
public abstract class OpenStageSDK_Test {
	
	/**
	 * Default constructor.
	 */
	protected OpenStageSDK_Test() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(System.out);
			this.writeXml(out);
			out.close();
		} catch (RuntimeException e) {
			if (out != null)
				out.close();
			e.printStackTrace(System.err);
		}
		
	}
	
	/**
	 * Here you should initialize all XML objects you would like to test. The generated
	 * content will be sent to console. Use any {@link Xml} sub-class, or your custom
	 * class.
	 * 
	 * @param out
	 *           PrintWriter created from System.out, you should use sendTo(out) at some
	 *           point of your test to see any results
	 */
	protected abstract void writeXml(PrintWriter out);
	
	/**
	 * Subclasses must define this method, and only invoke own constructor here. This will
	 * start SDK test, which is defined in the writeXml(PrintWriter) method of a subclass.
	 * Test is meant to be generating sample XML output via sendTo(PrintWriter) method.
	 * 
	 * @param args
	 *           not used
	 */
	public static void main(String[] args) {
		// test implementation must be here
		
		// simply construct your non-abstract subclass here, like:
		// new OpenStageSDK_Test();
	}
	
}
