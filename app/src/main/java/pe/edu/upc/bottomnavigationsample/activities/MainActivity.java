package pe.edu.upc.bottomnavigationsample.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import pe.edu.upc.bottomnavigationsample.R;
import pe.edu.upc.bottomnavigationsample.fragments.DashboardFragment;
import pe.edu.upc.bottomnavigationsample.fragments.HomeFragment;
import pe.edu.upc.bottomnavigationsample.fragments.NotificationsFragment;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateAccordingTo(item.getItemId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateAccordingTo(R.id.navigation_home);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == ORIENTATION_LANDSCAPE){
            //TODO: Active some behavior
        }

    }

    private boolean navigateAccordingTo(int id){
        try{
            getSupportFragmentManager()
                    .beginTransaction().
                    replace(R.id.content,getFragmentFor(id))
                    .commit();

            return  true;

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

 private Fragment getFragmentFor(int id){
     switch(id){
         case R.id.navigation_home:
             return new HomeFragment();
         case R.id.navigation_dashboard:
             return  new DashboardFragment();
         case R.id.navigation_notifications:
             return new NotificationsFragment();
     }
     return null;

 }
}
