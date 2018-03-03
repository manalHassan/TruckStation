package truckstationsa.truckstation;

/**
 * Created by amerah on 2/12/2018 AD.
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MyHolder {
//////////////////////////
    TextView nameTxt;
    TextView cusin ;

    ImageView img;
    public MyHolder(View itemView) {


        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        cusin =(TextView) itemView.findViewById(R.id.cusin);
        img=(ImageView) itemView.findViewById(R.id.dogimage);



    }
}
