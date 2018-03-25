package lawless.weatherapp.dev.weatherfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lawless.weatherapp.dev.R;


public class HumidityFragment extends Fragment {


    TextView Humidity;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.humidty_data_fragment, container, false);

        Humidity = (TextView) view.findViewById(R.id.humidtext);


        return view;
    }



    public void updateHumidity(float hum)

    {
        Humidity.setText(String.valueOf(hum)+"%");
    }






}
