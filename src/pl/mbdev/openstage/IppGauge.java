package pl.mbdev.openstage;

/**
 * Automated or user editable gauge, which can be added to {@link IppForm}.
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
public class IppGauge extends XmlFormItem {
	
	/**
	 * Defines types of {@link IppGauge} in the context of it being interactive.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Interactive {
		/**
		 * The value of the gauge is changeable by the user.
		 */
		USER,
		/**
		 * The gauge is automated, and progress is not controlled by the user but happens
		 * automatically with time.
		 */
		AUTO;
	}
	
	/**
	 * Value from {@link IppGauge.Interactive}, sets the gauge's interactivity.
	 */
	private final Interactive interactive;
	
	/**
	 * Key from the key-value pair, where the value is the progress of the scroll bar.
	 */
	private final String key;
	
	/**
	 * Creates an automated gauge, with start and end time.
	 * 
	 * @param label
	 *           label of the gauge
	 * @param maximumMinutes
	 *           minutes part of the maximum value
	 * @param maximumSeconds
	 *           seconds part of the maximum value
	 * @param initialMinutes
	 *           minutes part of the minimum value
	 * @param initialSeconds
	 *           seconds part of the minimum value
	 */
	public IppGauge(String label, int maximumMinutes, int maximumSeconds,
			int initialMinutes, int initialSeconds) {
		this(label, String.valueOf(maximumMinutes).concat(":")
				.concat(String.valueOf(maximumSeconds)), String.valueOf(initialMinutes)
				.concat(":").concat(String.valueOf(initialSeconds)), Interactive.AUTO, null);
	}
	
	/**
	 * Creates an interactive gauge, with initial and maximum values.
	 * 
	 * @param label
	 *           label of the gauge
	 * @param maximum
	 *           maximum value of the gauge
	 * @param initial
	 *           initial value
	 * @param key
	 *           key from the key-value pair, where the value is the progress of the scroll
	 *           bar
	 */
	public IppGauge(String label, int maximum, int initial, String key) {
		this(label, String.valueOf(maximum), String.valueOf(initial), Interactive.USER, key);
	}
	
	/**
	 * Creates
	 * 
	 * @param label
	 *           label of the gauge
	 * @param maximum
	 *           maximum value
	 * @param initial
	 *           initial value
	 * @param interactive
	 *           value from {@link IppGauge.Interactive}, sets the gauge's interactivity
	 * @param key
	 *           key from the key-value pair, where the value is the progress of the scroll
	 *           bar, ignored when the gauge is not interactive
	 */
	public IppGauge(String label, String maximum, String initial, Interactive interactive,
			String key) {
		super("IppGauge");
		this.interactive = interactive;
		if (key == null)
			if (interactive.equals(Interactive.AUTO))
				key = "key";
			else
				throw new NullPointerException(
						"'Key' attributte of IppGauge cannot be null, "
								+ "when 'Interactive' attribute is set to USER");
		this.key = key;
		
		add("Label", label);
		add("Maximum", maximum);
		add("Initial", initial);
	}
	
	/**
	 * Returns the value from {@link IppGauge.Interactive}, the gauge's interactivity.
	 * 
	 * @return the gauge's interactivity, value from {@link IppGauge.Interactive}
	 */
	public Interactive getInteractive() {
		return interactive;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Interactive", interactive));
		sb.append(attributeToXml("Key", key));
		return sb;
	}
	
}
