package pl.mbdev.openstage;

/**
 * Element that allows direct input of the pressed keys values to the remote server.
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
public class IppKey extends Xml {
	
	/**
	 * Keypad key presses may be ignored or not, while {@link IppKey} element is present on
	 * the screen.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Keypad {
		/**
		 * Keypad key presses are not ignored.
		 */
		YES,
		/**
		 * Keypad key presses are ignored.
		 */
		NO;
	}
	
	/**
	 * The input from {@link IppKey} element may be sent to the remote server or not.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum SendKeys {
		/**
		 * The keys are sent.
		 */
		YES,
		/**
		 * The keys are not sent.
		 */
		NO;
	}
	
	/**
	 * Setting of buffering of input of the {@link IppKey} element.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum BufferKeys {
		/**
		 * The keys are buffered until the buffer is filled. The size of the buffer can be
		 * set in a {@link IppKey} constructor.
		 */
		YES,
		/**
		 * The keys are not buffered.
		 */
		NO,
		/**
		 * The keys are buffered, with the exception of the first key, which is sent to the
		 * server immediately.
		 */
		SUBSEQUENT;
	}
	
	/**
	 * If true, the key presses of keypad are handled by the IppKey element.
	 */
	private final boolean keypad;
	
	/**
	 * Will the keys be sent to the server.
	 */
	private final boolean sendKeys;
	
	/**
	 * Current buffering mode, value from {@link IppKey.BufferKeys}.
	 */
	private final BufferKeys bufferKeys;
	
	/**
	 * Length of the buffer of characters.
	 */
	private final int bufferLength;
	
	/**
	 * Character, which will terminate the input when it is pressed. Relevant only when the
	 * keys are buffered. If null, the keys are submitted after the buffer is filled.<br />
	 * <br />
	 * When there is no buffer, the pressed key data is transmitted immediately after the
	 * first key is pressed.
	 */
	private Character termKey = null;
	
	/**
	 * Key from the key-value pair, where the value is the set of digits, or one digit if
	 * the buffering is turned off. Default key is digit, if it is not set.
	 */
	private String urlKey = "digit";
	
	/**
	 * Creates a default IppKey element, which disables
	 */
	public IppKey() {
		this(false, false, BufferKeys.NO, 0, null, null);
	}
	
	/**
	 * Creates an IppKey element that enables buffered input and has custom key.
	 * 
	 * @param bufferLength
	 *           length of the buffer of characters
	 * @param termKey
	 *           character, which will terminate the input when it is pressed
	 * @param urlKey
	 *           key from the key-value pair, where the value is the set of digits, default
	 *           key is 'digit', if this parameter is null
	 */
	public IppKey(int bufferLength, Character termKey, String urlKey) {
		this(true, true, BufferKeys.YES, bufferLength, termKey, urlKey);
	}
	
	/**
	 * Creates an IppKey element that enables not buffered input and has custom key.
	 * 
	 * @param urlKey
	 *           key from the key-value pair, where the value is one digit because the
	 *           buffering is turned off; default key is digit, if this parameter is null
	 */
	public IppKey(String urlKey) {
		this(true, true, BufferKeys.NO, 0, null, urlKey);
	}
	
	/**
	 * Constructs new IppKey element with all parameters available.
	 * 
	 * @param keypad
	 *           if true, the key presses of keypad are handled by the IppKey element
	 * @param sendKeys
	 *           will the keys be sent to the server
	 * @param bufferKeys
	 *           buffering mode, value from {@link IppKey.BufferKeys}
	 * @param bufferLength
	 *           length of the buffer of characters
	 * @param termKey
	 *           character, which will terminate the input when it is pressed
	 * @param urlKey
	 *           key from the key-value pair, where the value is the set of digits, or one
	 *           digit if the buffering is turned off; default key is digit, if this
	 *           parameter is null
	 */
	public IppKey(boolean keypad, boolean sendKeys, BufferKeys bufferKeys,
			int bufferLength, Character termKey, String urlKey) {
		super("IppKey");
		this.keypad = keypad;
		this.sendKeys = sendKeys;
		this.bufferKeys = bufferKeys;
		this.bufferLength = bufferLength;
		this.termKey = termKey;
		if (urlKey != null)
			this.urlKey = urlKey;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Keypad", keypad));
		sb.append(attributeToXml("SendKeys", sendKeys));
		sb.append(attributeToXml("BufferKeys", bufferKeys));
		sb.append(attributeToXml("BufferLength", bufferLength));
		if (termKey != null)
			sb.append(attributeToXml("TermKey", termKey));
		sb.append(attributeToXml("UrlKey", urlKey));
		return sb;
	}
}
