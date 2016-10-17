package owm.weather.simulation;

import java.io.IOException;
import org.json.JSONException;

import owm.weather.simulation.WeatherData.WeatherCondition;

public class Weather {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		OwmClient owm = new OwmClient ();
		WeatherStatusResponse currentWeather;
		try {
			
			currentWeather = owm.currentWeatherAtCity ("Sydney", "AU");
			if (currentWeather.hasWeatherStatus ()) {
			    WeatherData weather = currentWeather.getWeatherStatus ().get (0);
			    
			    if (weather.getMain() != null) {
			        String humidity = new Float (weather.getMain().getHumidity()).toString();
			        String temp = new Float (weather.getMain().getTemp()/10).toString().substring(0,4);
			        String presure = new Float (weather.getMain().getPressure()).toString();
			        
			        System.out.println ("Temparature: " + temp.substring(0, 4) + "C " + "Humidity: " + humidity + "% " + "Presure: " + presure + "Pa");
			    }


			    if (weather.getPrecipitation () == Integer.MIN_VALUE) {
			        WeatherCondition weatherCondition = weather.getWeatherConditions ().get (0);
			        String description = weatherCondition.getDescription ();
			        if (description.contains ("rain") || description.contains ("shower"))
			            System.out.println ("No rain measures in Sydney but reports of " + description);
			        else
			            System.out.println ("No rain measures in Sydney: " + description);
			    } else {
			        System.out.println ("It's raining in Sydney: " + weather.getPrecipitation () + " mm/h");
			    }

		    
			}		

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main_(String[] args) {

		OwmClient owm = new OwmClient ();
		WeatherStatusResponse currentWeather;
		try {
			
			currentWeather = owm.currentWeatherAtCity ("Sydney", "AU");
			if (currentWeather.hasWeatherStatus ()) {
			    WeatherData weather = currentWeather.getWeatherStatus ().get (0);
			    if (weather.getPrecipitation () == Integer.MIN_VALUE) {
			        WeatherCondition weatherCondition = weather.getWeatherConditions ().get (0);
			        String description = weatherCondition.getDescription ();
			        if (description.contains ("rain") || description.contains ("shower"))
			            System.out.println ("No rain measures in Sydney but reports of " + description);
			        else
			            System.out.println ("No rain measures in Sydney: " + description);
			    } else
			        System.out.println ("It's raining in Sydney: " + weather.getPrecipitation () + " mm/h");
			}		

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
