package pl.mbdev.openstage;

/**
 * A superclass of various objects that have a key attribute.
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
public abstract class XmlWithKey extends Xml {
	
	/**
	 * Name of the key, from key-value pair of various objects.
	 */
	private final String key;
	
	/**
	 * Creates new XML object with key.
	 * 
	 * @param nodeName
	 *           name of the XML node
	 * @param key
	 *           name of the key, from key-value pair of various objects
	 */
	public XmlWithKey(String nodeName, String key) {
		super(nodeName);
		this.key = key;
	}
	
	/**
	 * Creates new XML object with key.
	 * 
	 * @param nodeName
	 *           name of the XML node
	 * @param key
	 *           name of the key, from key-value pair of various objects
	 * @param contents
	 *           contents of this node
	 */
	public XmlWithKey(String nodeName, String key, Object contents) {
		super(nodeName, contents);
		this.key = key;
	}
	
	protected StringBuffer firstAttributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Key", key));
		return sb;
	}
	
}
