package pl.mbdev.openstage.push;

import java.util.HashMap;

/**
 * Parameters for HTTP request.
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
public class RequestParameters {
	
	/**
	 * Mapping of the parameters.
	 */
	HashMap<Object, Object> parameters;
	
	/**
	 * Creates new parameters for a HTTP request.
	 * 
	 * @param names
	 *           names of the parameters
	 * @param values
	 *           values of the parameters
	 */
	public RequestParameters(Object[] names, Object[] values) {
		if (names == null)
			throw new NullPointerException("");
		if (values == null)
			throw new NullPointerException("");
		
		if (names.length != values.length)
			throw new IllegalArgumentException("");
		
		parameters = new HashMap<Object, Object>();
		
		for (int i = 0; i < names.length; i++)
			parameters.put(names[i], values[i]);
	}
	
	/**
	 * Converts parameter data to key-value pair that is usable as a part of request
	 * string.
	 * 
	 * @param key
	 *           key
	 * @param value
	 *           value
	 * @param first
	 *           is this the first created parameter
	 * @return 'key=value' with character '&' or '?' depending on the circumstances
	 */
	private StringBuffer parameterToStringBuffer(String key, Object value, boolean first) {
		StringBuffer sb = new StringBuffer();
		if (first)
			sb.append('?');
		else
			sb.append('&');
		sb.append(key).append("=").append(String.valueOf(value));
		return sb;
	}
	
	/**
	 * Converts all parameters to a string buffer.
	 * 
	 * @return parameters in the string form, key-value pairs
	 */
	private StringBuffer parametersToStringBuffer() {
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		for (Object key : parameters.keySet()) {
			sb.append(parameterToStringBuffer(key.toString(), parameters.get(key), isFirst));
			isFirst = false;
		}
		return sb;
	}
	
	@Override
	public String toString() {
		return parametersToStringBuffer().toString();
	}
	
}
