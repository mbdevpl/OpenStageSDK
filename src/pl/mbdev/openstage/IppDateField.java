package pl.mbdev.openstage;

import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A field with a date and time that can be added to {@link IppForm}. Date and time from
 * such field will be sent to a remote server when a form is submitted.
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
public class IppDateField extends XmlFormItem {
	
	/**
	 * Defines possible modes of an {@link IppDateField}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Mode {
		/**
		 * The field is used to enter a date.
		 */
		DATE,
		/**
		 * The field is used to enter the time, without date.
		 */
		TIME,
		/**
		 * The field is used to enter both date and time.
		 */
		DATETIME;
	}
	
	/**
	 * Defines the possible kinds of default values of the {@link IppDateField}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Default {
		/**
		 * The field is by default empty.
		 */
		NULL,
		/**
		 * The field will have a default value provided in date and time parameters, and
		 * part of the data visible will be set according to mode parameter.
		 */
		MODE;
	}
	
	/**
	 * Mode of this date field, value comes from {@link IppDateField.Mode}.
	 */
	private final Mode mode;
	
	/**
	 * Kind of the default value, from {@link IppDateField.Mode}.
	 */
	private final Default defaultValue;
	
	/**
	 * Key that will identify date from this field when the form is submitted to a remote
	 * server.
	 */
	private final String dateKey;
	
	/**
	 * Key that will identify time from this field when the form is submitted to a remote
	 * server.
	 */
	private final String timeKey;
	
	/**
	 * Creates new date field that will have a default date and time provided in a dateTime
	 * parameter.
	 * 
	 * @param label
	 *           label of this date field
	 * @param dateTime
	 *           date and time that will be set as a default value
	 * @param mode
	 *           mode of this date field, value comes from {@link IppDateField.Mode}
	 * @param dateKey
	 *           key that will identify date from this field when the form is submitted to
	 *           a remote server
	 * @param timeKey
	 *           key that will identify time from this field when the form is submitted to
	 *           a remote server
	 */
	public IppDateField(String label, GregorianCalendar dateTime, Mode mode,
			String dateKey, String timeKey) {
		this(label, dateTime.getTimeZone(), XmlDate.getDate(dateTime), XmlDate
				.getTime(dateTime), mode, Default.MODE, dateKey, timeKey);
	}
	
	/**
	 * @param label
	 *           label of this date field
	 * @param timeZone
	 *           any time zone available in Java
	 * @param date
	 *           string representing a date, in YYYY-MM-DD format
	 * @param time
	 *           string representing the time, in HH:MM:SS.mmm format
	 * @param mode
	 *           mode of this date field, value comes from {@link IppDateField.Mode}
	 * @param defaultValue
	 *           kind of the default value
	 * @param dateKey
	 *           key that will identify date from this field when the form is submitted to
	 *           a remote server
	 * @param timeKey
	 *           key that will identify time from this field when the form is submitted to
	 *           a remote server
	 */
	public IppDateField(String label, TimeZone timeZone, String date, String time,
			Mode mode, Default defaultValue, String dateKey, String timeKey) {
		super("IppDateField");
		this.mode = mode;
		this.defaultValue = defaultValue;
		this.dateKey = dateKey;
		this.timeKey = timeKey;
		
		add("Label", label);
		add("TimeZone", timeZone.getDisplayName());
		add("Date", date);
		add("Time", time);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Mode", mode));
		sb.append(attributeToXml("Default", defaultValue));
		sb.append(attributeToXml("DateKey", dateKey));
		sb.append(attributeToXml("TimeKey", timeKey));
		return sb;
	}
}
