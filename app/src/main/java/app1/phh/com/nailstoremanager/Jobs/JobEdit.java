package app1.phh.com.nailstoremanager.Jobs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Time;
import java.util.Date;

import app1.phh.com.nailstoremanager.R;
import app1.phh.com.nailstoremanager.database.SqlHelper;

/**
 * Created by bri on 9/9/2015.
 */
public class JobEdit extends Activity {
    int id_To_Update = 0;
    Time now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_display_jobs);
        id_To_Update = (int) new Date().getTime();
    }

    public void JobSave(View view) {
        EditText mTitle   = (EditText)findViewById(R.id.edit_text_job_tile);
        EditText mTime   = (EditText)findViewById(R.id.edit_text_job_time);
        SqlHelper mydb = new SqlHelper(this);
        mydb.insertJobs(mTitle.getText().toString(), mTime.getText().toString());

        Intent intent = new Intent(getApplicationContext(), DisplayJob.class);
        startActivity(intent);
    }
}
