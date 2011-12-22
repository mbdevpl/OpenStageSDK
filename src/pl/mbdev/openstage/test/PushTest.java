package pl.mbdev.openstage.test;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import pl.mbdev.openstage.push.Push;

/**
 * Tests push capabilities of a OpenStage phone.
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
public class PushTest extends OpenStageSDK_Test {
	
	public static void main(String[] args) {
		new PushTest();
	}
	
	@Override
	protected void writeXml(PrintWriter out) {
		Push p;
		try {
			p = new Push(new URL("http://172.27.75.98:8080/OpenIM/Inbox"), "OpenIM",
					Push.RequestType.FORCE, "pushed", "yes");
			p.sendTo("172.27.75.51");
			p.sendTo("172.27.75.60");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}
	
}
