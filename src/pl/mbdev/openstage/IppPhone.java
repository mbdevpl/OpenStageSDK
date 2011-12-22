package pl.mbdev.openstage;

/**
 * Recommended, and one of two possible root nodes of a XML application. Only one IppPhone
 * may be present in an application.
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
public class IppPhone extends Xml {
	
	/**
	 * Creates new, empty IppPhone object.
	 */
	public IppPhone() {
		super("IppPhone");
	}
	
	/**
	 * Adds the provided IppDisplay to this IppPhone. Only one IppDisplay can be present in
	 * a XML application.
	 * 
	 * @param d
	 *           display, IppDisplay
	 */
	public void add(IppDisplay d) {
		if (this.subObjectsCount() < 1)
			super.add(d);
		else
			throw new RuntimeException("you cannot add more than one IppDisplay to IppPhone");
	}
	
}
