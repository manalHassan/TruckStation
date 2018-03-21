package truckstationsa.truckstation;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/////////////
public class Itemarrayforcustomer extends BaseAdapter {
    Context c;
    ArrayList<Item> artists;
    LayoutInflater inflater;


    public Itemarrayforcustomer(Context c, ArrayList<Item> artists) {
        this.c = c;
        this.artists = artists;
    }
    @Override
    public int getCount() {
        return artists.size();

    }

    @Override
    public Object getItem(int i) {
        return artists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        if (inflater== null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if(convertview==null)
        {
            convertview= inflater.inflate(R.layout.listviewtracks_layout,viewGroup,false);

        }

        itemHolder holder= new itemHolder(convertview);
//        holder.nameprice.setText((int) artists.get(i).getIPrice());
        holder.nameitem.setText(artists.get(i).getIName());



        return convertview;
    }

}
