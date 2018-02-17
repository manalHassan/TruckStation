package truckstationsa.truckstation;

/**
 * Created by amerah on 2/12/2018 AD.
 */


import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class PicassoClient {

    public  static  void downloadimg(Context c, String url, ImageView img)
    {
        if (url!=null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.mipmap.ic_launcher_round).into(img);

        }else
        {
            Picasso.with(c).load(R.mipmap.ic_launcher_round).into(img);
            Toast.makeText(c, "can not imag", Toast.LENGTH_SHORT).show();
        }
    }


}
