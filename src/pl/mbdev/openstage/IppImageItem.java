package pl.mbdev.openstage;

/**
 * Item with an image that can be added to {@link IppForm}. In case when the image is not
 * available, the alternative text can be used as a substitution.
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
public class IppImageItem extends XmlFormItem {
	
	/**
	 * Image attached to this image item.
	 */
	private Image image = null;
	
	/**
	 * Creates new image item with all parameters available.
	 * 
	 * @param label
	 *           the label
	 * @param image
	 *           the image
	 * @param altText
	 *           alternative text
	 */
	public IppImageItem(String label, Image image, String altText) {
		super("IppImageItem");
		
		this.image = image;
		
		this.add("Label", label);
		this.add("AltText", altText);
		if (image != null)
			this.add(image);
	}
	
	/**
	 * Sets the label for this image item.
	 * 
	 * @param label
	 *           new label for this image item
	 */
	public void setLabel(String label) {
		this.getImage().setContents(label);
	}
	
	/**
	 * Sets the alternative text for this image item.
	 * 
	 * @param altText
	 *           new alternative text
	 */
	public void setAltText(String altText) {
		this.getImage().setContents(altText);
	}
	
	/**
	 * Returns the image in this image item
	 * 
	 * @return image in this image item
	 */
	protected Image getImage() {
		return image;
	}
	
}
