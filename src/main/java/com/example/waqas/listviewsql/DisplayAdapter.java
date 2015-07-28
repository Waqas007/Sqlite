package com.example.waqas.listviewsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// adapter to populate listview with data
/**
 * Created by Waqas on 5/27/2015.
 */
public class DisplayAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> id;
    private ArrayList<String> movieName;
    private ArrayList<String> movieYear;


    public DisplayAdapter(Context c, ArrayList<String> id,ArrayList<String> mname, ArrayList<String> myear) {
        this.mContext = c;

        this.id = id;
        this.movieName = mname;
        this.movieYear = myear;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return id.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listcell_layout, null);
            mHolder = new Holder();
            mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
            mHolder.txt_fName = (TextView) child.findViewById(R.id.txt_fName);
            mHolder.txt_lName = (TextView) child.findViewById(R.id.txt_lName);
            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }
        mHolder.txt_id.setText(id.get(pos));
        mHolder.txt_fName.setText(movieName.get(pos));
        mHolder.txt_lName.setText(movieYear.get(pos));

        return child;
    }

    public class Holder {
        TextView txt_id;
        TextView txt_fName;
        TextView txt_lName;
    }

}
