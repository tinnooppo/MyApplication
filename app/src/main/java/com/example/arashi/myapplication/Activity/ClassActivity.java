package com.example.arashi.myapplication.Activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arashi.myapplication.Store.ClassLocalStore;


public class ClassActivity extends Activity {
  //  TextView txt_class_id;
    ListView listView;
    Button addClass;
    ClassLocalStore classLocalStore;
   // ClassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        classLocalStore = new ClassLocalStore(this);
 //       txt_class_id = (TextView) findViewById(R.id.txt_class_id);
//        int class_id = classLocalStore.getJoinedInClass().class_id;
       // txt_class_id.setText(Integer.toString(class_id));
       // textv1=(TextView)findViewById(R.id.textView1);
        //classLocalStore.getJoinedInClass().classname;

        String[] list = { classLocalStore.getJoinedInClass().classname, "Digital", "English"
                , "DataStructure", "Interface", "Web Technology", "Psychology"
                };

        ClassAdapter adapter = new ClassAdapter(getApplicationContext(), list);

        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(ClassActivity.this,tabMainActivity.class));
            }
        });

        //Add Class Button Click
        addClass = (Button) findViewById(R.id.btn_add_class);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int class_id = classLocalStore.getJoinedInClass().class_id;
               // Toast.makeText(ClassActivity.this,Integer.toString(class_id), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ClassActivity.this,PopUpActivity.class));


            }
        });

    }

}
