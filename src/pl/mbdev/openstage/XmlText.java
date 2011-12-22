package pl.mbdev.openstage;

/**
 * Superclass of {@link IppTextBox}, and also used in {@link IppTextField}.
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
public abstract class XmlText extends Xml {
	
	/**
	 * Possible settings for {@link IppTextField} or {@link IppTextBox} being a password.
	 * 
	 * @author Mateusz Bysiek
	 */
	protected enum Password {
		/**
		 * Yes.
		 */
		YES,
		/**
		 * No.
		 */
		NO;
	}
	
	/**
	 * Possible settings for the ability of the {@link IppTextField} or {@link IppTextBox}
	 * to be edited.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Uneditable {
		/**
		 * Yes.
		 */
		YES,
		/**
		 * No.
		 */
		NO;
	}
	
	/**
	 * Used only when defaultValue is set to TEXT.
	 */
	protected String text = "";
	
	/**
	 * Maximum length of the input, ignored if negative.
	 */
	private int maxSize = -1;
	
	/**
	 * If true, OpenStage device will treat the value of the text field/box as
	 * confidential, and try to obscure/hide it whenever possible.
	 */
	private boolean isPassword = false;
	
	/**
	 * Field/box value cannot be edited by the user, if true.
	 */
	private boolean isUneditable = false;
	
	/**
	 * Default constructor.
	 * 
	 * @param nodeName
	 *           name of the text node
	 * @param text
	 *           used only when defaultValue is set to TEXT
	 * @param maxSize
	 *           maximum length of the input, ignored if negative
	 * @param isPassword
	 *           if true, OpenStage device will treat the value of the field/box as
	 *           confidential, and try to obscure/hide it whenever possible
	 * @param isUneditable
	 *           field/box value cannot be edited by the user, if true
	 */
	public XmlText(String nodeName, String text, int maxSize, boolean isPassword,
			boolean isUneditable) {
		super(nodeName);
		this.text = text;
		this.maxSize = maxSize;
		this.isPassword = isPassword;
		this.isUneditable = isUneditable;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (maxSize > 0)
			sb.append(attributeToXml("MaxSize", maxSize));
		if (isPassword)
			sb.append(attributeToXml("PASSWORD", isPassword ? Password.YES : Password.NO));
		if (isUneditable)
			sb.append(attributeToXml("Uneditable", isUneditable ? Uneditable.YES
					: Uneditable.NO));
		return sb;
	}
	
}
