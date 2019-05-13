package sofrosyn.tech.com.orpheus.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;
import sofrosyn.tech.com.orpheus.R;
import sofrosyn.tech.com.orpheus.adapters.FeedsAdapter;
import sofrosyn.tech.com.orpheus.modals.Feeds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FeedsFragments extends Fragment {

private FloatingActionButton floatingActionButton;
    private DatabaseReference databaseReference;
    private List<Feeds> list;
    private EditText message;
    private Calendar calendar;
    private String date;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.controller_feeds_fragments, container, false);

        message = root.findViewById(R.id.FeedMessage);
        floatingActionButton = root.findViewById(R.id.controllerFeed_floatbutton);

        databaseReference = FirebaseDatabase.getInstance().getReference();





        calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String[] MONTH_STRING = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
        date = day + " " + MONTH_STRING[month];

        floatingActionButton.setOnClickListener( (v)->{
            String text = message.getText().toString();
            Feeds feeds = new Feeds("Admin",text,date);
            databaseReference.child("Feeds").child(databaseReference.push().getKey()).setValue(feeds);
            Toast.makeText(getActivity(),"sent",Toast.LENGTH_SHORT).show();
            Log.v("WS",databaseReference.push().getKey());
            message.getText().clear();


        });

/*

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Feeds value = dataSnapshot1.getValue(Feeds.class);
                    String subject = value.getSubject();
                    String message = value.getMessage();
                    String date= value.getDate();

                    Feeds fire = new Feeds(subject,message,date);
                    list.add(fire);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


*/

        return root;
    }

    private void sendFeed(){

    }








    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
