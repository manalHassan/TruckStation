package truckstationsa.truckstation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by manal on 2/10/2018.
 */

public class VisitorHomePage extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();
    double x = 0;
    double y = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visitor_home_page);


        dbRef.child("PublicFoodTruckOwner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    PublicFoodTruckOwner pOwner ;
                   pOwner =  postSnapshot.getValue(PublicFoodTruckOwner.class);
                   if ( pOwner == null)
                       Toast.makeText(VisitorHomePage.this, "فاضي !!!!!!!!", Toast.LENGTH_SHORT).show();
                    try {
                        if (pOwner != null){
                          x =   pOwner.getXFLication();
                          y = pOwner.getYFLocation();
                            Toast.makeText(VisitorHomePage.this, "يتم تحديد المواقع الان....", Toast.LENGTH_SHORT).show();
                           // mMap.clear();
                            LatLng location = new LatLng(x,y);
                            mMap.addMarker(new MarkerOptions()
                                            .position(location)
                                            .title(pOwner.getFUsername()));
                                            //.icon(BitmapDescriptorFactory.fromBitmap(bmp))
                        }
                    }

                    catch (Exception e){
                        Toast.makeText(VisitorHomePage.this, "حدث خطاء ما !!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(24.713, 46.675 ),5));
       // mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
      //  CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

    }






}
