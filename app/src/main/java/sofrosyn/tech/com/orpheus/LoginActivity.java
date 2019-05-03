package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "SignInActivity";


    private FirebaseAuth mAuth;
    private ProgressBar progress;
    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView resetPassword;
    private CardView cardView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Instanciate firebase authentication
        mAuth = FirebaseAuth.getInstance();

        // check if user is logged in
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
        }

        setContentView(R.layout.layout_signin);

        progress = findViewById(R.id.loginProgressBar);
        mEmailField = findViewById(R.id.signinEmail);
        mPasswordField = findViewById(R.id.signinPassword);
        resetPassword = findViewById(R.id.forgotPassword);
        cardView = findViewById(R.id.signinButton);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        cardView.startAnimation(animation);

        resetPassword.setOnClickListener((v -> {
            startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            overridePendingTransition(R.anim.right_in,R.anim.left_out);
        }));
           }


    //sign up handler
    public void signupHandler(View view) {
        startActivity(
                new Intent(LoginActivity.this, SignUpActivity.class));
              overridePendingTransition(R.anim.right_in,R.anim.left_out);  }

    // login handler
    public void loginHandler(View view) {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                    hideProgressDialog();

                    if (task.isSuccessful()) {

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Sign In Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


//launch forget xml
    public void forgotHandler(View view) {
    }




    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmailField.getText().toString())) {
            mEmailField.setError("Required");
            result = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        return result;
    }


    @Override
    protected void onResume() {
        super.onResume();
        progress.setVisibility(View.GONE);
    }
}
