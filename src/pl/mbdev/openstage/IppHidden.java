package pl.mbdev.openstage;

/**
 * Can store key-value pairs that are sent to the server when an {@link IppCommand} of
 * type {@link IppCommand.Type#SELECT} is used. Hidden fields are invisible for an
 * OpenStage user. They belong to {@link IppScreen} object, therefore must be added
 * directly to a screen.
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
public class IppHidden extends Xml {
	
	/**
	 * Defines possible types of values.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * Custom value will be sent to remote server.
		 */
		VALUE,
		/**
		 * Custom value is ignored, and value is set to the phone number of the OpenStage
		 * device, which runs the application.
		 */
		PHONENUMBER,
		/**
		 * Custom value is ignored, and value is set to the ip address of the OpenStage
		 * device, which runs the application.
		 */
		IPADDRESS;
	}
	
	/**
	 * Value from {@link IppHidden.Type}.
	 */
	private final Type type;
	
	/**
	 * Key from the key-value pair.
	 */
	private final String key;
	
	/**
	 * Creates hidden field with default 'Key' and 'Value', for type PHONENUMBER or
	 * IPADDRESS.
	 * 
	 * @param type
	 *           any value from {@link IppHidden.Type}, but not VALUE
	 */
	IppHidden(Type type) {
		this(type, type.toString().toLowerCase(), null);
	}
	
	/**
	 * Constructs new hidden field without custom value, but with custom key for phone
	 * number or ip address.
	 * 
	 * @param type
	 *           any value from {@link IppHidden.Type}, but not VALUE
	 * @param key
	 *           key from key-value pair
	 */
	public IppHidden(Type type, String key) {
		this(type, key, null);
	}
	
	/**
	 * Constructs fully customisable hidden field.
	 * 
	 * @param type
	 *           any value from {@link IppHidden.Type}
	 * @param key
	 *           key from key-value pair
	 * @param value
	 *           custom (hidden) value, used only when type is set to
	 *           {@link IppHidden.Type#VALUE}
	 */
	public IppHidden(Type type, String key, String value) {
		super("IppHidden");
		this.type = type;
		if (key == null || key.equals(""))
			throw new IllegalArgumentException(
					"'Key' attribute of IppHidden must be defined");
		this.key = key;
		
		if (value != null)
			add("Value", value);
		else if (type.equals(Type.VALUE))
			throw new IllegalArgumentException("if 'Type' attribute is equal 'VALUE', "
					+ "'value' attribute cannot be null");
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Type", type));
		sb.append(attributeToXml("Key", key));
		return sb;
	}
	
}
