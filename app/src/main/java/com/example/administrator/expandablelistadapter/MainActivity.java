package com.example.administrator.expandablelistadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter()
        {
            int [] logos = new int[]
                    {
                            R.mipmap.ic_launcher,
                            R.mipmap.ic_launcher,
                            R.mipmap.ic_launcher
                    };
            private String[] armTypes = new String[]
                    {"aa","bb","cc"};
            private String[][] arms = new String[][]
                    {{"11","22","33"},
                            {"44","55","66"},
                            {"77","88","99"}};
            @Override
        public Object getChild(int groupPosition,int childPosition)
            {
                return arms[groupPosition][childPosition];
            }
            @Override
        public long getChildId(int groupPosition,int childPosition)
            {
                return childPosition;
            }
            @Override
        public int getChildrenCount(int groupPosition)
            {
                return arms[groupPosition].length;
            }
            private TextView getTextView()
            {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView textView =  new TextView(MainActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }
            @Override
        public View getChildView(int groupPosition,int childPosition,boolean isLastChild,
                                 View convertView,ViewGroup parent)
            {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }
            @Override
        public Object getGroup(int groupPosition)
            {
                return armTypes[groupPosition];
            }
            @Override
        public int getGroupCount()
            {
                return armTypes.length;
            }
            @Override
        public long getGroupId(int groupPosition)
            {
                return groupPosition;
            }
            @Override
        public View getGroupView(int groupPosition,boolean isExpanded,View convertView,ViewGroup parent)
            {
                LinearLayout ll = new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(MainActivity.this);
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }
            @Override
        public boolean isChildSelectable(int groupPosition,int childPosition)
            {
                return true;
            }
            @Override
        public boolean hasStableIds()
            {
                return true;
            }

        };
        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandablelistview);
        expandableListView.setAdapter(adapter);
    }
}
