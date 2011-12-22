package pl.mbdev.openstage;

/**
 * Can be added to {@link IppForm}.
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
public class IppButton extends XmlFormItem {
	
	/**
	 * Defines possible types of the {@link IppButton}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * The button has an image.
		 */
		IMAGE;
	}
	
	/**
	 * Type of the button, value from {@link IppButton.Type}.
	 */
	private final Type type;
	
	/**
	 * Key from key-value pair, which is sent to the remote server.
	 */
	private String key;
	
	/**
	 * Value from key-value pair, which is sent to the remote server.
	 */
	private final String value;
	
	/**
	 * Creates new IppButton with all parameters available to set.
	 * 
	 * @param label
	 *           label of the button, text displayed on the button
	 * @param image
	 *           image for this button
	 * @param key
	 *           key from key-value pair, which is sent to the remote server
	 * @param value
	 *           value from key-value pair, which is sent to the remote server
	 */
	public IppButton(String label, Image image, String key, String value) {
		this(label, image, Type.IMAGE, key, value);
	}
	
	/**
	 * Creates new IppButton with all parameters available to set.
	 * 
	 * @param label
	 *           label of the button, text displayed on the button
	 * @param image
	 *           image for this button
	 * @param type
	 *           type of the button, value from {@link IppButton.Type}
	 * @param key
	 *           key from key-value pair, which is sent to the remote server
	 * @param value
	 *           value from key-value pair, which is sent to the remote server
	 */
	public IppButton(String label, Image image, Type type, String key, String value) {
		super("IppButton");
		this.type = type;
		this.key = key;
		this.value = value;
		
		add("Label", label);
		if (image != null)
			add(image);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (type != null)
			sb.append(attributeToXml("Type", type));
		if (key.equals(""))
			key = "key";
		sb.append(attributeToXml("Key", key));
		sb.append(attributeToXml("Value", value));
		return sb;
	}
	
}
