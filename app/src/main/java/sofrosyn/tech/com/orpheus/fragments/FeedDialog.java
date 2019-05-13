package sofrosyn.tech.com.orpheus.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import sofrosyn.tech.com.orpheus.R;
import sofrosyn.tech.com.orpheus.modals.Feeds;

import java.util.Calendar;

public class FeedDialog {

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_feed);

        EditText message= dialog.findViewById(R.id.popFeedMessage);

        ImageButton send = dialog.findViewById(R.id.popFeedSend);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
       final String dSubjet= "Admin";
        String dMessage= message.getText().toString();
        Calendar calendar;
        calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String[] MONTH_STRING = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
        String date = day + " " + MONTH_STRING[month];




    send.setOnClickListener((v)-> {

        dialog.dismiss();
    }

        );

    dialog.show();
    }
}
