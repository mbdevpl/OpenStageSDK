package pl.mbdev.openstage;

/**
 * Abstract parent of all form items, all of which can be wrapped with {@link IppItem} to
 * associate command with them. Children of this class are: {@link IppStringItem},
 * {@link IppImageItem}, {@link IppSpacer}, {@link IppTextField}, {@link IppChoiceGroup},
 * {@link IppDateField}, {@link IppButton} or {@link IppGauge}
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
public abstract class XmlFormItem extends Xml {
	
	/**
	 * Default constructor. Creates new node without sub-nodes, and without any text
	 * contents.
	 * 
	 * @param nodeName
	 *           name of this XML node
	 */
	public XmlFormItem(String nodeName) {
		super(nodeName);
	}
	
	/**
	 * Creates new node, without sub-nodes but with text contents.
	 * 
	 * @param nodeName
	 *           name of the XML node
	 * @param contents
	 *           contents of the XML entity
	 */
	public XmlFormItem(String nodeName, Object contents) {
		super(nodeName, contents);
	}
	
	/**
	 * Associates the provided {@link IppCommand} with this object, by automatically
	 * wrapping this object with {@link IppItem} if it was not wrapped up already, and then
	 * adding the command to the IppItem.
	 * 
	 * @param c
	 *           command, IppCommand
	 */
	public void add(IppCommand c) {
		this.wrapWithIppItem();
		this.logicalAdd(c);
	}
	
}
