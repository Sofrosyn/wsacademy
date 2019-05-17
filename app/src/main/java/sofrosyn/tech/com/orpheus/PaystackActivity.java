package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PaystackActivity extends AppCompatActivity {
    private EditText cardNumber, month, year, cvv;
    private String costIntent,instrumentIntent, nameIntent;
    private Button pay;
    private ProgressBar progressBar;
    private String ScardNumber,Scvv;
    private int Smonth,Syear;

    private Charge charge;
    private Card card;

    private final String email  = "wsbootcamp@gmail.com";
    private FirebaseAuth mAuth;
    private TextView message;
    private static final String TAG="WSACADEMY LOGGER";
    private DatabaseReference mDataref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize paystack
        PaystackSdk.initialize(getApplicationContext());

        PaystackSdk.setPublicKey(getResources().getString(R.string.test_publicKey));

        setContentView(R.layout.payment_transaction_layout);

    //inintalize firebase authentication
    mAuth=FirebaseAuth.getInstance();
    //initialize firebase database
    mDataref=FirebaseDatabase.getInstance().getReference();

    pay = findViewById(R.id.paystack_pay);

    message = findViewById(R.id.paystack_message);

    cardNumber= findViewById(R.id.cardNumber);
    month= findViewById(R.id.expMonth);
    year= findViewById(R.id.expYear);
    cvv= findViewById(R.id.cvvNumber);

    progressBar = findViewById(R.id.paystack_progressbar);

    Intent receive = getIntent();
    nameIntent = receive.getStringExtra("name");
    instrumentIntent = receive.getStringExtra("choice");
    costIntent = receive.getStringExtra("payment");

pay.setOnClickListener((v)->{

        if(!validate()){return;}
    progressBar.setVisibility(View.VISIBLE);

        try{
            ScardNumber = cardNumber.getText().toString().trim();
            Scvv = cvv.getText().toString().trim();
            Smonth = Integer.parseInt(month.getText().toString().trim());
            Syear = Integer.parseInt(year.getText().toString().trim());

            card= new Card(ScardNumber,Smonth,Syear,Scvv);

            // String cardType = card.getType();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage(nameIntent +" /n"+instrumentIntent+" /n"+ costIntent);
            alertDialog.setTitle("Attention");
            alertDialog.setIcon(R.drawable.idea);
            alertDialog.setPositiveButton("Yes", (dialog, which) -> {
             if(card.isValid()){

        performCharge();


    }
});
 alertDialog.setNegativeButton("No",((dialog, which) -> { }));
}catch(Exception e){e.printStackTrace();}
        });
    }

// editfield validation

    private boolean validate(){
        boolean valid = true;
        if(TextUtils.isEmpty(cardNumber.getText().toString().trim())){
            cardNumber.setError("required");
            cardNumber.getText().clear();
        }else{cardNumber.setError(null);}

        if(TextUtils.isEmpty(month.getText().toString().trim())){
            month.setError("required");
            month.getText().clear();
        }else{month.setError(null);}

        if(TextUtils.isEmpty(year.getText().toString().trim())){
          year.setError("required");
          year.getText().clear();
        } else{year.setError(null); }

    if(TextUtils.isEmpty(cvv.getText().toString().trim())){
        cvv.setError("required");
        cvv.getText().clear();
    } else{cvv.setError(null); }

    return valid;
    }

    //paystack card charge
    private void performCharge(){
       charge = new Charge();
       charge.setCard(card);
       charge.setEmail(email);
       charge.setAmount(Integer.parseInt(costIntent));
       PaystackSdk.chargeCard(PaystackActivity.this, charge, new Paystack.TransactionCallback() {

           @Override
           public void onSuccess(Transaction transaction) {
            String userId = mAuth.getUid();
            //mDataref.child("Transaction").child(userId).setValue(transaction.getReference());
               makeToast("the card has been debited successfully");
            progressBar.setVisibility(View.INVISIBLE);
            makeToast(transaction.getReference());
               Log.i(TAG,transaction.getReference());
               message.setText(transaction.getReference());
           }

           @Override
           public void beforeValidate(Transaction transaction) {

           }

           @Override
           public void onError(Throwable error, Transaction transaction) {
               progressBar.setVisibility(View.INVISIBLE);
               message.setText(transaction.getReference());
               message.setText(error.getMessage());


               Log.i(TAG,error.getMessage());
               Log.i(TAG,transaction.getReference());
           }
       });

    }



private void makeToast(String message){
    Toast.makeText(PaystackActivity.this, message,Toast.LENGTH_SHORT).show();
}





    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);

    }
}
