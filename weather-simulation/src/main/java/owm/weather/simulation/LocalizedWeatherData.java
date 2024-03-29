package owm.weather.simulation;

import org.json.JSONObject;

public class LocalizedWeatherData extends WeatherData {
	private static final String JSON_URL      = "url";
	private static final String JSON_COORD    = "coord";
	private static final String JSON_DISTANCE = "distance";

	public static class GeoCoord {
		private static final String JSON_LAT = "lat";
		private static final String JSON_LON = "lon";

		private float latitude;
		private float longitude;

		GeoCoord (JSONObject json) {
			this.latitude = (float) json.optDouble (GeoCoord.JSON_LAT);
			this.longitude = (float) json.optDouble (GeoCoord.JSON_LON);
		}

		public boolean hasLatitude () {
			return this.latitude != Float.NaN;
		}
		public float getLatitude () {
			return latitude;
		}

		public boolean hasLongitude () {
			return this.longitude != Float.NaN;
		}
		public float getLongitude () {
			return longitude;
		}
	}

	private final String url;
	private final GeoCoord coord ;
	private final float distance;

	public LocalizedWeatherData (JSONObject json) {
		super (json);

		this.url = json.optString (LocalizedWeatherData.JSON_URL);
		this.distance = (float) json.optDouble (LocalizedWeatherData.JSON_DISTANCE, Double.NaN);

		JSONObject jsonCoord = json.optJSONObject (LocalizedWeatherData.JSON_COORD);
		this.coord = jsonCoord != null ? new GeoCoord (jsonCoord) : null;
	}

	public boolean hasUrl () {
		return this.url != null && this.url.length () > 0;
	}
	public String getUrl () {
		return this.url;
	}

	public boolean hasCoord () {
		return this.coord != null;
	}
	public GeoCoord getCoord () {
		return this.coord;
	}

	public boolean hasDistance () {
		return !Float.isNaN (this.distance);
	}
	public float getDistance () {
		return this.distance;
	}
}