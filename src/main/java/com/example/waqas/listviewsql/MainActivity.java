package com.example.waqas.listviewsql;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends FragmentActivity {


    private DbHelper mHelper;
    private SQLiteDatabase dataBase;

    private ArrayList<String> userId = new ArrayList<String>();
    private ArrayList<String> user_mName = new ArrayList<String>();
    private ArrayList<String> user_mYear = new ArrayList<String>();
    HashMap<String,String> details=new HashMap<String,String>();

    private ListView userList;
    private AlertDialog.Builder build;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = (ListView) findViewById(R.id.List);

        mHelper = new DbHelper(this);

        //add new record
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),
                        AddActivity.class);
                i.putExtra("update", false);
                startActivity(i);

            }
        });

        //click to update data
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {



                Bundle b = new Bundle();


                Intent i = new Intent(getApplicationContext(),
                        AddActivity.class);
                i.putExtra("Mname", user_mName.get(arg2));
                i.putExtra("Myear", user_mYear.get(arg2));
                i.putExtra("ID", userId.get(arg2));
                i.putExtra("update", true);
                startActivity(i);



            }
        });

        //long click to delete data
        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int arg2, long arg3) {

                build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Delete " + user_mName.get(arg2) + " "
                        + user_mYear.get(arg2));
                build.setMessage("Do you want to delete ?");
                build.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Toast.makeText(
                                        getApplicationContext(),
                                        user_mName.get(arg2) + " "
                                                + user_mYear.get(arg2)
                                                + " is deleted.", Toast.LENGTH_LONG).show();

                                dataBase.delete(
                                        DbHelper.TABLE_NAME,
                                        DbHelper.KEY_ID + "="
                                                + userId.get(arg2), null);
                                displayData();
                                dialog.cancel();
                            }
                        });

                build.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }

    /**
     * displays data from SQLite
     */
    private void displayData() {
        dataBase = mHelper.getWritableDatabase();
        Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
                + DbHelper.TABLE_NAME, null);

        userId.clear();
        user_mName.clear();
        user_mYear.clear();
        if (mCursor.moveToFirst()) {
            do {
                userId.add(mCursor.getString(mCursor.getColumnIndex(DbHelper.KEY_ID)));
                user_mName.add(mCursor.getString(mCursor.getColumnIndex(DbHelper.KEY_MNAME)));
                user_mYear.add(mCursor.getString(mCursor.getColumnIndex(DbHelper.KEY_MYEAR)));

            } while (mCursor.moveToNext());
        }
        DisplayAdapter disadpt = new DisplayAdapter(MainActivity.this,userId, user_mName, user_mYear);
        userList.setAdapter(disadpt);
        mCursor.close();
    }


}
