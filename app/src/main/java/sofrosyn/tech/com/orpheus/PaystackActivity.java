package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    private TextView name, insrtument, cost;
    private Button pay;
    private ProgressBar progressBar;
    private String ScardNumber,Scvv;
    private int Smonth,Syear;

    private Charge charge;
    private Card card;

    private final String email  = "wsbootcamp@gmail.com";
    private FirebaseAuth mAuth;

    private DatabaseReference mDataref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize paystack
        PaystackSdk.initialize(getApplicationContext());

        setContentView(R.layout.payment_transaction_layout);

    //inintalize firebase authentication
    mAuth=FirebaseAuth.getInstance();
    //initialize firebase database
    mDataref=FirebaseDatabase.getInstance().getReference();
   pay = findViewById(R.id.payPaystack);
//    name = findViewById(R.id.pay_name);
   // insrtument= findViewById(R.id.pay_instrument);
   // cost= findViewById(R.id.pay_cost);
    cardNumber= findViewById(R.id.cardNumber);
    month= findViewById(R.id.expMonth);
    year= findViewById(R.id.expYear);
    cvv= findViewById(R.id.cvvNumber);
    progressBar = findViewById(R.id.paystackProgress);

    Intent receive = getIntent();
    String nameIntent = receive.getStringExtra("name");
    String instrumentIntent = receive.getStringExtra("choice");
    String costIntent = receive.getStringExtra("payment");

  //  name.setText(nameIntent);
   // insrtument.setText(instrumentIntent);
  //  cost.setText(costIntent);

    pay.setOnClickListener((v)->{

        if(!validate()){return;}

        try{
            ScardNumber = cardNumber.getText().toString().trim();
            Scvv = cvv.getText().toString().trim();
            Smonth = Integer.parseInt(month.getText().toString().trim());
            Syear = Integer.parseInt(year.getText().toString().trim());

            card= new Card(ScardNumber,Smonth,Syear,Scvv);
            String cardType = card.getType();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage(nameIntent +"/n"+instrumentIntent+"/n"+ costIntent+"/n"+cardType);
            alertDialog.setTitle("Attention");
            alertDialog.setIcon(R.drawable.idea);
alertDialog.setPositiveButton("Yes", (dialog, which) -> {
             if(card.isValid()){
        progressBar.setVisibility(View.VISIBLE);

        performCharge();

        makeToast("the card has been debited successfully");
    }progressBar.setVisibility(View.INVISIBLE);
});
 alertDialog.setNegativeButton("No",((dialog, which) -> {}));
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
       charge.setAmount(Integer.parseInt(cost.getText().toString().trim()));
       PaystackSdk.chargeCard(PaystackActivity.this, charge, new Paystack.TransactionCallback() {
           @Override
           public void onSuccess(Transaction transaction) {
            String userId = mAuth.getUid();
            mDataref.child("Transaction").child(userId).setValue(transaction.getReference());
               makeToast("stored in firebase");
            progressBar.setVisibility(View.INVISIBLE);
            makeToast("Transaction successful");
           }

           @Override
           public void beforeValidate(Transaction transaction) {

           }

           @Override
           public void onError(Throwable error, Transaction transaction) {
                makeToast("transaction Unsuccessful");
           }
       });

    }



private void makeToast(String message){
    Toast.makeText(PaystackActivity.this, message,Toast.LENGTH_SHORT).show();
}


// TODO: add paystack public key to manifest


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
