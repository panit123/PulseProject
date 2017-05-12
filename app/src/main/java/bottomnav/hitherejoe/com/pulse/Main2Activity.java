package bottomnav.hitherejoe.com.pulse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import bottomnav.hitherejoe.com.pulse.adapter.CustomAdapter;
import bottomnav.hitherejoe.com.pulse.model.DBHelper;

public class Main2Activity extends AppCompatActivity {
    DBHelper mHelper;
    List<String> notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mHelper = new DBHelper(this);
        notification = mHelper.getNotificationList();
        CustomAdapter adapter = new CustomAdapter(Main2Activity.this, notification);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Main2Activity.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
