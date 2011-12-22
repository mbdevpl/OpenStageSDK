package pl.mbdev.openstage;

/**
 * Allows user's interaction with many aspects of the OpenStage phone. Commands can change
 * behaviour of the current XML application, launch another, and enable communication with
 * any remote server.
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
public class IppCommand extends Xml {
	
	/**
	 * Available types of the {@link IppCommand}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * Used to send data to the remote server. Data and the address of the server are
		 * defined in the {@link IppForm} from which the command is issued.
		 */
		SELECT,
		/**
		 * 
		 */
		BACK,
		/**
		 * 
		 */
		UPDATE,
		/**
		 * Used to change active screen, useful when there is more than one screen in our
		 * XML application.
		 */
		SCREEN,
		/**
		 * 
		 */
		CANCEL,
		/**
		 * Used to quit the application.
		 */
		EXIT;
	}
	
	/**
	 * Defines places where the {@link IppCommand} will be displayed. In some cases, the
	 * context in which the command is used may forbid some options. Read the documentation
	 * of entities to which you want to add the command to know more.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum DisplayOn {
		/**
		 * Command will be displayed
		 */
		OPTIONS,
		/**
		 * Displays command in a command list attached to a specific item. Useful when we
		 * add {@link IppCommand} to an {@link IppItem}.
		 */
		LISTITEM,
		/**
		 * Displays command in both global options list and specific item list. In other
		 * words, BOTH = LISTITEM and OPTIONS.
		 */
		BOTH;
	}
	
	/**
	 * For commands added to implicit {@link IppList} Possible valued of a field that
	 * indicates whether the command is selected by default or not. Meaningless otherwise.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Select {
		/**
		 * It is.
		 */
		YES,
		/**
		 * It is not.
		 */
		NO;
	}
	
	/**
	 * Possible values of a field that indicates whether this command will be a default
	 * command in the context menu.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Default {
		/**
		 * It will be default, unless there already is a default command.
		 */
		YES,
		/**
		 * It will not be default.
		 */
		NO;
	}
	
	/**
	 * Value from {@link IppCommand.Type}.
	 */
	private Type type = Type.SELECT;
	
	/**
	 * Nonnegative integer indicating position of this IppCommand on the list of commands.
	 * If negative, not used.
	 */
	private int priority = -1;
	
	/**
	 * This command will be executed automatically after provided number of seconds, or
	 * will not be executed automatically if zero or a negative number is provided.
	 */
	private int auto = 0;
	
	/**
	 * Useless unless command is of type SELECT, then it is a key from key-value pair,
	 * which is sent to the remote server.
	 */
	private String key = null;
	
	/**
	 * Useless unless command is of type SELECT, then it is the value from key-value pair,
	 * which is sent to the remote server.
	 */
	private String value = null;
	
	/**
	 * Value from {@link IppCommand.DisplayOn}.
	 */
	private DisplayOn displayOn = DisplayOn.OPTIONS;
	
	/**
	 * Useful only if the command is added to a implicit list.
	 */
	private boolean isSelected = false;
	
	/**
	 * This command will be the default command in the context menu.
	 */
	private boolean isDefault = false;
	
	/**
	 * Creates new IppCommand, with label that is guessed according to the type argument.
	 * This constructor works only for the following command types: EXIT, BACK and CANCEL.<br />
	 * <br />
	 * type -> label:<br />
	 * EXIT -> "Exit", BACK -> "Back", CANCEL -> "Cancel"
	 * 
	 * @param type
	 *           value from {@link IppCommand.Type}
	 * @param displayOn
	 *           value from {@link IppCommand.DisplayOn}
	 */
	public IppCommand(Type type, DisplayOn displayOn) {
		super("IppCommand");
		
		this.type = type;
		this.displayOn = displayOn;
		
		String label = "";
		if (type.equals(Type.EXIT))
			label = "Exit";
		else if (type.equals(Type.BACK))
			label = "Back";
		else if (type.equals(Type.CANCEL))
			label = "Cancel";
		else
			throw new IllegalArgumentException(
					"this IppCommand constructor works only for types: EXIT, BACK and CANCEL");
		
		this.add("Label", label);
	}
	
	/**
	 * Creates new IppCommand of type SCREEN.
	 * 
	 * @param label
	 *           label of this command
	 * @param screenID
	 *           ID of the screen
	 * @param displayOn
	 *           value from {@link IppCommand.DisplayOn}
	 */
	public IppCommand(String label, int screenID, DisplayOn displayOn) {
		this(label, Type.SCREEN, screenID, -1, 0, null, null, displayOn, false, false);
	}
	
	/**
	 * Creates new IppCommand of type SELECT.
	 * 
	 * @param label
	 *           label of this command
	 * @param key
	 *           the key from key-value pair, which is sent to the remote server
	 * @param value
	 *           the value from key-value pair, which is sent to the remote server
	 * @param displayOn
	 *           value from {@link IppCommand.DisplayOn}
	 */
	public IppCommand(String label, String key, String value, DisplayOn displayOn) {
		this(label, Type.SELECT, -1, -1, 0, key, value, displayOn, false, false);
	}
	
	/**
	 * Creates new IppCommand with some fields set to default values.
	 * 
	 * @param label
	 *           label of this command
	 * @param type
	 *           value from {@link IppCommand.Type}, if you want to set it to SCREEN,
	 *           please use a constructor that allows you to also define screen ID
	 * @param key
	 *           useless unless command is of type SELECT, then it is a key from key-value
	 *           pair, which is sent to the remote server
	 * @param value
	 *           useless unless command is of type SELECT, then it is the value from
	 *           key-value pair, which is sent to the remote server
	 * @param displayOn
	 *           value from {@link IppCommand.DisplayOn}
	 * @param isSelected
	 *           useful only if the command is added to a implicit list
	 */
	public IppCommand(String label, Type type, String key, String value,
			DisplayOn displayOn, boolean isSelected) {
		this(label, type, -1, -1, 0, key, value, displayOn, isSelected, false);
	}
	
	/**
	 * Creates new IppCommand with all possible fields available to set.
	 * 
	 * @param label
	 *           label of this command
	 * @param type
	 *           value from {@link IppCommand.Type}
	 * @param screenID
	 *           ID of the screen, used when the command is of type SCREEN
	 * @param priority
	 *           priority of the command, may change order of appearance to other than
	 *           "first added, first displayed"
	 * @param auto
	 *           this command will be executed automatically after provided number of
	 *           seconds, or will not be executed automatically if zero or a negative
	 *           number is provided
	 * @param key
	 *           useless unless command is of type SELECT, then it is a key from key-value
	 *           pair, which is sent to the remote server
	 * @param value
	 *           useless unless command is of type SELECT, then it is the value from
	 *           key-value pair, which is sent to the remote server
	 * @param displayOn
	 *           value from {@link IppCommand.DisplayOn}
	 * @param isSelected
	 *           useful only if the command is added to a implicit list
	 * @param isDefault
	 *           this command will be the default command in the context menu
	 */
	public IppCommand(String label, Type type, int screenID, int priority, int auto,
			String key, String value, DisplayOn displayOn, boolean isSelected,
			boolean isDefault) {
		super("IppCommand");
		
		this.type = type;
		this.priority = priority;
		this.auto = auto;
		this.key = key;
		this.value = value;
		this.displayOn = displayOn;
		this.isSelected = isSelected;
		this.isDefault = isDefault;
		
		this.add("Label", label);
		if (type.equals(Type.SCREEN))
			this.add("ScreenID", screenID);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Type", type));
		if (priority >= 0)
			sb.append(attributeToXml("Priority", priority));
		if (auto > 0)
			sb.append(attributeToXml("Auto", auto));
		if (type.equals(Type.SELECT) && (key != null || value != null)) {
			if (key == null)
				key = "key";
			sb.append(attributeToXml("Key", key));
			if (value == null)
				value = "";
			sb.append(attributeToXml("Value", value));
		}
		sb.append(attributeToXml("DisplayOn", displayOn));
		if (isSelected)
			sb.append(attributeToXml("Select", isSelected ? Select.YES : Select.NO));
		if (isDefault)
			sb.append(attributeToXml("Default", isDefault ? Default.YES : Default.NO));
		return sb;
	}
}
