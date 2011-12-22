package pl.mbdev.openstage;

import java.util.Calendar;

/**
 * Defines the date and time formats used by the OpenStage phones.
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
public abstract class XmlDate {
	
	/**
	 * Private constructor, this is an utility class.
	 */
	private XmlDate() {
	}
	
	/**
	 * From the calendar object provided, gets the date in the OpenStage format.
	 * 
	 * @param cal
	 *           a Calendar object
	 * @return date in YYYY-MM-DD format
	 */
	public static String getDate(Calendar cal) {
		return new StringBuffer(cal.get(Calendar.YEAR)).append("-")
				.append(cal.get(Calendar.MONTH)).append("-")
				.append(cal.get(Calendar.DAY_OF_MONTH)).toString();
	}
	
	/**
	 * From the calendar object provided, gets the time in the OpenStage format.
	 * 
	 * @param cal
	 *           a Calendar object
	 * @return time in HH:MM:SS.mmm format
	 */
	public static String getTime(Calendar cal) {
		return new StringBuffer(cal.get(Calendar.HOUR)).append(":")
				.append(cal.get(Calendar.MINUTE)).append(":")
				.append(cal.get(Calendar.SECOND)).append(".")
				.append(cal.get(Calendar.MILLISECOND)).toString();
	}
	
}
