package pl.mbdev.openstage;

/**
 * Used to attach {@link IppCommand}s to single entries of the {@link IppForm}. This is
 * achieved by adding one or multiple commands, as well as a single form item to the
 * IppItem, and then adding such IppItem to {@link IppForm}.<br/>
 * <br/>
 * You can add commands directly to {@link IppStringItem}, {@link IppImageItem},
 * {@link IppTextField} and other items, to which you do cannot normally add them, and
 * they will be automatically wrapped in {@link IppItem}.
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
public class IppItem extends Xml {
	
	/**
	 * True if this IppItem already has an attached item.
	 */
	private boolean hasItem = false;
	
	/**
	 * Number of commands (see {@link IppCommand}) present in this IppItem.
	 */
	private int commandCount = 0;
	
	/**
	 * Constructs new, empty IppItem.
	 */
	public IppItem() {
		super("IppItem");
	}
	
	/**
	 * Attaches the provided IppStringItem to this IppItem.
	 * 
	 * @param si
	 *           string item, IppStringItem
	 */
	public void add(IppStringItem si) {
		this.add((Xml) si);
	}
	
	/**
	 * Attaches the provided {@link IppImageItem} to this IppItem.
	 * 
	 * @param ii
	 *           image item IppImageItem
	 */
	public void add(IppImageItem ii) {
		this.add((Xml) ii);
	}
	
	/**
	 * Attaches the provided IppSpacer to this IppItem.
	 * 
	 * @param s
	 *           spacer, IppSpacer
	 */
	public void add(IppSpacer s) {
		this.add((Xml) s);
	}
	
	/**
	 * Attaches the provided IppTextField to this IppItem.
	 * 
	 * @param t
	 *           text field, IppTextField
	 */
	public void add(IppTextField t) {
		this.add((Xml) t);
	}
	
	/**
	 * Attaches the provided IppChoiceGroup to this IppItem.
	 * 
	 * @param cg
	 *           choice group, IppChoiceGroup
	 */
	public void add(IppChoiceGroup cg) {
		this.add((Xml) cg);
	}
	
	/**
	 * Attaches the provided IppDateField to this IppItem.
	 * 
	 * @param d
	 *           date field, IppDateField
	 */
	public void add(IppDateField d) {
		this.add((Xml) d);
	}
	
	/**
	 * Attaches the provided IppButton to this IppItem.
	 * 
	 * @param b
	 *           button, IppButton
	 */
	public void add(IppButton b) {
		this.add((Xml) b);
	}
	
	/**
	 * Attaches the provided IppGauge to this IppItem.
	 * 
	 * @param g
	 *           gauge, IppGauge
	 */
	public void add(IppGauge g) {
		this.add((Xml) g);
	}
	
	protected Xml add(Xml o) {
		if (o instanceof IppCommand) {
			this.add((IppCommand) o);
			return o;
		}
		if (hasItem)
			throw new IllegalArgumentException(
					"IppItem can have multiple commands, but only one item");
		Xml i = super.add(o);
		hasItem = true;
		return i;
	}
	
	/**
	 * Adds IppCommand to this IppItem.
	 * 
	 * @param c
	 *           command
	 */
	public void add(IppCommand c) {
		super.add(c);
		commandCount++;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("CommandCount", commandCount));
		return sb;
	}
	
}
