package pl.mbdev.openstage;

/**
 * Can be added to {@link IppForm} and is in appearance similar to {@link IppStringItem},
 * but IppTextField can be edited. The value entered by the user can be then sent back to
 * server.
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
public class IppTextField extends XmlFormItem {
	
	/**
	 * Narrows the set of possible characters of the {@link IppTextField}, and sometimes
	 * changes the method by which they are entered.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Constraint {
		/**
		 * No constraints, any character may be entered.
		 */
		ANY,
		/**
		 * Only numbers may be entered.
		 */
		NUMERIC,
		/**
		 * Any character may be entered, but they are all covered with asterisks.
		 */
		PASSWORD,
		/**
		 * Phone number can be entered.
		 */
		PHONENUMBER,
		/**
		 * URL can be entered.
		 */
		URL,
		/**
		 * Email address can be entered.
		 */
		EMAILADDR;
	}
	
	/**
	 * Possible kinds of default values of {@link IppTextField}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum DefaultValue {
		/**
		 * Value from text parameter given in the constructor is used as a starting value of
		 * this {@link IppTextField}.
		 */
		TEXT;
	}
	
	/**
	 * Label of the field, placed in left column.
	 */
	private String label = "";
	
	/**
	 * Value from {@link IppTextField.Constraint}.
	 */
	private Constraint constraint = Constraint.ANY;
	
	/**
	 * One of the values from {@link IppTextField.DefaultValue}.
	 */
	private DefaultValue defaultValue = DefaultValue.TEXT;
	
	/**
	 * Key from the key-value pair, where value is the content of the editable field.
	 */
	private String key = "";
	
	/**
	 * Data of the text field must be here due to inheritance restrictions in Java.
	 */
	private XmlText xmlText;
	
	/**
	 * Creates IppTextField with default characteristics, therefore the value will not be
	 * treated as confidential data.
	 * 
	 * @param label
	 *           label of the field, placed in left column
	 * @param text
	 *           initial contents of the field
	 * @param key
	 *           key of the key-value pair sent to the URL specified in {@link IppForm},
	 *           content of the editable text field is the value
	 */
	public IppTextField(String label, String text, String key) {
		this(label, text, -1, Constraint.ANY, false, DefaultValue.TEXT, key, false);
	}
	
	/**
	 * Creates a password field.
	 * 
	 * @param label
	 *           label of the field, placed in left column
	 * @param key
	 *           key of the key-value pair sent to the URL specified in {@link IppForm},
	 *           content of the editable text field is the value
	 */
	public IppTextField(String label, String key) {
		this(label, "", -1, Constraint.PASSWORD, true, DefaultValue.TEXT, key, false);
	}
	
	/**
	 * Creates an ordinary text field, which may not be editable.
	 * 
	 * @param label
	 *           label of the field, placed in left column
	 * @param text
	 *           initial contents of the field
	 * @param key
	 *           key of the key-value pair sent to the URL specified in {@link IppForm},
	 *           content of the editable or not editable text field is the value
	 * @param isUneditable
	 *           field value cannot be edited by the user, if true
	 */
	public IppTextField(String label, String text, String key, boolean isUneditable) {
		this(label, "", -1, Constraint.ANY, false, DefaultValue.TEXT, key, isUneditable);
	}
	
	/**
	 * Creates IppTextField with all characteristics defined by the user.
	 * 
	 * @param label
	 *           label of the field, placed in left column
	 * @param text
	 *           used only when defaultValue is set to TEXT
	 * @param maxSize
	 *           maximum length of the input, ignored if negative
	 * @param constraint
	 *           value from {@link IppTextField.Constraint}
	 * @param isPassword
	 *           OpenStage will try to obscure the value of this field whenever possible
	 * @param defaultValue
	 *           one of the values from {@link IppTextField.DefaultValue}
	 * @param key
	 *           key from the key-value pair, where value is the content of the editable
	 *           field
	 * @param isUneditable
	 *           field value cannot be edited by the user, if true
	 */
	public IppTextField(String label, String text, int maxSize, Constraint constraint,
			boolean isPassword, DefaultValue defaultValue, String key, boolean isUneditable) {
		super("IppTextField");
		this.xmlText = new XmlText("IppTextField", text, maxSize, isPassword, isUneditable) {
		};
		this.label = label;
		
		this.constraint = constraint;
		this.defaultValue = defaultValue;
		this.key = key;
		
		this.add("Label", this.label);
		this.add("Text", this.xmlText.text);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = xmlText.attributesToXmlString();
		sb.append(attributeToXml("Constraint", constraint));
		sb.append(attributeToXml("Default", defaultValue));
		sb.append(attributeToXml("Key", key));
		return sb;
	}
	
}
