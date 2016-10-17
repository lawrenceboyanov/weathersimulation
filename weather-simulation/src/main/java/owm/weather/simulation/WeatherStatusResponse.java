package owm.weather.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherStatusResponse extends AbstractOwmResponse {
	private final List<StatusWeatherData> status;

	/** A parser for a weather status query response
	 * @param json The JSON obejct built from the OWM response */
	public WeatherStatusResponse (JSONObject json) {
		super (json);
		JSONArray jsonWeatherStatus = json.optJSONArray (AbstractOwmResponse.JSON_LIST);
		if (jsonWeatherStatus == null) {
			this.status = Collections.emptyList ();
		} else {
			this.status = new ArrayList<StatusWeatherData> (jsonWeatherStatus.length ());
			for (int i = 0; i <jsonWeatherStatus.length (); i++) {
				JSONObject jsonStatus = jsonWeatherStatus.optJSONObject (i);
				if (jsonStatus != null) {
					this.status.add (new StatusWeatherData (jsonStatus));
				}
			}
		}
	}

	public boolean hasWeatherStatus () {
		return this.status != null && !this.status.isEmpty ();
	}
	public List<StatusWeatherData> getWeatherStatus () {
		return this.status;
	}
}
