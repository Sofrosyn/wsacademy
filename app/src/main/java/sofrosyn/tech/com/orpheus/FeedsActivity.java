package sofrosyn.tech.com.orpheus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.google.firebase.database.*;
import sofrosyn.tech.com.orpheus.adapters.FeedsAdapter;
import sofrosyn.tech.com.orpheus.modals.Feeds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FeedsActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;
    private List<Feeds> list;
    private RecyclerView recycle;
    private Calendar calendar;

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.child("Feeds").addValueEventListener(new ValueEventListener() {
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

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_feeds);
      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        recycle = findViewById(R.id.studentFeedsRecycler);
        recycle.setHasFixedSize(true);
        databaseReference= FirebaseDatabase.getInstance().getReference();



        list = new ArrayList<Feeds>();
        FeedsAdapter feedsAdapter = new FeedsAdapter(list,FeedsActivity.this);

         RecyclerView.LayoutManager recyce = new LinearLayoutManager(this);
        // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycle.setLayoutManager(recyce);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(feedsAdapter);
        calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String[] MONTH_STRING = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
        String date = day + " " + MONTH_STRING[month];

            Feeds feeds = new Feeds("Introduction","welcome to wsacademy", date);
            list.add(feeds);












    }}