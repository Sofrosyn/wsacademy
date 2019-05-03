package sofrosyn.tech.com.orpheus;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.TextView;

public class CurriculumActivity extends AppCompatActivity {

private CardView drei1,drei2,drei3,drei4,drei5,drei6,
        drei7,drei8,drei9,drei10,drei11,drei12;
private TextView title, context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curriculum_layout);

        title = findViewById(R.id.CurriculumTitle);
        context = findViewById(R.id.CurriculumConcept);


        drei1 = findViewById(R.id.drei1);
        drei2 = findViewById(R.id.drei2);
        drei3 = findViewById(R.id.drei3);
        drei4 = findViewById(R.id.drei4);
        drei5 = findViewById(R.id.drei5);
        drei6 = findViewById(R.id.drei6);
        drei7 = findViewById(R.id.drei7);
        drei8 = findViewById(R.id.drei8);
        drei9 = findViewById(R.id.drei9);
        drei10 = findViewById(R.id.drie10);
        drei11 = findViewById(R.id.drie11);
        drei12 = findViewById(R.id.drei12);


        drei1.setOnClickListener((v)->{
          title.setText(getResources().getString(R.string.drei1_title));
          context.setText(getResources().getString(R.string.drei1_context));
        });

        drei2.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei2_title));
            context.setText(getResources().getString(R.string.drei2_context));
        });

        drei3.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei3_title));
            context.setText(getResources().getString(R.string.drei3_context));
        });

        drei4.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei4_title));
            context.setText(getResources().getString(R.string.drei4_context));
        });

        drei5.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei5_title));
            context.setText(getResources().getString(R.string.drei5_context));

        });

        drei6.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei6_title));
            context.setText(getResources().getString(R.string.drei6_context));

        });

        drei7.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei7_title));
            context.setText(getResources().getString(R.string.drei7_context));

        });

        drei8.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei8_title));
            context.setText(getResources().getString(R.string.drei8_context));

        });

        drei9.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei9_title));
            context.setText(getResources().getString(R.string.drei9_context));
        });

        drei10.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.drei10_title));
            context.setText(getResources().getString(R.string.drei10_context));
        });

        drei11.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.private_tutoring));
            context.setText(getResources().getString(R.string.private_context));
        });

        drei12.setOnClickListener((v)->{
            title.setText(getResources().getString(R.string.info));
            context.setText(getResources().getString(R.string.info_context));

        });





 }




}


