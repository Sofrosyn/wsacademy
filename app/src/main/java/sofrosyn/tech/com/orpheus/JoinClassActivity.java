package sofrosyn.tech.com.orpheus;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import sofrosyn.tech.com.orpheus.modals.Classes;

import static android.widget.Toast.LENGTH_SHORT;

public class JoinClassActivity extends AppCompatActivity {
private EditText firstName,lastName,addressClass,ageClass,phoneClass,nameGuard,addressGuard,numberGuard,healthGuard;
private Spinner instruments,owner;
private Button proceed;
private AlertDialog.Builder builder;
private TextView cost;
ProgressBar progressBar;
private DatabaseReference databaseReference;
private FirebaseAuth auth;
private final String classNode= "JoinClass";
 //     private TextView daName,daInstrument,daChoice,diaCost;
 //       private Button cancel,pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_layout);




        Introdialog();

        // initialize firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        progressBar = findViewById(R.id.classProgressBar);
        cost =findViewById(R.id.showPrice);
        firstName= findViewById(R.id.classFname);
        lastName = findViewById(R.id.classLname);
        addressClass = findViewById(R.id.classAddress);
        ageClass = findViewById(R.id.classAge);
        phoneClass = findViewById(R.id.classPhone);
        proceed = findViewById(R.id.classProceed);


        instruments = findViewById(R.id.spinInstrument);
        owner = findViewById(R.id.spinOwner);

        nameGuard = findViewById(R.id.classGuardianName);
        addressGuard = findViewById(R.id.classGuardianAddress);
        numberGuard = findViewById(R.id.classGuardianNumber);
        healthGuard = findViewById(R.id.healthGuardian);

        owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String choice = owner.getSelectedItem().toString();
                if(choice.equals("Yes")){
                    cost.setText("30,000");
                }else
                { cost.setText("40,000");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        healthGuard.setText(null);





        proceed.setOnClickListener((v)-> {
    if (!validateForm()) { return; }
    progressBar.setVisibility(View.VISIBLE);
Intent pass = new Intent(JoinClassActivity.this,PaystackActivity.class);
    String name = firstName.getText().toString();
    String last= lastName.getText().toString();
    String choice = instruments.getSelectedItem().toString();
    String payment = cost.getText().toString();
    pass.putExtra("name",last+ " "+name);
    pass.putExtra("choice",choice);
    pass.putExtra("payment",payment);
commitToFirebase();
    Toast.makeText(this,"Payment activity",LENGTH_SHORT).show();
    startActivityForResult(pass,1);
} );


  //  dialog = new Dialog(this);



    }
    private boolean validateForm()  {
        boolean result = true;
        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("Required");
            result = false;
        } else {
            firstName.setError(null);
        }

        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("Required");
            result = false;
        } else {
            lastName.setError(null);
        }
        if (TextUtils.isEmpty(addressClass.getText().toString())) {
            addressClass.setError("Required");
            result = false;
        } else {
            addressClass.setError(null);
        }
        if (TextUtils.isEmpty(ageClass.getText().toString())) {
            ageClass.setError("Required");
            result = false;
        } else {
            ageClass.setError(null);
        }

        if (TextUtils.isEmpty(nameGuard.getText().toString())) {
            nameGuard.setError("Required");
            result = false;
        } else {
            nameGuard.setError(null);
        }

        if (TextUtils.isEmpty(addressGuard.getText().toString())) {
            addressGuard.setError("Required");
            result = false;
        } else {
            addressGuard.setError(null);
        }
        if (TextUtils.isEmpty(numberGuard.getText().toString())) {
            numberGuard.setError("Required");
            result = false;
        } else {
            numberGuard.setError(null);
        }


            return result;
    }

    public void commitToFirebase(){

        String fname;
        String lname;
        int age;
        String address;
        int phoneNumber;
        String instrument;
        String owners;
        String health;
        String guardianName;
        String guardianAddress;
        int guardianNumber;

        fname = firstName.getText().toString();
        lname = lastName.getText().toString();
        age = Integer.parseInt(ageClass.getText().toString());
        address = addressClass.getText().toString();
        phoneNumber = Integer.parseInt(phoneClass.getText().toString());
        instrument = instruments.getSelectedItem().toString();
        owners = owner.getSelectedItem().toString();
        health = healthGuard.getText().toString();
        guardianName = nameGuard.getText().toString();
        guardianAddress = addressGuard.getText().toString();
        guardianNumber= Integer.parseInt(numberGuard.getText().toString());
try {
    Classes classes = new Classes(fname,lname,age,address,phoneNumber,instrument,owners,health,guardianName,guardianAddress,guardianNumber);
    String userId = auth.getUid();
    String key = databaseReference.push().getKey();
    databaseReference.child(classNode).child(userId).child(key).setValue(classes);
    Toast.makeText(this,"saved to base",LENGTH_SHORT).show();

}catch (NullPointerException e){
    Toast.makeText(this,"no internet Connection",LENGTH_SHORT).show();
}


//  Todo: catch null pointer exception

    }
private void Introdialog(){
builder = new AlertDialog.Builder(this);
builder.setTitle("Welcome to symphony");
builder.setIcon(R.drawable.ic_audiotrack_black_24dp);
builder.setMessage("we offer discounts if you choose to register more that on person");
builder.setNeutralButton("OK", (dialog, which) -> {

});
builder.show();
}

    @Override
    protected void onResume() {
        super.onResume();
progressBar.setVisibility(View.GONE);
    }
}

