package pl.mbdev.openstage;

/**
 * Shows an image on the screen of phone. Image may be downloaded from a remote server, or
 * may come from the OpenStage device that launched the application.
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
public class Image extends Xml {
	
	/**
	 * name under which the image is stored in the cache, and can be used later on without
	 * re-downloading
	 */
	private String cache = "";
	
	/**
	 * @param cache
	 *           name under which the image is stored in the cache, and can be used later
	 *           on without re-downloading
	 * @param source
	 *           location of the source of the image
	 */
	public Image(String cache, String source) {
		super("Image", source);
		
		this.cache = cache;
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		if (cache != null && cache.length() > 0)
			sb.append(attributeToXml("Cache", cache));
		return sb;
	}
	
}
