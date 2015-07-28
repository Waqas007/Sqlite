package com.example.waqas.listviewsql;

import android.app.Activity;
import android.view.View;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Waqas on 5/27/2015.
 */
public class AddActivity extends Activity implements View.OnClickListener {

    private Button btn_save;
    private EditText edit_first,edit_last;
    private DbHelper mHelper;
    private SQLiteDatabase dataBase;
    private String id,mname,myear;
    private boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_layout);

        btn_save=(Button)findViewById(R.id.save_btn);
        edit_first=(EditText)findViewById(R.id.frst_editTxt);
        edit_last=(EditText)findViewById(R.id.last_editTxt);

        isUpdate=getIntent().getExtras().getBoolean("update");
        if(isUpdate)
        {
            id=getIntent().getExtras().getString("ID");
            mname=getIntent().getExtras().getString("Mname");
            myear=getIntent().getExtras().getString("Myear");
            edit_first.setText(mname);
            edit_last.setText(myear);

        }

        btn_save.setOnClickListener(this);

        mHelper=new DbHelper(this);

    }

    // saveButton click event
    public void onClick(View v) {
        mname=edit_first.getText().toString().trim();
        myear=edit_last.getText().toString().trim();
        if(mname.length()>0 && myear.length()>0)
        {
            saveData();
        }
        else
        {
            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(AddActivity.this);
            alertBuilder.setTitle("Invalid Data");
            alertBuilder.setMessage("Please, Enter valid data");
            alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            alertBuilder.create().show();
        }

    }

    /**
     * save data into SQLite
     */
    private void saveData(){
        dataBase=mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DbHelper.KEY_MNAME,mname);
        values.put(DbHelper.KEY_MYEAR,myear );

        System.out.println("");
        if(isUpdate)
        {
            //update database with new data
            dataBase.update(DbHelper.TABLE_NAME, values, DbHelper.KEY_ID+"="+id, null);
        }
        else
        {
            //insert data into database
            dataBase.insert(DbHelper.TABLE_NAME, null, values);
        }
        //close database
        dataBase.close();
        finish();


    }
}
