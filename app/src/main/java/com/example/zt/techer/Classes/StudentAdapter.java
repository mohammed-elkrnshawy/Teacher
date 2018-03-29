package com.example.zt.techer.Classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.zt.techer.R;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by zt on 28/03/2018.
 */

public class StudentAdapter extends BaseAdapter {


        Context context;
        List<StudentClass> studentClassList;
        private LinearLayout.LayoutParams params;

        public StudentAdapter(Context context,
                              List<StudentClass> studentClassList)
        {
            this.context=context;
            this.studentClassList = studentClassList;
            params = new LinearLayout.
                    LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        }

        @Override
        public int getCount() {
            return studentClassList.size();
        }

        @Override
        public Object getItem(int i) {
            return studentClassList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return studentClassList.indexOf(getItem(i));
        }

        class ViewHolder
        {
            TextView Name;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder=null;

            LayoutInflater mIFlater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            View row=mIFlater.inflate(R.layout.view_student,viewGroup,false);

           holder=new ViewHolder();

            holder.Name=row.findViewById(R.id.TV);

            holder.Name.setText(studentClassList.get(i).getName());

            return row;
        }
    }

