package sofrosyn.tech.com.orpheus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SlashActivtity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_splash);
        startActivity(new Intent(SlashActivtity.this,IntroActivity.class));
        overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
    }
}
