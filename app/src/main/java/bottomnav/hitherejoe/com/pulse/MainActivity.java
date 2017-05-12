package bottomnav.hitherejoe.com.pulse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import bottomnav.hitherejoe.com.pulse.fragment.AlarmFragment;
import bottomnav.hitherejoe.com.pulse.fragment.CareFragment;
import bottomnav.hitherejoe.com.pulse.fragment.MainFragment;
import bottomnav.hitherejoe.com.pulse.model.DBHelper;
import bottomnav.hitherejoe.com.pulse.model.Knowledge;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFrag(new MainFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.menu1:
                                setFrag(new MainFragment());
                                break;
                            case R.id.menu2:
                                setFrag(new CareFragment());
                                break;
                            case R.id.menu3:
                                setFrag(new AlarmFragment());
                                break;
                        }

                        return false;
                    }
                });
    }

    private void setFrag(Fragment frag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content, frag);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                // About option clicked.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
