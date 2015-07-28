package com.example.waqas.listviewsql;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Waqas on 6/1/2015.
 */
public class DetailsFragment extends ListFragment {

    TextView text,vers;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.details_fragment, container, false);
        text= (TextView) view.findViewById(R.id.AndroidOs);
        vers= (TextView)view.findViewById(R.id.Version);


        return view;
    }

    public void change(String txt, String txt1) {
        text.setText(txt);
        vers.setText(txt1);
    }
}
