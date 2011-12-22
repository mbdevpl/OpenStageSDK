package pl.mbdev.openstage;

/**
 * Used to create lists of elements, one or more of which then can be selected, according
 * to the list type.
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
public class IppList extends Xml {
	
	/**
	 * Defines possible types of {@link IppList}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * Only one option can be selected, and the selected option's key-value pair is sent
		 * immediately after its selection.
		 */
		IMPLICIT,
		/**
		 * Only one option can be selected, but selection does not cause the data transfer.
		 * Separate {@link IppCommand} is needed to send the data.
		 */
		EXCLUSIVE,
		/**
		 * More than one option may be selected, and after the form is submitted all
		 * key-value pairs of selected items are sent to the server. One needs an
		 * {@link IppCommand} to send the data.
		 */
		MULTIPLE;
	}
	
	/**
	 * Type of the list, value from {@link IppList.Type}.
	 */
	private Type type = Type.IMPLICIT;
	
	/**
	 * Number of options present in this list.
	 */
	private int optionsCount;
	
	/**
	 * Number of columns of this list.
	 */
	private int columns;
	
	/**
	 * Creates a new, empty IppList.
	 * 
	 * @param title
	 *           title of the list
	 * @param url
	 *           URL, to which the selected data of this list will be sent after it is
	 *           submitted
	 * @param type
	 *           type of the IppList, value from {@link IppList.Type}
	 * @param columns
	 *           integer between 1 and 3
	 */
	public IppList(String title, String url, Type type, int columns) {
		this(title, url, type, columns, null);
	}
	
	/**
	 * Creates a new list with all parameters available to set.
	 * 
	 * @param title
	 *           title of the list
	 * @param url
	 *           URL, to which the selected data of this list will be sent after it is
	 *           submitted
	 * @param type
	 *           type of the IppList, value from {@link IppList.Type}
	 * @param columns
	 *           integer between 1 and 3
	 * @param options
	 *           array that holds list of {@link Option}s to be added to this list
	 */
	public IppList(String title, String url, Type type, int columns, Option[] options) {
		super("IppList");
		if (type == null)
			throw new NullPointerException("type of the IppList cannot be null");
		this.type = type;
		if (columns < 1 || columns > 3)
			throw new IllegalArgumentException(
					"number of columns of IppList must be 1, 2 or 3");
		
		add("Title", title);
		add("Url", url);
		
		if (options != null)
			for (Option o : options)
				this.add(o);
	}
	
	/**
	 * Adds an option to this IppList. The number of {@link OptionText} elements inside the
	 * {@link Option}, must be equal to the number of columns of the list.
	 * 
	 * @param o
	 *           option, an object of type {@link Option}
	 */
	public void add(Option o) {
		if (o.getOptionTextCount() != this.columns)
			throw new IllegalArgumentException("The number of OptionText objects "
					+ "inside the option to be added is different than the number of columns");
		super.add(o);
		optionsCount++;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Type", type));
		sb.append(attributeToXml("Count", optionsCount));
		sb.append(attributeToXml("Columns", columns));
		return sb;
	}
	
}
