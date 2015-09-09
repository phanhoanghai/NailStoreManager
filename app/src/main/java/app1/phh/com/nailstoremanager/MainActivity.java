package app1.phh.com.nailstoremanager;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.KeyEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import app1.phh.com.nailstoremanager.Common.StaticVar;
import app1.phh.com.nailstoremanager.Contact.DisplayContact;
import app1.phh.com.nailstoremanager.Jobs.DisplayJob;
import app1.phh.com.nailstoremanager.database.SqlHelper;

public class MainActivity extends Activity {
    private ListView obj;
    SqlHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StaticVar.showSaveButton = false;

        mydb = new SqlHelper(this);
        ArrayList array_list = mydb.getAllContacts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);

        obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        Button button_add_ct = (Button) findViewById(R.id.button_add_contact);
        button_add_ct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticVar.showSaveButton = true;
                Toast.makeText(MainActivity.this, "Add new contact", Toast.LENGTH_LONG).show();
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    public void onClickJobs(View v){
        Intent intent = new Intent(getApplicationContext(), DisplayJob.class);
        startActivity(intent);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
