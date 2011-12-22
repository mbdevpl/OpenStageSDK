package pl.mbdev.openstage;

/**
 * Enables translation of a phone number stored in a local phone book of OpenStage. This
 * element is used as a child element of {@link IppAlert} and {@link IppList}.
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
public class IppPhoneNumber extends Xml {
	
	/**
	 * Defines possible types of images attached to a {@link IppPhoneNumber}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum ImageType {
		/**
		 * The icon of the kind of the phone number (for example mobile number) is
		 * displayed.
		 */
		PHONETYPE,
		/**
		 * The picture clip of the participant is displayed, if it was set.
		 */
		PICTURECLIP;
	}
	
	/**
	 * Defines possible options of how to display a number attached to a
	 * {@link IppPhoneNumber}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum NumberType {
		/**
		 * Only the number is displayed.
		 */
		NUMBER,
		/**
		 * Only the name of the contact is displayed.
		 */
		NAME,
		/**
		 * Name and the number of the contact are displayed.
		 */
		BOTH;
	}
	
	/**
	 * Type of the image attached to a IppPhoneNumber, value from
	 * {@link IppPhoneNumber.ImageType}.
	 */
	private final ImageType imageType;
	
	/**
	 * How to display a number attached to a IppPhoneNumber, value from
	 * {@link IppPhoneNumber.NumberType}.
	 */
	private final NumberType numberType;
	
	/**
	 * Creates new object that displays a phone number from a
	 * 
	 * @param altText
	 *           alternate text
	 * @param imageType
	 *           type of the image attached to a IppPhoneNumber, value from
	 *           {@link IppPhoneNumber.ImageType}
	 * @param numberType
	 *           how to display a number attached to a IppPhoneNumber, value from
	 *           {@link IppPhoneNumber.NumberType}
	 */
	public IppPhoneNumber(String altText, ImageType imageType, NumberType numberType) {
		super("IppPhoneNumber");
		
		this.imageType = imageType;
		this.numberType = numberType;
		
		add("AltText", altText);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("ImageType", imageType));
		sb.append(attributeToXml("NumberType", numberType));
		return sb;
	}
}
