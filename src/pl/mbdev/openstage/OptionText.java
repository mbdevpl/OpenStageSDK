package pl.mbdev.openstage;

/**
 * Text field of the {@link Option} of the {@link IppList}. Option can contain up to 3
 * OptionTexts, and must contain the number of OptionTexts equal to the number of columns
 * of the list, to which the Option is added.
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
public class OptionText extends Xml {
	
	/**
	 * @param text
	 *           text of this option text
	 */
	public OptionText(String text) {
		this(text, null);
	}
	
	/**
	 * @param phoneNumber
	 *           phone number
	 */
	public OptionText(IppPhoneNumber phoneNumber) {
		this(null, phoneNumber);
	}
	
	/**
	 * Creates new OptionText with all parameters available.
	 * 
	 * @param text
	 *           text of this option text
	 * @param phoneNumber
	 *           phone number
	 */
	private OptionText(String text, IppPhoneNumber phoneNumber) {
		super("OptionText");
		
		this.setContents(text);
		if (phoneNumber != null)
			add(phoneNumber);
	}
	
}
