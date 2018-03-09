package truckstationsa.truckstation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * Created by wafaa7maD on 05/03/18.
 */

public class publicTab1profileforcustmer  extends Fragment {
    Button button;
    DatabaseReference databaseref;
    TextView username;
    TextView mail;
    TextView phone;
    TextView wwh;
  //  FirebaseAuth firebaseAuth;
    private TextView location;
    String user1="";
    String address;
    //String user1="jVmYjqfu5leLTx4gkFPQiQ9E3g83";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       //  user1 = getArguments().getString("id");
        Bundle b = getActivity().getIntent().getExtras();
        user1 = b.getString("id");
        final View rootView= inflater.inflate(R.layout.public_tab1_profileforcustomer, container, false);


        setHasOptionsMenu(true);//Make sure you have this line of code
        databaseref = FirebaseDatabase.getInstance().getReference();
        databaseref.child("PublicFoodTruckOwner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child(user1).child("fusername").getValue(String.class);
               int phonnum = dataSnapshot.child(user1).child("fponeNoumber").getValue(int.class);
                String email = dataSnapshot.child(user1).child("femail").getValue(String.class);
                String cc = dataSnapshot.child(user1).child("qusins").getValue(String.class);


                //String wh=dataSnapshot.child(user1).child("fworkingHours").getValue(String.class);

                username = (TextView) rootView.findViewById(R.id.type);
                username.setText(" " + cc);
                TextView profilename = (TextView) rootView.findViewById(R.id.user);
                profilename.setText(name);
               // wwh= (TextView) rootView.findViewById(R.id.wh);
              //  wwh.setText(" " + wh);
                mail = (TextView) rootView.findViewById(R.id.email);
                mail.setText(" " + email);
                phone = (TextView) rootView.findViewById(R.id.phone);
                phone.setText(" " + phonnum + " ");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "لايوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
            }
        });





        //
        return rootView;
    }
    public void gorating(View view){
         Intent intent = new Intent(getActivity(), Rating.class);
            Bundle b=new Bundle();
            b.putString("id",user1);
            intent.putExtras(b);
            startActivity(intent);

    }

    public void goreserve(View view){
        Intent intent = new Intent(getActivity(), ReserveTruck.class);
        Bundle b=new Bundle();
        b.putString("id",user1);
        intent.putExtras(b);
        startActivity(intent);

    }

}

