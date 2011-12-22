package pl.mbdev.openstage;

/**
 * Entity used to perform an action on OpenStage phone. This can mean: making a call,
 * ending a call, turning a designated LED on or turning it off.
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
public class IppAction extends Xml {
	
	/**
	 * Defines types of {@link IppAction}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Type {
		/**
		 * Make a call.
		 */
		MAKECALL,
		/**
		 * End a call.
		 */
		ENDCALL,
		/**
		 * Turn the LED on.
		 */
		TURNLEDON,
		/**
		 * Turn the LED off.
		 */
		TURNLEDOFF;
	}
	
	/**
	 * Stores the type of this action.
	 */
	private Type type;
	
	/**
	 * Creates IppAction with all parameters available to setup.
	 * 
	 * @param type
	 *           type of the action
	 * @param phoneNumber
	 *           phone number associated with the action
	 */
	public IppAction(Type type, String phoneNumber) {
		super("IppAction");
		
		this.type = type;
		if (this.type.equals(Type.MAKECALL)) {
			if (phoneNumber == null)
				throw new NullPointerException(
						"phone number must be not null when IppAction is of type MAKECALL");
			this.add("Number", phoneNumber);
		}
	}
	
	/**
	 * Creates an action of a given type, without any phone number associated with it.
	 * 
	 * @param type
	 *           type of the action
	 */
	public IppAction(Type type) {
		this(type, null);
	}
	
	/**
	 * Creates an action to call a given phone number.
	 * 
	 * @param phoneNumber
	 *           phone number associated with the action
	 */
	public IppAction(String phoneNumber) {
		this(Type.MAKECALL, phoneNumber);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (type != null)
			sb.append(attributeToXml("Type", type));
		return sb;
	}
	
}
