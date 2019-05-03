package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import sofrosyn.tech.com.orpheus.modals.User;


public class SignUpActivity extends AppCompatActivity {
    private static final String REQUIRED = "Required";
    private static final String TAG = "SignupActivity";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText email, password,  username;
    private ProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //[set layout]
            setContentView(R.layout.layout_signup);

            //Get Firebase auth instance
            mAuth = FirebaseAuth.getInstance();
            // Get Firebase database
            mDatabase = FirebaseDatabase.getInstance().getReference();

            email = findViewById(R.id.signupEmail);
            password = findViewById(R.id.signupPassword);
            username = findViewById(R.id.signupUsername);
            progressBar = findViewById(R.id.signupProgressBar);

            username.setEnabled(false);

        }

// Edit field validation

    private boolean validateForm(){
         boolean  result = true;
        String getEmail = email.getText().toString().trim();
        String getPassword = password.getText().toString().trim();



        if (TextUtils.isEmpty(getEmail)){
            email.setError(REQUIRED);
            result = false;
        }else{
            email.setError(null);
            username.setText(UserNameFromEmail(getEmail));

        }
        if (TextUtils.isEmpty(getPassword) ){
            password.setError(REQUIRED);
            result =false;
        }else{password.setError(null);}

        if(getPassword.length() < 6){
            password.setError("weak password");
            result = false;
        }else{password.setError(null);}


return result;
        }



        // launches login activity
    public void loginHandler (View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();       }


    public void signupHandler (View view) {

    if(!validateForm()){
        Log.v(TAG, "not validated");
        return;
    }
            progressBar.setVisibility(View.VISIBLE);
         String getEmails = email.getText().toString().trim();
        String getPassword = password.getText().toString().trim();



/*
    mAuth.createUserWithEmailAndPassword(getEmails, getPassword)
            .addOnCompleteListener(this, task -> {


                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.INVISIBLE);
//                    mDatabase.child("users").child(userId).setValue(user);
                    Toast.makeText(SignUpActivity.this, " Signup successful saved to base", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(SignUpActivity.this, " Signup Failed", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            });     **/
      progressBar.setVisibility(View.VISIBLE);

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(getEmails, getPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            onAuthSuccess(task.getResult().getUser());                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Failed to signup",Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        progressBar.setVisibility(View.INVISIBLE);
                        // [END_EXCLUDE]
                    }
                });

    } //[END OF LAUNCH ACTIVITY]

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume () {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        mDatabase.child("users").child(userId).setValue(user);

    }
    private void onAuthSuccess(FirebaseUser user) {
        String username = UserNameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }

    // obtain username from email address
    private String UserNameFromEmail(String email){
        if(email.contains("@")){
            return email.split("@")[0];
        }else {return email;}

    }


}