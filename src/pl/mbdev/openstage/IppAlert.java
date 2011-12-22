package pl.mbdev.openstage;

/**
 * Used to display alerts to the user and after the alert is sent away, user can see
 * normal content of the {@link IppScreen}.
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
public class IppAlert extends Xml {
	
	/**
	 * Possible kinds of alerts (see {@link IppAlert}) are defined here.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * This is an alarm.
		 */
		ALARM,
		/**
		 * This is a confirmation.
		 */
		CONFIRMATION,
		/**
		 * This is an error.
		 */
		ERROR,
		/**
		 * This is just a piece of information.
		 */
		INFO,
		/**
		 * This is a warning.
		 */
		WARNING;
	}
	
	/**
	 * Type of alert, value from {@link IppAlert.Type}.
	 */
	private Type type = Type.INFO;
	
	/**
	 * Delay in milliseconds, or, if null, delay is set to FOREVER.
	 */
	private Integer delay = null;
	
	/**
	 * Constructs new information alert, it has type {@link IppAlert.Type#INFO}.
	 * 
	 * @param title
	 *           title of this alert
	 * @param text
	 *           text
	 */
	public IppAlert(String title, String text) {
		this(title, null, text, null, null, Type.INFO, null);
	}
	
	/**
	 * Constructs IppAlert with all options available to set.
	 * 
	 * @param title
	 *           title of this alert
	 * @param url
	 *           address used when a custom command is used to dismiss an alert, and only
	 *           if that command has type that causes communication with the server
	 * @param text
	 *           text
	 * @param phoneNumber
	 *           phone number
	 * @param image
	 *           custom image for this alert
	 * @param type
	 *           type of alert, value from {@link IppAlert.Type}
	 * @param delay
	 *           delay in milliseconds, or, if null, delay is set to FOREVER
	 */
	public IppAlert(String title, String url, String text, IppPhoneNumber phoneNumber,
			Image image, Type type, Integer delay) {
		super("IppAlert");
		
		this.type = type;
		this.delay = delay;
		
		this.add("Title", title);
		if (url != null)
			this.add("Url", url);
		Xml o = this.addAndReturn("Text", text);
		if (phoneNumber != null)
			o.add(phoneNumber);
		if (image != null)
			this.add(image);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.attributeToXml("Type", type));
		if (delay == null)
			sb.append(this.attributeToXml("Delay", "FOREVER"));
		else
			sb.append(this.attributeToXml("Delay", delay));
		return sb;
	}
	
}
