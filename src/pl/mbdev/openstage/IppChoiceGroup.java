package pl.mbdev.openstage;

/**
 * Choice element, which can be added to {@link IppForm}, and is similar to the
 * {@link IppList}.
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
public class IppChoiceGroup extends XmlFormItem {
	
	/**
	 * Defines possible types of the {@link IppChoiceGroup}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * The exclusive choice group, only one option can be selected.
		 */
		EXCLUSIVE,
		/**
		 * The multiple choice group, multiple options may be selected at the same time.
		 */
		MULTIPLE,
		/**
		 * The choice group acts like an exclusive choice group, but is presented
		 * differently. It appears as a pop-up with initially only the selected option
		 * visible.
		 */
		POPUP;
	}
	
	/**
	 * Number of options present in this choice group.
	 */
	private int optionCount = 0;
	
	/**
	 * Type of the choice group, value from {@link IppChoiceGroup.Type}.
	 */
	private Type type = Type.EXCLUSIVE;
	
	/**
	 * Creates an exclusive, initially empty choice group.
	 * 
	 * @param label
	 *           label of this choice group
	 */
	public IppChoiceGroup(String label) {
		this(label, Type.EXCLUSIVE);
	}
	
	/**
	 * @param label
	 *           label of this choice group
	 * @param type
	 *           type of the choice group, value from {@link IppChoiceGroup.Type}
	 */
	public IppChoiceGroup(String label, Type type) {
		this(label, type, null);
	}
	
	/**
	 * @param label
	 *           label of this choice group
	 * @param type
	 *           type of the choice group, value from {@link IppChoiceGroup.Type}
	 * @param options
	 *           array of initial options
	 */
	public IppChoiceGroup(String label, Type type, Option[] options) {
		super("IppChoiceGroup");
		if (type == null)
			throw new NullPointerException("type of the IppChoiceGroup cannot be null");
		this.type = type;
		
		add("Label", label);
		if (options != null)
			for (Option o : options)
				this.add(o);
	}
	
	/**
	 * Adds an option to this choice group. The option must have a single OptionText
	 * element.
	 * 
	 * @param o
	 *           option with a single {@link OptionText} element.
	 */
	public void add(Option o) {
		super.add(o);
		optionCount++;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (type != null)
			sb.append(attributeToXml("Type", type));
		sb.append(attributeToXml("Count", optionCount));
		return sb;
	}
	
}
