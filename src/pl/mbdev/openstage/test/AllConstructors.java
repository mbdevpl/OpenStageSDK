package pl.mbdev.openstage.test;

import java.io.PrintWriter;
import java.util.TimeZone;

import pl.mbdev.openstage.*;

/**
 * This class contains all constructors of OpenStage SDK, the reader is able to see how
 * the objects should be initialized and what is sent to OpenStage VoIP phone for each of
 * the objects in the SDK.
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
public class AllConstructors extends OpenStageSDK_Test {
	
	public static void main(String[] args) {
		new AllConstructors();
	}
	
	@Override
	protected void writeXml(PrintWriter out) {
		// root elements
		IppPhone p = new IppPhone();
		p.sendTo(out);
		IppDisplay d = new IppDisplay(1, 2);
		d.sendTo(out);
		
		// fundamental element, up to 5 screens per display
		IppScreen s = new IppScreen(1);
		s.sendTo(out);
		
		// low-level sub-elements are used in many different elements
		Image im = new Image("name_in_cache", "image_source");
		IppPhoneNumber pn = new IppPhoneNumber("alternat. desrc.",
				IppPhoneNumber.ImageType.PICTURECLIP, IppPhoneNumber.NumberType.BOTH);
		
		// unique elements of a screen
		IppAlert al = new IppAlert("title", "url", "text", pn, im, IppAlert.Type.INFO, 200);
		al.sendTo(out);
		IppList l = new IppList("list title", "http://localhost:80/",
				IppList.Type.IMPLICIT, 2);
		l.sendTo(out);
		IppTextBox tb = new IppTextBox("title", "text", "url", 256,
				IppTextBox.Constraint.ANY, false, IppTextBox.DefaultValue.TEXT, "text", false);
		tb.sendTo(out);
		IppPlayer pl = new IppPlayer("http://localhost/file.mp3", IppPlayer.Mode.CALL,
				IppPlayer.State.PLAYING, "key", new IppGauge("playing file.mp3", 5, 0, 0, 0));
		pl.sendTo(out);
		IppForm f = new IppForm("title", "url", IppForm.Proportion.L50_R50);
		f.sendTo(out);
		
		// elements used on the side of the unique elements
		IppScreen sc = new IppScreen(-1);
		sc.add(new IppTextBox("title", "text", "url", "key")); // must have unique element
		
		IppAction a = new IppAction(IppAction.Type.MAKECALL, "+48223333333");
		sc.add(a);
		IppCommand c = new IppCommand("label", IppCommand.Type.SCREEN, 1, 1, 10, "key",
				"value", IppCommand.DisplayOn.OPTIONS, true, true);
		sc.add(c);
		IppTicker ti = new IppTicker("example rolling text");
		sc.add(ti);
		IppHidden h = new IppHidden(IppHidden.Type.VALUE, "some_key", "some_value");
		sc.add(h);
		IppKey k = new IppKey(true, true, IppKey.BufferKeys.YES, -1, '#', "url");
		sc.add(k);
		
		sc.sendTo(out);
		
		IppForm fo = new IppForm("title", "url", IppForm.Proportion.L50_R50);
		
		// sub-elements of the form elements
		IppItem i = new IppItem();
		fo.add(i);
		IppStringItem is = new IppStringItem("label", "text");
		fo.add(is);
		IppImageItem ii = new IppImageItem("label", im, "alternative text");
		fo.add(ii);
		IppSpacer sp = new IppSpacer(IppSpacer.NewLine.NEWLINE_AFTER);
		fo.add(sp);
		IppTextField tf = new IppTextField("label", "text", "key");
		fo.add(tf);
		IppChoiceGroup cg = new IppChoiceGroup("cg label", IppChoiceGroup.Type.EXCLUSIVE,
				new Option[] {
						new Option("text one", new Image("image_cache", "img_src.png"), false,
								"selected", "1"),
						new Option("text two", new Image("image_cache", "img_src.png"), true,
								"selected", "2") });
		fo.add(cg);
		IppDateField df = new IppDateField("label", TimeZone.getDefault(), "2000-12-31",
				"23:30:00", IppDateField.Mode.DATETIME, IppDateField.Default.MODE, "date",
				"time");
		fo.add(df);
		IppButton b = new IppButton("the label", null, IppButton.Type.IMAGE, "b_key",
				"b_val");
		fo.add(b);
		IppGauge g = new IppGauge("gauge label", 50, 10, "gaugeVal");
		fo.add(g);
		
		fo.sendTo(out);
	}
	
}
