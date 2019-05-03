package sofrosyn.tech.com.orpheus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private CardView resetButton;
    private TextView message;
    private EditText email;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_layout);
auth = FirebaseAuth.getInstance();
        resetButton = findViewById(R.id.resetButton);
        message = findViewById(R.id.resetMessage);
        email = findViewById(R.id.resetEmail);
        progressBar = findViewById(R.id.resetProgress);
        resetButton.setOnClickListener(v -> {
            String emailStr = email.getText().toString().trim();

            if (TextUtils.isEmpty(emailStr)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);


    auth.sendPasswordResetEmail(emailStr)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    message.setText("A reset email has been sent to your email");
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            });



        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
