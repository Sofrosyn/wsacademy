package sofrosyn.tech.com.orpheus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class ProfileActivity extends AppCompatActivity {
private RelativeLayout container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_profile);

        container = findViewById(R.id.profile_container);
        container.setEnabled(false);
    }
}
