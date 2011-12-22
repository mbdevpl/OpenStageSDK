package pl.mbdev.openstage;

/**
 * Important type of XML object, used to send user-created data from the OpenStage phone
 * to the external server.
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
public class IppForm extends Xml {
	
	/**
	 * Available values of proportion parameter in the constructor of {@link IppForm}, they
	 * define proportions between left and right column of the form.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Proportion {
		/**
		 * left column 0%, right 100%
		 */
		L0_R100,
		/**
		 * left column 15%, right 85%
		 */
		L15_R85,
		/**
		 * left column 25%, right 75%
		 */
		L25_R75,
		/**
		 * left column 40%, right 60%
		 */
		L40_R60,
		/**
		 * left column 50%, right 50%
		 */
		L50_R50,
		/**
		 * left column 60%, right 40%
		 */
		L60_R40,
		/**
		 * left column 75%, right 25%
		 */
		L75_R25;
		
		public String toString() {
			return super.toString().replace("L", "").replace("R", "");
		}
	}
	
	/**
	 * Number of items currently present in this form.
	 */
	private int itemCount = 0;
	
	/**
	 * Title of the form.
	 */
	private String title = "";
	
	/**
	 * URL address to which the values from the form can be sent via SELECT command (see
	 * {@link IppCommand} and {@link IppCommand.Type}).
	 */
	private String url = "";
	
	/**
	 * Value from {@link IppForm.Proportion}, defines proportion between width of left and
	 * right column.
	 */
	private Proportion proportion = Proportion.L50_R50;
	
	/**
	 * Constructs new form with no initial items or commands, and with default column
	 * proportions (left 50%, right 50%).
	 * 
	 * @param title
	 *           title of the form
	 * @param url
	 *           URL address to which the values from this form, and hidden fields (see
	 *           {@link IppHidden}) can be sent via SELECT command (see {@link IppCommand}
	 *           and {@link IppCommand.Type})
	 */
	public IppForm(String title, String url) {
		this(title, url, null);
	}
	
	/**
	 * Constructs new form with no initial items or commands.
	 * 
	 * @param title
	 *           title of the form
	 * @param url
	 *           URL address to which the values from the form can be sent via SELECT
	 *           command (see {@link IppCommand} and {@link IppCommand.Type})
	 * @param proportion
	 *           value from {@link IppForm.Proportion}, defines proportion between width of
	 *           left and right column
	 * 
	 */
	public IppForm(String title, String url, Proportion proportion) {
		super("IppForm");
		this.title = title;
		this.url = url;
		this.proportion = proportion;
		
		this.add("Title", this.title);
		this.add("Url", this.url);
	}
	
	/**
	 * Adds the provided {@link IppItem} to this IppForm.
	 * 
	 * @param i
	 *           IppItem
	 */
	public void add(IppItem i) {
		super.add(i);
		itemCount++;
	}
	
	/**
	 * Adds the provided form item (any subclass of the {@link XmlFormItem}) to this
	 * IppForm.
	 * 
	 * @param fi
	 *           form item: {@link IppStringItem}, {@link IppImageItem}, {@link IppSpacer},
	 *           {@link IppTextField}, {@link IppChoiceGroup}, {@link IppDateField},
	 *           {@link IppButton} or {@link IppGauge}
	 * @return the added form item, or its logical root if it was wrapped with
	 *         {@link IppItem}
	 */
	public Xml add(XmlFormItem fi) {
		Xml x = this.add(fi.getLogicalRoot());
		itemCount++;
		return x;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("ItemCount", itemCount));
		if (proportion != null)
			sb.append(attributeToXml("Proportion", proportion));
		return sb;
	}
	
}
