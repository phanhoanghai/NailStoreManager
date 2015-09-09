package app1.phh.com.nailstoremanager.Jobs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import app1.phh.com.nailstoremanager.Contact.DisplayContact;
import app1.phh.com.nailstoremanager.R;
import app1.phh.com.nailstoremanager.database.SqlHelper;

/**
 * Created by HaiPhan on 9/8/2015.
 */
public class DisplayJob extends Activity {
    private SqlHelper mydb;
    private ListView listViewJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.jobs_layout);

        mydb = new SqlHelper(this);

        this.mapComponent();

        ArrayList arrayListJob = mydb.getAllJobs();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListJob);

        listViewJobs.setAdapter(arrayAdapter);
        listViewJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), DisplayContact.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

    }

    private void mapComponent(){
        listViewJobs = (ListView)findViewById(R.id.listViewJobs);
    }

}
