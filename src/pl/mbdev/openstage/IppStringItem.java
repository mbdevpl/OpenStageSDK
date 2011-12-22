package pl.mbdev.openstage;

/**
 * This simple item can be added to {@link IppForm}, and consists of single line of static
 * text with two columns. One of them contains a label, another contains content.
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
public class IppStringItem extends XmlFormItem {
	
	/**
	 * Text that will be visible in the left column.
	 */
	private String label = "";
	
	/**
	 * Text that will be visible in the right column.
	 */
	private String text = "";
	
	/**
	 * Constructs new IppStringItem.
	 * 
	 * @param label
	 *           text that will be visible in the left column
	 * @param text
	 *           this will be in the right column, and will scroll if text is too long
	 */
	public IppStringItem(String label, String text) {
		super("IppStringItem");
		this.label = label;
		this.text = text;
		
		this.add("Label", this.label);
		this.add("Text", this.text);
	}
	
}
