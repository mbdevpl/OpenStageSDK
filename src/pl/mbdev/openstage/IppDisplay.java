package pl.mbdev.openstage;

/**
 * Definition of IppDisplay, required sub-node of {@link IppPhone}. It may also be used as
 * a optional root according to the programmer's guide, but it is highly recommended to
 * use {@link IppPhone} as a root. This SDK will automatically wrap the IppDisplay with
 * {@link IppPhone} if the attempt to send IppDisplay shall occur - via sendTo() method
 * inherited from {@link Xml}.
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
public class IppDisplay extends Xml {
	
	/**
	 * ID of the initial {@link IppScreen} in this IppDisplay.
	 */
	private Integer initialScreen;
	
	/**
	 * Optional ID of another screen that will be updated in the background.
	 */
	private int updateScreen = 0;
	
	/**
	 * Creates new, empty IppDisplay object.
	 * 
	 * @param initialScreen
	 *           ID of the initial {@link IppScreen} in this IppDisplay
	 * @param updateScreen
	 *           Optional ID of another screen that will be updated in the background
	 */
	public IppDisplay(Integer initialScreen, int updateScreen) {
		super("IppDisplay");
		this.initialScreen = initialScreen;
		this.updateScreen = updateScreen;
	}
	
	/**
	 * Adds the provided {@link IppScreen} to this IppDisplay
	 * 
	 * @param s
	 *           screen, IppScreen
	 */
	public void add(IppScreen s) {
		if (this.subObjectsCount() < 5)
			super.add(s);
		else
			throw new RuntimeException(
					"you cannot add more than five IppScreens to IppDisplay");
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (initialScreen != null)
			sb.append(attributeToXml("InitialScreen", initialScreen));
		if (updateScreen > 0)
			sb.append(attributeToXml("UpdateScreen", updateScreen));
		return sb;
	}
	
}
