package lawless.weatherapp.dev;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chris on 24/03/2018.
 */

public class Weather {

    private FusedLocationProviderClient mFusedLocationClient;
    Location location_;
    RequestQueue queue;

    private Activity parentActivity;
    String weather_type_name;
    float CurrentTemperature;
    float MinimunTemperature;
    float MaximumTemperature;
    float WindSpeed;
    float Humidity;
    long  sunset;
    long  sunrise;


    String apiurl = "http://api.openweathermap.org/data/2.5/weather?";
    String token  = "e33fcac2468f09cd4b6759753abadc0a";
    String weatherIconurl;
    Bitmap weather_icon ;



    public Weather(Activity activity)
    {
        parentActivity =activity;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        queue = Volley.newRequestQueue(parentActivity);

        getLastLocation();



    }




    public float getCurrentTemperature() {
        return CurrentTemperature;
    }



    public float getMaximumTemperature() {
        return MaximumTemperature ;
    }

    public float getMinimunTemperature() {
        return MinimunTemperature;
    }

    public float getHumidity() {
        return Humidity ;
    }

    public float getWindSpeed() {
        return WindSpeed ;
    }

    public Bitmap getWeather_icon(){
        return weather_icon;
    }

    public Long getSunRise() {
        return sunrise ;
    }

    public Long getSunset() {
        return sunset ;
    }
    public String getWeather_type_name()
    {
        return weather_type_name;
    }


    public void  getLastLocation()
    {

        if (ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(parentActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(parentActivity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            location_=location;
                            System.out.println("Location is :"+location);
                            requestWeather();

                        }
                        else
                        {
                            System.out.println("Failed to get location");

                            Context context = parentActivity.getApplicationContext();
                            CharSequence text = "Check Location permissions!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        }
                    }
                });

    }


    public void  requestWeather()
    {

        String url = apiurl+"lat="+location_.getLatitude()+"&lon="+ location_.getLongitude()+"&APPID="+token;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        System.out.println("weather response: "+response );
                        parseJson(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("weather response: fail");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void  requestWeatherIcon()
    {
        System.out.println("IMAGE TRY");
        ImageRequest imageRequest = new ImageRequest(
                "http://openweathermap.org/img/w/"+ weatherIconurl +".png" ,//+"&APPID="+token, // Image URL
                new Response.Listener<Bitmap>() { // Bitmap listener
                    @Override
                    public void onResponse(Bitmap response) {

                        weather_icon = response;
                        updateFragments();
                        System.out.println("GOT IMAGE");
                    }
                },
                50, // Image width
                50, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
                new Response.ErrorListener() { // Error listener
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something with error response
                        error.printStackTrace();
                        System.out.println("IMAGE FAIL");

                    }
                }
        );

        queue.add(imageRequest);
    }


    public void parseJson(String response)
    {


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject sys = new JSONObject(jsonObject.getString("sys"));

            JSONArray weather = jsonObject.getJSONArray("weather");

            JSONObject wind = new JSONObject(jsonObject.getString("wind"));
            JSONObject main = new JSONObject(jsonObject.getString("main"));

           CurrentTemperature= Float.valueOf(  main.getString("temp"));
           MinimunTemperature= Float.valueOf(  main.getString("temp_min"));
           MaximumTemperature= Float.valueOf(  main.getString("temp_max"));

           JSONObject jsonObjecticon =  weather.getJSONObject(0);
           weatherIconurl =  jsonObjecticon.getString("icon");
           weather_type_name =jsonObjecticon.getString("main");

           Humidity=   Float.valueOf(  main.getString("humidity"));
           WindSpeed = Float.valueOf(  wind.getString("speed"));

           sunset=   sys.getLong("sunset");
           sunrise=   sys.getLong("sunrise");

            requestWeatherIcon();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //override this
    public void updateFragments()
    {

    }
}
