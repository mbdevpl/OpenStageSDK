package pl.mbdev.openstage.test;

import java.io.PrintWriter;
import java.util.GregorianCalendar;

import pl.mbdev.openstage.*;

/**
 * Example showing how you can add commands directly to form items, instead of wrapping
 * each of them with {@link IppItem} first. This is achieved because of automatic wrapping
 * up mechanism. Look at the output to see how the mechanism of automatic wrapping in
 * {@link IppItem} works.
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
public class WrappingTest extends OpenStageSDK_Test {
	
	public static void main(String[] args) {
		new WrappingTest();
	}
	
	@Override
	protected void writeXml(PrintWriter out) {
		IppDisplay d = new IppDisplay(1, -1);
		
		IppScreen s1 = new IppScreen(1);
		d.add(s1);
		IppForm f1 = new IppForm("Form One", "localhost", IppForm.Proportion.L50_R50);
		s1.add(f1);
		IppScreen s2 = new IppScreen(2);
		d.add(s2);
		IppForm f2 = new IppForm("Form Two", "localhost", null);
		s2.add(f2);
		
		IppStringItem is = new IppStringItem("Text item", "text");
		is.add(new IppCommand(IppCommand.Type.CANCEL, IppCommand.DisplayOn.BOTH));
		is.add(new IppCommand(IppCommand.Type.BACK, IppCommand.DisplayOn.BOTH));
		is.add(new IppCommand(IppCommand.Type.EXIT, IppCommand.DisplayOn.BOTH));
		f1.add(is);
		f1.add(new IppSpacer());
		f1.add(new IppDateField("label", new GregorianCalendar(),
				IppDateField.Mode.DATETIME, "date", "time"));
		f1.add(new IppButton("label", null, "key", ""));
		
		IppImageItem ii = new IppImageItem("Image item", null, "alt. text");
		f2.add(ii);
		ii.add(new IppCommand(IppCommand.Type.CANCEL, IppCommand.DisplayOn.BOTH));
		ii.add(new IppCommand(IppCommand.Type.BACK, IppCommand.DisplayOn.BOTH));
		ii.add(new IppCommand(IppCommand.Type.EXIT, IppCommand.DisplayOn.BOTH));
		f2.add(new IppStringItem("Text item", "example text"));
		
		d.sendTo(out);
	}
	
}
