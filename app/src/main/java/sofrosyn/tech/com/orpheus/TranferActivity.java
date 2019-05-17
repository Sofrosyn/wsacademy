package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

public class TranferActivity extends AppCompatActivity {
    private CardView transfer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_transfer);

        transfer = findViewById(R.id.transfer_pay);
        transfer.setOnClickListener((v)-> sendMail());

    }
    private void sendMail(){

        String receiver= "wsmusicbootcamp@gmail.com";
        String cc   = "josephfidelis75@gmail.com";
        String subject = "Confirmation of payment";
        String message ="Attach a snapshot of payment and the full name of the applicant";
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setData(Uri.parse("mailto:"+ receiver)); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);    }

}
