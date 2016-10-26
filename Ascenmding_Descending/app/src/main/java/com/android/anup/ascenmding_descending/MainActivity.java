package com.android.anup.ascenmding_descending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    LAdapter madapter;
    public ArrayList<String> months=new ArrayList<>();

    //element to be displayed in list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populate();
        }

    public void populate() {
        Button ascbutton = (Button) findViewById(R.id.buttonascending);
        ascbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                asc();
                madapter.notifyDataSetChanged();
            }
        });
        Button descbutton = (Button) findViewById(R.id.buttondescending);
        descbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                desc();
                madapter.notifyDataSetChanged();
            }
        });

        final ListView listView = (ListView) findViewById(R.id.monthslistview);
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        madapter = new LAdapter();
        listView.setAdapter(madapter);
        listView.setClickable(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String value = (String)listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Position " + position + " Month " +value, Toast.LENGTH_SHORT).show();
            }
        });
    }
    class LAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return months.size();

        }

        @Override
        public Object getItem(int position) {
            return months.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.monthsrowview,parent,false);
                holder = new ViewHolder();
                holder.bindView(convertView);
                convertView.setTag(holder);
                Log.e("Main_ACTIVITY","convertView is NULL");
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView1.setText(months.get(position));
            return convertView;

        }
    }

    private class ViewHolder{
        TextView textView1;

        void bindView(View convertView) {
            textView1 = (TextView)convertView.findViewById(R.id.monthsrowViewtext);
        }
    }

    public void asc(){
        Collections.sort(months);

    }

    public void desc(){
        Collections.sort(months, Collections.reverseOrder());

    }
}

