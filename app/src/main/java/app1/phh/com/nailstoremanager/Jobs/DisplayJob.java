package app1.phh.com.nailstoremanager.Jobs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

import app1.phh.com.nailstoremanager.R;
import app1.phh.com.nailstoremanager.database.SqlHelper;

/**
 * Created by HaiPhan on 9/8/2015.
 */
public class DisplayJob extends Activity {
    private SqlHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.jobs_layout);

        mydb = new SqlHelper(this);

        this.mapComponent();


    }

    private void mapComponent(){
    }

    private ArrayList<String> getTableValue() {
        ArrayList<String> arrayList = new ArrayList<String>();
        try{

        }catch (Exception ex){

        }
        return arrayList;
    }
}
