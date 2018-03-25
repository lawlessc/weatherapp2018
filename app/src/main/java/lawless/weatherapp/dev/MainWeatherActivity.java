package lawless.weatherapp.dev;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import lawless.weatherapp.dev.weatherfragments.HumidityFragment;
import lawless.weatherapp.dev.weatherfragments.IconFragment;
import lawless.weatherapp.dev.weatherfragments.SunSetRiseFragment;
import lawless.weatherapp.dev.weatherfragments.TemperatureFragment;
import lawless.weatherapp.dev.weatherfragments.WindSpeedFragment;

public class MainWeatherActivity extends AppCompatActivity {

    WeatherOb weather;
    ImageView background;
    TextView weathername;


    TemperatureFragment temperatureFragment;
    WindSpeedFragment windSpeedFragment;
    HumidityFragment humidityFragment;
    IconFragment iconFragment;
    SunSetRiseFragment sunSetRiseFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);

        //Just to look nice
        background = (ImageView) findViewById(R.id.background);
        Drawable back = getResources().getDrawable(R.drawable.wallpaper);
        background.setImageDrawable(back);
        int width =background.getMeasuredWidth();
        background.setMaxHeight(width);

        weather = new WeatherOb(this);

        weathername = (TextView)findViewById(R.id.weather_name) ;

        temperatureFragment = (TemperatureFragment) getSupportFragmentManager().findFragmentById(R.id.temp_fragment);
        windSpeedFragment = (WindSpeedFragment) getSupportFragmentManager().findFragmentById(R.id.wind_fragment);
        humidityFragment = (HumidityFragment) getSupportFragmentManager().findFragmentById(R.id.humidity_fragment);
        iconFragment = (IconFragment) getSupportFragmentManager().findFragmentById(R.id.icon_fragment);
        sunSetRiseFragment = (SunSetRiseFragment) getSupportFragmentManager().findFragmentById(R.id.sunsetrise_fragment);

    }


    public class WeatherOb extends  Weather
    {

        public WeatherOb(Activity activity) {
            super(activity);
        }


        @Override
        public void updateFragments()
        {
            temperatureFragment.updateTemperature(weather.getCurrentTemperature() , weather.getMinimunTemperature(),getMaximumTemperature());
            windSpeedFragment.updateWindSpeed(weather.getWindSpeed());
            humidityFragment.updateHumidity(weather.getHumidity());
            iconFragment.updateIcon(weather.getWeather_icon());
            sunSetRiseFragment.updateSunriseSunset(weather.getSunset(),getSunRise());
            weathername.setText(weather.getWeather_type_name());

        }


    }


}

