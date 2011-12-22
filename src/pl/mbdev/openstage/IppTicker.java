package pl.mbdev.openstage;

/**
 * Ticker is a piece of text, which is displayed underneath the title bar and moves from
 * right to left across the display.
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
public class IppTicker extends Xml {
	
	/**
	 * Constructs new ticker.
	 * 
	 * @param text
	 *           text of this ticker
	 */
	public IppTicker(String text) {
		super("IppTicker");
		
		if (text == null)
			throw new NullPointerException("text of IppTicker cannot be null");
		
		add("Text", text);
	}
	
	/**
	 * Returns current text of this ticker.
	 * 
	 * @return text of the ticker
	 */
	public String getText() {
		return this.getSubNode(0).getContents();
	}
	
	/**
	 * Sets new text for this ticker.
	 * 
	 * @param text
	 *           new piece of text
	 */
	public void setText(String text) {
		this.getSubNode(0).setContents(text);
	}
}
