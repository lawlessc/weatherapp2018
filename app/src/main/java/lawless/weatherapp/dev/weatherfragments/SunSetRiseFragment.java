package lawless.weatherapp.dev.weatherfragments;


import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.Date;

import lawless.weatherapp.dev.R;


public class SunSetRiseFragment extends Fragment {

    TextView Sunset;
    TextView Sunrise;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sunsetrise_fragment, container, false);

        Sunset = (TextView) view.findViewById(R.id.sunset);
        Sunrise = (TextView) view.findViewById(R.id.sunrise);

        return view;
    }

    public void updateSunriseSunset(Long set, Long rise)
    {
        Sunset.setText(convertTime(set));
        Sunrise.setText(convertTime(rise));
    }

    public String convertTime(long time){
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(new Date(time*1000L));
    }


}
