package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
private CardView joinClass, curriculum, feeds,bootCamp;
private int touch;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth= FirebaseAuth.getInstance();
        touch =0;

    joinClass = findViewById(R.id.joinClass);
    curriculum = findViewById(R.id.Curriculum);
    feeds = findViewById(R.id.feed);
    bootCamp = findViewById(R.id.bootCamp);



        joinClass.setOnClickListener((v)-> startActivity(new Intent(this, JoinClassActivity.class  )));
    curriculum.setOnClickListener((v)-> startActivity(new Intent(this, CurriculumActivity.class)));
        feeds.setOnClickListener((v)-> startActivity(new Intent(this, FeedsActivity.class  )));
        bootCamp.setOnClickListener((v)-> startActivity(new Intent(this, BootCampActivity.class)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

            if(id == R.id.message) {sendMail(); return true;}


            if(id == R.id.lock){
                touch++;
                Toast.makeText(MainActivity.this,Integer.toString(touch),Toast.LENGTH_SHORT).show();
                if(touch==5){
                    startActivity(new Intent(MainActivity.this,AdminActivity.class));
                    finish();} return  true;}

            if(id == R.id.signout){
                auth.signOut();
                startActivity(new Intent(this,LoginActivity.class));
             return true;
            }


   return super.onOptionsItemSelected(item);
    }

    private void sendMail(){

        String receiver= "wsmusicbootcamp@gmail.com";
        String cc   = "josephfidelis75@gmail.com";
        String subject = "Inquiry";
        String message ="We are open to any form of enquiry";
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setData(Uri.parse("mailto:"+ receiver)); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);    }



}



