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

import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by zt on 28/03/2018.
 */

public class TestAdapter extends BaseAdapter {


        Context context;
        List<TestClass> testClassList;
        private LinearLayout.LayoutParams params;

        public TestAdapter(Context context,
                           List<TestClass> studentClassList)
        {
            this.context=context;
            this.testClassList = studentClassList;
            params = new LinearLayout.
                    LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        }

        @Override
        public int getCount() {
            return testClassList.size();
        }

        @Override
        public Object getItem(int i) {
            return testClassList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return testClassList.indexOf(getItem(i));
        }

        class ViewHolder
        {
            TextView Name,Degree;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder=null;

            LayoutInflater mIFlater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            View row=mIFlater.inflate(R.layout.view_test,viewGroup,false);

           holder=new ViewHolder();

            holder.Name=row.findViewById(R.id.TV);
            holder.Degree=row.findViewById(R.id.TV1);

            holder.Name.setText(testClassList.get(i).getTestName());
            holder.Degree.setText(testClassList.get(i).getTestDegree()+"");

            return row;
        }
    }

