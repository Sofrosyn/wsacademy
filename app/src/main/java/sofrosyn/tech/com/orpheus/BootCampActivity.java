package sofrosyn.tech.com.orpheus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class BootCampActivity extends AppCompatActivity {
    private LinearLayout core;
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.bootcamp_layout);
core = findViewById(R.id.linearCore);

core.setEnabled(false);








}

}
