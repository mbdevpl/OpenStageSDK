package pl.mbdev.openstage;

import pl.mbdev.openstage.IppGauge.Interactive;

/**
 * Player is designed for applications that involve audio streaming. All commands are sent
 * to the server on the fly, enabling it to react properly and stop/resume stream
 * broadcast or receiving.
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
public class IppPlayer extends XmlWithKey {
	
	/**
	 * Defines possible modes of the {@link IppPlayer}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum Mode {
		/**
		 * The player is in call mode, therefore it generally receives content.
		 */
		CALL,
		/**
		 * The player is in record mode, therefore it generally produces content.
		 */
		RECORD;
	}
	
	/**
	 * Defines possible states of the {@link IppPlayer}.
	 * 
	 * @author Mateusz Bysiek
	 */
	public enum State {
		/**
		 * The player is in playing state.
		 */
		PLAYING,
		/**
		 * The player is in stopped state.
		 */
		STOPPED,
		/**
		 * The player is in recording state. The player can be in this state only when it is
		 * in RECORD mode.
		 */
		RECORDING;
	}
	
	/**
	 * Mode of the player, value from {@link IppPlayer.Mode}.
	 */
	private final Mode mode;
	
	/**
	 * State of the player, value from {@link IppPlayer.State}.
	 */
	private final State state;
	
	/**
	 * Creates new player with all initial conditions available.
	 * 
	 * @param url
	 *           URL to which the actions inside the player are submitted
	 * @param mode
	 *           Mode of the player, value from {@link IppPlayer.Mode}
	 * @param state
	 *           State of the player, value from {@link IppPlayer.State}
	 * @param key
	 *           key of the key-value pair sent to the server, when a button of the player
	 *           is pressed
	 * @param gauge
	 *           gauge that will be displayed above the player's buttons, and will show the
	 *           progress of the playback/recording.
	 */
	public IppPlayer(String url, Mode mode, State state, String key, IppGauge gauge) {
		super("IppPlayer", key);
		this.mode = mode;
		if (mode.equals(Mode.CALL) && state.equals(State.RECORDING))
			throw new IllegalArgumentException(
					"when IppPlayer is in CALL mode, it cannot be in RECORDING state");
		this.state = state;
		
		add("Url", url);
		
		if (mode.equals(Mode.CALL) && state.equals(State.PLAYING)
				&& gauge.getInteractive().equals(Interactive.USER))
			throw new IllegalArgumentException("in IppPlayer, when mode is CALL "
					+ "and state is PLAYING, the IppGauge must be in AUTO mode");
		if (mode.equals(Mode.RECORD)
				&& (state.equals(State.PLAYING) || state.equals(State.RECORDING))
				&& gauge.getInteractive().equals(Interactive.USER))
			throw new IllegalArgumentException("in IppPlayer, in RECORD mode "
					+ "and in PLAYING or RECORDING state, "
					+ "the IppGauge must be in AUTO mode");
		
		add(gauge);
	}
	
	protected StringBuffer attributesToXmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(attributeToXml("Mode", mode));
		sb.append(attributeToXml("Default", state));
		return sb;
	}
}
