package pl.mbdev.openstage;

/**
 * Options are added to the {@link IppList}. To be successfully added, Option must have as
 * many {@link OptionText}s as there are columns in the IppList.
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
public class Option extends Xml {
	
	/**
	 * Defines possible {@link Option} selection states.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Selected {
		/**
		 * The option is selected.
		 */
		TRUE,
		/**
		 * The option is not selected.
		 */
		FALSE;
	}
	
	/**
	 * Indicates that this option is preselected.
	 */
	private final boolean selected;
	
	/**
	 * Key from key-value pair, which is sent to the remote server.
	 */
	private String key;
	
	/**
	 * Value from key-value pair, which is sent to the remote server.
	 */
	private String value;
	
	/**
	 * Number of option texts (see {@link OptionText}) present in this option.
	 */
	private int optionTextCount = 0;
	
	/**
	 * Creates new option, initially without any {@link OptionText}.
	 * 
	 * @param image
	 *           optional image for this IppList option
	 * @param selected
	 *           if true, indicates that this option is preselected
	 * @param key
	 *           key from key-value pair, which is sent to the remote server
	 * @param value
	 *           value from key-value pair, which is sent to the remote server
	 */
	public Option(Image image, boolean selected, String key, String value) {
		this(new OptionText[] {}, image, selected, key, value);
	}
	
	/**
	 * Creates new option with a single text inside.
	 * 
	 * @param optionText
	 *           the text for this option
	 * @param image
	 *           optional image for this IppList option
	 * @param selected
	 *           if true, indicates that this option is preselected
	 * @param key
	 *           key from key-value pair, which is sent to the remote server
	 * @param value
	 *           value from key-value pair, which is sent to the remote server
	 */
	public Option(String optionText, Image image, boolean selected, String key,
			String value) {
		this(new OptionText(optionText), image, selected, key, value);
	}
	
	/**
	 * Creates new option with a single OptionText.
	 * 
	 * @param optionText
	 *           the text for this option, {@link OptionText}
	 * @param image
	 *           optional image for this IppList option
	 * @param selected
	 *           if true, indicates that this option is preselected
	 * @param key
	 *           key from key-value pair, which is sent to the remote server
	 * @param value
	 *           value from key-value pair, which is sent to the remote server
	 */
	public Option(OptionText optionText, Image image, boolean selected, String key,
			String value) {
		this(new OptionText[] { optionText }, image, selected, key, value);
	}
	
	/**
	 * Creates new option with single or multiple {@link OptionText}s, and with all other
	 * parameters available to set.
	 * 
	 * @param optionTexts
	 *           array of {@link OptionText} objects, from 1 to 3 option texts
	 * @param image
	 *           optional image for this IppList option
	 * @param selected
	 *           if true, indicates that this option is preselected
	 * @param key
	 *           key from key-value pair, which is sent to the remote server
	 * @param value
	 *           value from key-value pair, which is sent to the remote server
	 */
	public Option(OptionText[] optionTexts, Image image, boolean selected, String key,
			String value) {
		super("Option");
		
		this.selected = selected;
		this.key = key;
		this.value = value;
		
		if (optionTexts != null)
			for (OptionText ot : optionTexts)
				add(ot);
		
		add(image);
	}
	
	/**
	 * Adds a new {@link OptionText} to this option.
	 * 
	 * @param ot
	 *           option's text, {@link OptionText}
	 */
	public void add(OptionText ot) {
		if (optionTextCount >= 3)
			throw new IllegalArgumentException("Option cannot have more than 3 OptionTexts.");
		super.add(ot);
		optionTextCount++;
	}
	
	/**
	 * Adds a new option's text to this option.
	 * 
	 * @param optionText
	 *           option's text, a string of characters
	 */
	public void add(String optionText) {
		this.add(new OptionText(optionText));
	}
	
	/**
	 * Returns the number of {@link OptionText}s present in this Option.
	 * 
	 * @return OptionText object count of this Option
	 */
	public int getOptionTextCount() {
		return optionTextCount;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (selected)
			sb.append(attributeToXml("Selected", selected ? Selected.TRUE : Selected.FALSE));
		if (key.equals(""))
			key = "key";
		sb.append(attributeToXml("Key", key));
		sb.append(attributeToXml("Value", value));
		return sb;
	}
	
}
