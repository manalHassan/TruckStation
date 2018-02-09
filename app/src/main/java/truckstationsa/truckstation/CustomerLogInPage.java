package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by manal on 2/9/2018.
 */

public class CustomerLogInPage extends AppCompatActivity {
Button login  ;
TextView rigister ;
EditText username ,password ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.customer_log_in_page);
        rigister = (TextView) findViewById(R.id.rigister);


    }
    public void goTOCustomerRegisterPage (View view ){
        Intent intent = new Intent(CustomerLogInPage.this , goTOCustomerRegisterPage.class );
        startActivity(intent);

    }
}
