package truckstationsa.truckstation;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReserveTruck extends AppCompatActivity {
    private DatabaseReference spinnerRef ,reserveRef;
    Spinner DateTimeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_truck);

        spinnerRef = FirebaseDatabase.getInstance().getReference("PublicFoodTruckOwner");

        //request =reserve
        reserveRef = FirebaseDatabase.getInstance().getReference("Request"); // make sure its identical to the table name in the database





String ownerID="kPmL8c1nkdOnfvvIFC3kJbX7DP73";
        spinnerRef.child(ownerID).child("RDate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> dateAndTimeList = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String dateAndTime = areaSnapshot.getValue(String.class);
                    dateAndTimeList.add(dateAndTime);
                }

                 DateTimeSpinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> DateTimeAdapter = new ArrayAdapter<String>(ReserveTruck.this, android.R.layout.simple_spinner_item, dateAndTimeList);
                DateTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                DateTimeSpinner.setAdapter(DateTimeAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void ReservationInfo(View view){

        // get selected value from dropdoenlist(spinner)
        final String DateTIME=DateTimeSpinner.getSelectedItem().toString().trim();
if(!TextUtils.isEmpty(DateTIME)){
        reserveRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();//customer id is the same as rating id to make it easy to refer

                final String FID="FIDtst";
                final String CID=id;
                final String RID=reserveRef.push().getKey();  //random id for request  *malh da3i*


                if ( !TextUtils.isEmpty(FID) && !TextUtils.isEmpty(CID) && !TextUtils.isEmpty(RID)) {

                    Request request=new Request(CID,FID,RID,DateTIME);

                    reserveRef.child(RID).setValue(request);
                    Toast.makeText(ReserveTruck.this, "تم الحجز,بانتظار موافقة صاحب حافلة الطعام", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    else{
    Toast.makeText(ReserveTruck.this, "نأسف, لايوجد وقت للحجز", Toast.LENGTH_SHORT).show();
    finish();
}


    }



    public void canceleRequest(View view){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Do your Yes progress
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("هل أنت متأكد من أنك تريد إلغاء الحجز ؟").setPositiveButton("نعم", dialogClickListener)
                .setNegativeButton("لا", dialogClickListener).show();

    }

}
