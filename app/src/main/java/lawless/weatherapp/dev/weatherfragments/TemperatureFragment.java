package lawless.weatherapp.dev.weatherfragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import lawless.weatherapp.dev.R;


public class TemperatureFragment extends Fragment {


    TextView CurrentTemperature;
    TextView minT;
    TextView maxT;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temperature_data_fragment, container, false);

        CurrentTemperature = (TextView) view.findViewById(R.id.temperaturetext);
        minT = (TextView) view.findViewById(R.id.temperaturemintext);
        maxT = (TextView) view.findViewById(R.id.temperaturemaxtext);

        return view;
    }


    public void updateTemperature(float current, float min, float max)
    {
                                   //convert to metric here, celcius
        CurrentTemperature.setText(String.valueOf(current - 273.15f)+"°C" );
        minT.setText(String.valueOf(min- 273.15f)+"°C");
        maxT.setText(String.valueOf(max- 273.15f)+"°C");

    }

}
