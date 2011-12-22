package pl.mbdev.openstage;

/**
 * Multi-line box with editable text, which is similar to {@link IppTextField}, but can be
 * added directly to {@link IppScreen} and cannot be added to {@link IppForm}. It also has
 * some minor differences in possible constraints (see {@link IppTextBox.Constraint} and
 * {@link IppTextField.Constraint}).
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
public class IppTextBox extends XmlText {
	
	/**
	 * Defines kinds of {@link IppTextBox} input.
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
		PASSWORD;
	}
	
	/**
	 * Defined possible kinds of default value for {@link IppTextBox} objects.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum DefaultValue {
		/**
		 * This box is empty by default, the text parameter will be ignored.
		 */
		NULL,
		/**
		 * The value from text parameter will be used.
		 */
		TEXT,
		/**
		 * Text parameter will be ignored, and phone number of this OpenStage device will be
		 * used.
		 */
		PHONENUMBER;
	}
	
	/**
	 * Title of this IppTextBox, placed in left column.
	 */
	private String title = "";
	
	/**
	 * URL address to which the value from the text box can be sent via SELECT command (see
	 * {@link IppCommand} and {@link IppCommand.Type}).
	 */
	private String url = "";
	
	/**
	 * Value from {@link IppTextBox.Constraint}.
	 */
	private Constraint constraint = Constraint.ANY;
	
	/**
	 * One of the values from {@link IppTextBox.DefaultValue}.
	 */
	private DefaultValue defaultValue = DefaultValue.TEXT;
	
	/**
	 * Key from the key-value pair, where value is the content of the editable box.
	 */
	private String key = "";
	
	/**
	 * Creates a new text box.
	 * 
	 * @param title
	 *           title of this IppTextBox, placed in left column
	 * @param url
	 *           URL address to which the value from the text box can be sent via SELECT
	 *           command (see {@link IppCommand} and {@link IppCommand.Type})
	 * @param defaultValue
	 *           one of the values from {@link IppTextBox.DefaultValue}
	 * @param key
	 *           key from the key-value pair, where value is the content of the editable
	 *           box
	 * @param isUneditable
	 *           field value cannot be edited by the user, if true
	 */
	public IppTextBox(String title, String url, DefaultValue defaultValue, String key,
			boolean isUneditable) {
		this(title, "", url, -1, Constraint.ANY, false, defaultValue, key, isUneditable);
	}
	
	/**
	 * Creates a new text box.
	 * 
	 * @param title
	 *           title of this IppTextBox, placed in left column
	 * @param text
	 *           used only when defaultValue is set to TEXT
	 * @param url
	 *           URL address to which the value from the text box can be sent via SELECT
	 *           command (see {@link IppCommand} and {@link IppCommand.Type})
	 * @param key
	 *           key from the key-value pair, where value is the content of the editable
	 *           box
	 */
	public IppTextBox(String title, String text, String url, String key) {
		this(title, text, url, -1, Constraint.ANY, false, DefaultValue.TEXT, key, false);
	}
	
	/**
	 * Creates a new text box.
	 * 
	 * @param title
	 *           title of this IppTextBox, placed in left column
	 * @param text
	 *           used only when defaultValue is set to TEXT
	 * @param url
	 *           URL address to which the value from the text box can be sent via SELECT
	 *           command (see {@link IppCommand} and {@link IppCommand.Type})
	 * @param maxSize
	 *           maximum length of the input, ignored if negative
	 * @param constraint
	 *           value from {@link IppTextBox.Constraint}
	 * @param isPassword
	 *           if true, OpenStage device will treat the value of the box as confidential,
	 *           and try to obscure/hide it whenever possible
	 * @param defaultValue
	 *           one of the values from {@link IppTextBox.DefaultValue}
	 * @param key
	 *           key from the key-value pair, where value is the content of the editable
	 *           box
	 * @param isUneditable
	 *           field value cannot be edited by the user, if true
	 */
	public IppTextBox(String title, String text, String url, int maxSize,
			Constraint constraint, boolean isPassword, DefaultValue defaultValue,
			String key, boolean isUneditable) {
		super("IppTextBox", text, maxSize, isPassword, isUneditable);
		this.title = title;
		this.url = url;
		
		this.constraint = constraint;
		this.defaultValue = defaultValue;
		this.key = key;
		
		if (title != null)
			this.add("Title", this.title);
		if (defaultValue != DefaultValue.PHONENUMBER && defaultValue != DefaultValue.NULL
				&& text != null) {
			this.add("Text", this.text);
		} else if (defaultValue == DefaultValue.TEXT && text == null) {
			this.defaultValue = DefaultValue.NULL;
		}
		if (url != null)
			this.add("Url", this.url);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = super.attributesToXmlString();
		sb.append(attributeToXml("Constraint", constraint));
		sb.append(attributeToXml("Default", defaultValue));
		sb.append(attributeToXml("Key", key));
		return sb;
	}
	
}
