package lawless.weatherapp.dev.weatherfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lawless.weatherapp.dev.R;


public class WindSpeedFragment extends Fragment {

    TextView Windspeed;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wind_data_fragment, container, false);

        Windspeed = (TextView) view.findViewById(R.id.windspeedtext);


        return view;
    }


    public void updateWindSpeed(float speed)
    {
        Windspeed.setText(String.valueOf(speed)+" metres per second");
    }

}
