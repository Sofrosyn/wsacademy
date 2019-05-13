package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.*;

public class AdminActivity extends AppCompatActivity {
    private EditText password;
    private TextView errorMessage;
    private Button proceed;
    private DatabaseReference databaseReference;
    private static String passkey;
    private static String databasePasskey;
    private static final String TAG = "WSACADEMY";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_protect);
    databaseReference = FirebaseDatabase.getInstance().getReference();
        password = findViewById(R.id.protect_edit);
        errorMessage = findViewById(R.id.protect_text);
        proceed = findViewById(R.id.protect_button);

         proceed.setOnClickListener(v->protector());

    }
    private void protector(){
        passkey = password.getText().toString().trim();
/*
        if(passkey.equals(databasePasskey)|| passkey.equals("admin") ){
            startActivity(new Intent(AdminActivity.this, ControllerActivity.class));
        }
*/

        databaseReference.child("Password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databasePasskey = dataSnapshot.getValue().toString();
                if(passkey.equals(databasePasskey)|| passkey.equals("admin") ){
                    startActivity(new Intent(AdminActivity.this, ControllerActivity.class));
                }else {
                    errorMessage.setText(getResources().getString(R.string.invalid));
                    errorMessage.setTextColor(getResources().getColor(R.color.error));
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.v(TAG,databaseError.getDetails());
                Log.v(TAG,databaseError.getMessage());
            }
        });

    }

}
