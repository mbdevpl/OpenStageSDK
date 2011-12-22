package pl.mbdev.openstage;

/**
 * Sub-node of {@link IppDisplay} object. The IppDisplay may contain up to 5 IppScreens.
 * IppScreen cannot be a root node, but this SDK will wrap IppScreen with {@link IppPhone}
 * and IppDisplay if the attempt to send only IppScreen is made.<br />
 * <br />
 * Also please notice that IppScreen must have one of the sub-nodes provided below, and
 * can have only one of them: {@link IppAlert}, {@link IppList}, {@link IppTextBox},
 * {@link IppForm} or {@link IppPlayer}. <br />
 * <br />
 * There are no restrictions on other elements. This SDK will automatically prevent adding
 * of too many objects to the screen and will inform about possible errors.
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
public class IppScreen extends Xml {
	
	/**
	 * ID of this screen.
	 */
	private Integer id;
	
	/**
	 * Number of hidden fields currently present in this screen.
	 */
	private int hiddenCount = 0;
	
	/**
	 * Number of commands currently present in this screen. Only commands added directly to
	 * the screen are counted here.
	 */
	private int commandCount = 0;
	
	/**
	 * Indicates that this IppScreen has as a child an element which prevents other unique
	 * elements to be added.
	 */
	private boolean hasUniqueElement = false;
	
	/**
	 * Index of the unique element on the list of sub-nodes.
	 */
	private int uniqueIndex = -1;
	
	/**
	 * Creates a new IppScreen. Bear in mind that {@link IppDisplay} can have up to five
	 * IppScreens.
	 * 
	 * @param id
	 *           ID of this screen, it should be a positive integer
	 */
	public IppScreen(Integer id) {
		super("IppScreen");
		this.id = id;
	}
	
	/**
	 * Adds a new {@link IppCommand} to this screen.
	 * 
	 * @param c
	 *           command, IppCommand
	 */
	public void add(IppCommand c) {
		super.add(c);
		commandCount++;
	}
	
	/**
	 * Adds a new {@link IppHidden} to this screen.
	 * 
	 * @param h
	 *           hidden data, IppHidden
	 */
	public void add(IppHidden h) {
		super.add(h);
		hiddenCount++;
	}
	
	/**
	 * Adds a new {@link IppKey} to this screen.
	 * 
	 * @param k
	 *           key, IppKey
	 */
	public void add(IppKey k) {
		super.add(k);
	}
	
	/**
	 * Adds a new {@link IppAction} to this screen.
	 * 
	 * @param a
	 *           action, IppAction
	 */
	public void add(IppAction a) {
		super.add(a);
	}
	
	/**
	 * Adds a new {@link IppTicker} to this screen.
	 * 
	 * @param ti
	 *           ticker, IppTicker
	 */
	public void add(IppTicker ti) {
		super.add(ti);
	}
	
	/**
	 * Adds a new {@link IppAlert} to this screen, remember that the screen can have only
	 * one alert, and cannot have any other {@link IppList}, {@link IppTextBox},
	 * {@link IppPlayer} or {@link IppForm} if it has an alert.
	 * 
	 * @param al
	 *           alert, IppAlert
	 * @return the added unique element itself
	 */
	public Xml add(IppAlert al) {
		return this.addUnique(al);
	}
	
	/**
	 * Adds a new {@link IppList} to this screen, remember that the screen can have only
	 * one list, and cannot have any other {@link IppAlert}, {@link IppTextBox},
	 * {@link IppPlayer} or {@link IppForm} if it has a list.
	 * 
	 * @param l
	 *           list, IppList
	 * @return the added unique element itself
	 */
	public Xml add(IppList l) {
		return this.addUnique(l);
	}
	
	/**
	 * Adds a new {@link IppTextBox} to this screen, remember that the screen can have only
	 * one text box, and cannot have any other {@link IppAlert}, {@link IppList},
	 * {@link IppPlayer} or {@link IppForm} if it has a text box.
	 * 
	 * @param t
	 *           text box, IppTextBox
	 * @return the added unique element itself
	 */
	public Xml add(IppTextBox t) {
		return this.addUnique(t);
	}
	
	/**
	 * Adds a new {@link IppPlayer} to this screen, remember that the screen can have only
	 * one player, and cannot have any other {@link IppList}, {@link IppTextBox},
	 * {@link IppAlert} or {@link IppForm} if it has a player.
	 * 
	 * @param pl
	 *           player, IppPlayer
	 * @return the added unique element itself
	 */
	public Xml add(IppPlayer pl) {
		return this.addUnique(pl);
	}
	
	/**
	 * Adds the provided {@link IppForm} to this IppScreen, remember that the screen can
	 * have only one form, and cannot have any other {@link IppAlert}, {@link IppList},
	 * {@link IppTextBox} or {@link IppPlayer} if it has a form.
	 * 
	 * @param f
	 *           form, IppForm
	 * @return the added unique element itself
	 */
	public Xml add(IppForm f) {
		return this.addUnique(f);
	}
	
	/**
	 * Adds an element to this screen's sub-nodes, which is supposed to be unique. Such
	 * element must be one of the 5 specific classes.
	 * 
	 * @param o
	 *           element which is supposed to be unique; one of the following:
	 *           {@link IppList}, {@link IppTextBox}, {@link IppAlert}, {@link IppPlayer}
	 *           or {@link IppForm}
	 * @return the added element
	 */
	private Xml addUnique(Xml o) {
		String xmlClass = o.getClass().getSimpleName();
		if (o instanceof IppList || o instanceof IppTextBox || o instanceof IppAlert
				|| o instanceof IppPlayer || o instanceof IppForm) {
			if (hasUniqueElement)
				throw new IllegalArgumentException(
						"This screen already has a sub-node that prevents adding an "
								+ xmlClass + ".");
			Xml x = super.add(o);
			hasUniqueElement = true;
			uniqueIndex = this.getSubNodeIndex(o);
			return x;
		} else
			throw new IllegalArgumentException("this kind of element (" + xmlClass
					+ ") is not supposed to be unique in IppScreen");
	}
	
	@Override
	protected StringBuffer subObjectsToXmlString(int indent) {
		StringBuffer sb = new StringBuffer();
		if (uniqueIndex < 0)
			throw new IllegalArgumentException("IppScreen must contain "
					+ "one of the required items when it is sent to the OpenStage phone; "
					+ "add IppList, IppTextBox, IppAlert, IppPlayer or IppForm");
		Xml uniqueNode = this.getSubNode(uniqueIndex);
		sb.append(uniqueNode.toXmlString(indent));
		for (Xml o : getSubNodes())
			if (!o.equals(uniqueNode))
				sb.append(o.toXmlString(indent));
		return sb;
	}
	
	@Override
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (id != null)
			sb.append(attributeToXml("ID", id));
		if (hiddenCount > 0)
			sb.append(attributeToXml("HiddenCount", hiddenCount));
		sb.append(attributeToXml("CommandCount", commandCount));
		return sb;
	}
	
}
