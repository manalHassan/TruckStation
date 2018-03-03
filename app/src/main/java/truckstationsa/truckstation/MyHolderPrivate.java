package truckstationsa.truckstation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by amerah on 2/26/2018 AD.
 */
////////////
public class MyHolderPrivate {
    TextView nameTxt;
    TextView cusin ;

    ImageView img;
    public MyHolderPrivate(View itemView) {


        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        cusin =(TextView) itemView.findViewById(R.id.cusin);
        img=(ImageView) itemView.findViewById(R.id.dogimage);



    }
}
