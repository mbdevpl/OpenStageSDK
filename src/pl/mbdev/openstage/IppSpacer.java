package pl.mbdev.openstage;

/**
 * Separates two consecutive entries of the {@link IppForm} with a blank, empty line.
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
public class IppSpacer extends XmlFormItem {
	
	/**
	 * As of September 2011, unused.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum NewLine {
		/**
		 * Not used.
		 */
		NEWLINE_AFTER,
		/**
		 * Not used.
		 */
		NEWLINE_BEFORE,
		/**
		 * Not used.
		 */
		NEWLINE_BEF_AFT;
	}
	
	/**
	 * Not used.
	 */
	private NewLine newLine = null;
	
	/**
	 * Creates new IppSpacer.
	 */
	public IppSpacer() {
		super("IppSpacer");
	}
	
	/**
	 * Creates new IppSpacer.
	 * 
	 * @param newLine
	 *           not used as of September 2011, may be null, no value has any impact on
	 *           this object's behaviour
	 */
	public IppSpacer(NewLine newLine) {
		super("IppSpacer");
		this.newLine = newLine;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (newLine != null)
			sb.append(attributeToXml("NewLine", newLine));
		return sb;
	}
	
}
