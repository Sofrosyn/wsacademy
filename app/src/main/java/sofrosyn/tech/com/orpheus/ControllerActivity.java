package sofrosyn.tech.com.orpheus;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import sofrosyn.tech.com.orpheus.adapters.BottomNavigationBehavior;
import sofrosyn.tech.com.orpheus.fragments.AssessmentFragment;
import sofrosyn.tech.com.orpheus.fragments.ClassFragment;
import sofrosyn.tech.com.orpheus.fragments.FeedsFragments;

public class ControllerActivity extends AppCompatActivity {
private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controler_activity);


         toolbar = findViewById(R.id.controllerToolbar);
        setSupportActionBar(toolbar);



        BottomNavigationView navigation =  findViewById(R.id.bottomNav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        loadFragment(new FeedsFragments());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.controlFeed:
                    toolbar.setTitle("Feeds");
                    fragment = new FeedsFragments();
                    loadFragment(fragment);
                    return true;
                case R.id.controlClass:
                    toolbar.setTitle("Class");
                fragment = new ClassFragment();
                loadFragment(fragment);
                    return true;
                case R.id.controlAsssessment:
                    toolbar.setTitle("Assessment");
                    fragment = new AssessmentFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.controllerHolder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}



