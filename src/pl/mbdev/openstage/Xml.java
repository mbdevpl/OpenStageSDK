package pl.mbdev.openstage;

import java.io.PrintWriter;

import java.util.ArrayList;

/**
 * General definition of an XML object that represents the common part of all XML entities
 * used by OpenStage 60/80 VoIP phones, according to the
 * "OpenStage 60/80 - XML Applications, Developer's Guide" available in September 2011.
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
public abstract class Xml {
	
	/**
	 * Name of this object in its XML-text form.
	 */
	private String nodeName = "";
	
	/**
	 * Contents of this node.
	 */
	private String contents = "";
	
	/**
	 * References to all sub-objects of this XML object.
	 */
	private ArrayList<Xml> subObjects;
	
	/**
	 * Parent of this node. May be null. Used in some cases when adding command to an item
	 * that should not usually have commands.
	 */
	private Xml parent;
	
	/**
	 * The node that is considered as a node with respect to which all operations are made.
	 */
	private Xml logicalRoot;
	
	/**
	 * Parameterless constructor.
	 */
	private Xml() {
		super();
		logicalRoot = this;
	}
	
	/**
	 * Default constructor. Creates new node without sub-nodes, and without any text
	 * contents.
	 * 
	 * @param nodeName
	 *           name of the XML node
	 */
	public Xml(String nodeName) {
		this();
		this.nodeName = nodeName;
		subObjects = new ArrayList<Xml>();
	}
	
	/**
	 * Creates new node, without sub-nodes but with text contents.
	 * 
	 * @param nodeName
	 *           name of the XML node
	 * @param contents
	 *           contents of the XML entity
	 */
	public Xml(String nodeName, Object contents) {
		this(nodeName);
		this.contents = String.valueOf(contents);
	}
	
	/**
	 * 
	 * @param index
	 *           non-negative number
	 * @return sub node at given index in the list of sub-nodes
	 */
	protected Xml getSubNode(int index) {
		return subObjects.get(index);
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in sub-nodes
	 * list, or -1 if that list does not contain the element. More formally, returns the
	 * lowest index i such that (o==null ? subObjects.get(i)==null :
	 * o.equals(subObjects.get(i))), or -1 if there is no such index.
	 * 
	 * @param o
	 *           element to search for
	 * @return the index of the first occurrence of the element in sub-nodes list, or -1 if
	 *         the list does not contain the element specified
	 */
	protected int getSubNodeIndex(Xml o) {
		return subObjects.indexOf(o);
	}
	
	/**
	 * Returns reference to the list of sub-nodes of this object.
	 * 
	 * @return reference to the list of sub-nodes of this object
	 */
	protected ArrayList<Xml> getSubNodes() {
		return subObjects;
	}
	
	/**
	 * Returns the parent node of this node, provided that this node is some other object's
	 * sub-node.
	 * 
	 * @return parent node of this node, null if it is a root node (has no parent)
	 */
	protected Xml getParent() {
		return parent;
	}
	
	/**
	 * Gets the logical root of this object.
	 * 
	 * @return logical root of this object
	 */
	protected Xml getLogicalRoot() {
		return this.logicalRoot;
	}
	
	/**
	 * Sets parent of this object to a desired one.
	 * 
	 * @param o
	 *           any kind of XML object from OpenStage SDK
	 */
	protected void setParent(Xml o) {
		this.parent = o;
	}
	
	/**
	 * Inserts the provided object between this node and its parent.
	 * 
	 * @param o
	 *           any kind of XML object from OpenStage SDK
	 */
	private void insert(Xml o) {
		Xml localParent = this.parent;
		if (localParent == null) {
			o.add(this);
			// this.parent = o;
			this.logicalRoot = o;
		} else {
			localParent.remove(this);
			localParent.add(o);
			o.add(this);
			this.parent = o;
			if (logicalRoot != this) {
				// nothing happens
			} else {
				this.logicalRoot = o;
			}
		}
	}
	
	/**
	 * Adds the provided XML object to sub-objects of this object, and returns it.
	 * 
	 * @param o
	 *           any kind of XML object from OpenStage SDK
	 * @return the object provided in the parameter
	 */
	protected Xml add(Xml o) {
		if (o == null)
			throw new NullPointerException(
					"you shouldn't add null references as a sub-nodes to any XML entity");
		subObjects.add(o);
		o.setParent(this);
		return o;
	}
	
	/**
	 * Adds the provided XML object to the logical root of this object. In 99% of cases,
	 * you should use ordinary add() method instead of this one.<br />
	 * <br />
	 * See description of logicalRoot attribute of {@link Xml} class for details like: what
	 * is a logical root, how to use it and how it is used by OpenStage SDK.
	 * 
	 * @param o
	 *           any kind of XML object from OpenStage SDK
	 */
	protected void logicalAdd(Xml o) {
		this.logicalRoot.add(o);
	}
	
	/**
	 * Removes selected item from the list of sub-nodes of this XML object.
	 * 
	 * @param o
	 *           any kind of XML object from OpenStage SDK, that you wish to be removed
	 */
	private void remove(Xml o) {
		if (o == null)
			return;
		this.subObjects.remove(o);
		o.parent = null;
	}
	
	/**
	 * Adds a simple sub-node to this XML entity.
	 * 
	 * @param nodeName
	 *           name of the node
	 * @param contents
	 *           contents, which will be converted to String
	 */
	protected void add(String nodeName, Object contents) {
		this.addAndReturn(nodeName, contents);
	}
	
	/**
	 * Adds a simple sub-node to this XML entity, and returns a reference to it.
	 * 
	 * @param nodeName
	 *           name of the node
	 * @param contents
	 *           contents, which will be converted to String
	 * @return the added sub-node
	 */
	protected Xml addAndReturn(String nodeName, Object contents) {
		Xml o = new Xml(nodeName, contents) {
		};
		this.add(o);
		return o;
	}
	
	/**
	 * Wraps this XML object with a new {@link IppItem} and returns this IppItem. In case
	 * this object is already wrapped, it simply returns the existing IppItem that wraps
	 * the object.
	 * 
	 * @return the {@link IppItem}
	 */
	protected IppItem wrapWithIppItem() {
		if (this.logicalRoot != this) {
			if (this.logicalRoot instanceof IppItem)
				return (IppItem) this.logicalRoot;
			else
				throw new IllegalArgumentException(
						"this node is already wrapped in something else");
		}
		IppItem it = new IppItem();
		this.insert(it);
		this.logicalRoot = it;
		return it;
	}
	
	/**
	 * Returns number of sub-nodes of this XML node.
	 * 
	 * @return nonnegative integer, number of sub-nodes of this XML node
	 */
	protected int subObjectsCount() {
		return subObjects.size();
	}
	
	/**
	 * Special method used by intermediate subclasses, which may desire to attach
	 * attributes to the object without need to override attirbutesToXmlString() method.
	 * 
	 * @return should return attributes of the intermediate subclass in a XML-text format
	 */
	protected StringBuffer firstAttributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		return sb;
	}
	
	/**
	 * Should be overridden by classes that have some attributes.
	 * 
	 * @return XML-text containing all attributes of the object
	 */
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		return sb;
	}
	
	/**
	 * Converts an attribute for an object to its XML-text representation.
	 * 
	 * @param attributeName
	 *           name of the attribute
	 * @param attributeValue
	 *           value of the attribute
	 * @return XML-text in the format: ' "name"="value"'
	 */
	protected String attributeToXml(String attributeName, Object attributeValue) {
		StringBuffer sb = new StringBuffer();
		if (attributeName == null)
			throw new NullPointerException("XML attribute name cannot be null");
		sb.append(" ").append(attributeName).append("=\"")
				.append(String.valueOf(attributeValue)).append("\"");
		return sb.toString();
	}
	
	/**
	 * Goes through every sub-object of this object, creates its XML-text representation,
	 * and returns all of them in a single StringBuffer.
	 * 
	 * @param indent
	 *           indent of the output of this function, done by tab character
	 * @return XML-text with all sub-objects of this object
	 */
	protected StringBuffer subObjectsToXmlString(int indent) {
		StringBuffer sb = new StringBuffer();
		for (Xml o : subObjects)
			sb.append(o.toXmlString(indent));
		return sb;
	}
	
	/**
	 * Returns full XML-text representation of this object, with all objects it contains
	 * inside, attributes, and a XML header.
	 * 
	 * @param indent
	 *           indent of the output of this function, done by tab character
	 * @return full XML-text representation of this object
	 */
	protected StringBuffer toXmlString(int indent) {
		StringBuffer sb = new StringBuffer();
		
		// adding indent
		for (int i = 0; i < indent; i++)
			sb.append("\t");
		
		sb.append("<").append(this.nodeName).append(this.firstAttributesToXmlString())
				.append(this.attributesToXmlString());
		
		if (this.isEmpty()) {
			sb.append(" />\n");
			return sb;
		} else
			sb.append(">");
		
		if (this.subObjectsCount() > 0) {
			sb.append("\n");
			sb.append(this.subObjectsToXmlString(indent + 1));
			// adding indent
			for (int i = 0; i < indent; i++)
				sb.append("\t");
		} else if (contents.length() > 0)
			sb.append(contents);
		
		sb.append("</").append(this.nodeName).append(">\n");
		return sb;
	}
	
	/**
	 * Checks if this node has any contents or sub-nodes.
	 * 
	 * @return true if this node does not have any contents or sub-nodes
	 */
	public boolean isEmpty() {
		return (contents == null || contents.equals("")) && subObjectsCount() == 0;
	}
	
	/**
	 * Returns default XML file header, needed for proper interpretation of the file
	 * contents by the OpenStage device.
	 * 
	 * @return default XML file header, needed for proper interpretation of the file
	 *         contents by the OpenStage device
	 */
	protected StringBuffer getXmlHeader() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		return sb;
	}
	
	/**
	 * This method sends the XML to the receiving OpenStage device.
	 * 
	 * @param out
	 *           stream that receives sent content
	 * @throws IllegalArgumentException
	 *            when the server is trying to send an object that cannot be a root or
	 *            cannot be "made" a root
	 */
	public void sendTo(PrintWriter out) throws IllegalArgumentException {
		out.println(this.getXmlHeader().toString());
		IppPhone p = null;
		if (this instanceof IppPhone) {
			p = (IppPhone) this;
		} else {
			p = new IppPhone();
			IppDisplay d = null;
			if (this instanceof IppDisplay) {
				d = (IppDisplay) this;
			} else {
				d = new IppDisplay(null, -1);
				IppScreen s = null;
				if (this instanceof IppScreen) {
					s = (IppScreen) this;
				} else {
					s = new IppScreen(1);
					if (this instanceof IppAlert)
						s.add((IppAlert) this);
					else if (this instanceof IppList)
						s.add((IppList) this);
					else if (this instanceof IppTextBox)
						s.add((IppTextBox) this);
					else if (this instanceof IppPlayer)
						s.add((IppPlayer) this);
					else if (this instanceof IppForm)
						s.add((IppForm) this);
					else {
						s.add(this.getLogicalRoot());
						// other sub-object checking can be added here
						throw new IllegalArgumentException("The item "
								+ this.getClass().getSimpleName() + " is not suitable "
								+ "to be sent directly to the phone; please place it "
								+ "inside the correct element");
					}
					
				}
				d.add(s);
			}
			p.add(d);
		}
		out.println(p.toXmlString(0));
	}
	
	/**
	 * Gets the contents of this node.
	 * 
	 * @return the contents of this node
	 */
	public String getContents() {
		return this.contents;
	}
	
	/**
	 * Sets the contents of this node.
	 * 
	 * @param contents
	 *           new contents of this node
	 */
	protected void setContents(String contents) {
		this.contents = contents;
	}
	
}
