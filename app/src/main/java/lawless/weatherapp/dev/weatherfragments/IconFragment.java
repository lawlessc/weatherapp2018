package lawless.weatherapp.dev.weatherfragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lawless.weatherapp.dev.R;


public class IconFragment extends Fragment {


    ImageView icon;




    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_icon_fragment, container, false);

        icon = (ImageView) view.findViewById(R.id.iconview);

        Drawable empty_profile = getResources().getDrawable(R.drawable.blank);
        icon.setImageDrawable(empty_profile);



        return view;
    }



    public void updateIcon(Bitmap bitmap)

    {



        icon.setImageBitmap(bitmap);

    }






}
