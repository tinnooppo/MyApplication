package com.example.arashi.myapplication.Activity;

/**
 * Created by Arashi on 2/21/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassAdapter extends BaseAdapter {
    Context mContext;
    String[] strName;
//    int[] resId;

    public ClassAdapter(Context context, String[] strName) {
        this.mContext= context;
        this.strName = strName;
//        this.resId = resId;
    }

    public int getCount() {
        return strName.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.class_list_view, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(strName[position]);


//        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
//        imageView.setBackgroundResource(resId[position]);

        return view;
    }
}