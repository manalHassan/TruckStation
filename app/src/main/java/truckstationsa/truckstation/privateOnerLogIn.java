package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by manal on 2/10/2018.
 */

public class privateOnerLogIn extends AppCompatActivity {
    Button login  ;
    Button rigister ;
    EditText username ,password ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.private_login);
        rigister = (Button) findViewById(R.id.signup);


    }
    public void goTOPrivatRegisterPage (View view ){
        Intent intent = new Intent(privateOnerLogIn.this , GoTOPrivatRegisterPage.class );
        startActivity(intent);

    }
}
