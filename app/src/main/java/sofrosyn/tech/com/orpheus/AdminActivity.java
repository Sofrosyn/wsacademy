package sofrosyn.tech.com.orpheus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {
    private EditText password;
    private TextView errorMessage;
    private Button proceed;
    private DatabaseReference databaseReference;
    private static String passkey;
    private static String databasePasskey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_protect);
    databaseReference = FirebaseDatabase.getInstance().getReference();
        password = findViewById(R.id.protect_edit);
        errorMessage = findViewById(R.id.protect_text);
        proceed = findViewById(R.id.protect_button)
    }

}
