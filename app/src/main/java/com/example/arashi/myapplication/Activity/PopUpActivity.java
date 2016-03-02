package com.example.arashi.myapplication.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arashi.myapplication.Store.ClassLocalStore;
import com.example.arashi.myapplication.Object.Roster;
import com.example.arashi.myapplication.UserLocalStore;
import com.example.arashi.myapplication.Object.Class;

/**
 * Created by Arashi on 2/11/2016.
 */
public class PopUpActivity extends Activity {

    EditText etClassName, etClassCode;
    Button createButton,cancelButton;
    UserLocalStore userLocalStore;
    ClassLocalStore classLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        etClassName =  (EditText) findViewById(R.id.etClassName);
        etClassCode = (EditText) findViewById(R.id.etClassCode);
        userLocalStore = new UserLocalStore(this);
        classLocalStore = new ClassLocalStore(this);

//        if(userLocalStore.getLoggedInUser().is_teacher == 1) {
//            createButton.setText("CREATE");
//        }
//        else {
//            createButton.setText("JOIN");
//        }


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        //create
        createButton = (Button) findViewById(R.id.CreateClassButton);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String className = etClassName.getText().toString();
                String classCode = etClassCode.getText().toString();
                int user_id = userLocalStore.getLoggedInUser().user_id;

                    if(className.isEmpty() || classCode.isEmpty() ){
                        Toast.makeText(PopUpActivity.this,"Please fill all completely", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(userLocalStore.getLoggedInUser().is_teacher == 1){
                        //   Toast.makeText(PopUpActivity.this,Integer.toString(), Toast.LENGTH_SHORT).show();
                            Class classroom = new Class(className, classCode,user_id);
                            createClass(classroom);
                        }
                        else {

                                //Roster roster = new Roster(user_id,);
                                Class classroom = new Class(className, classCode);
                                fetchClass(classroom);
//                            Toast.makeText(PopUpActivity.this,"Student", Toast.LENGTH_SHORT).show();

                        }
                    }
            }
        });


        //cancel
        cancelButton = (Button) findViewById(R.id.CancelClassButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void createClass(Class classroom){

        SeverRequests serverRequests = new SeverRequests(this);
        serverRequests.storeClassDataInBackground(classroom, new GetClassCallback(){

            public void done(Class returnedClass){
                Toast.makeText(PopUpActivity.this, "Class is created", Toast.LENGTH_SHORT).show();

                finish();

            }
        });

        //For teacher show class
        //SeverRequests serverRequests = new SeverRequests(this);
        serverRequests.fetchClassDataInBackground(classroom, new GetClassCallback(){
            public void done(Class returnedClass){
                if(returnedClass != null){
                    classLocalStore.storeClassData(returnedClass);
                    classLocalStore.setClassJoinedIn(true);
                    Toast.makeText(PopUpActivity.this, "Show Class", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PopUpActivity.this, ClassActivity.class));
                    finish();

                }
                else{
                    Toast.makeText(PopUpActivity.this, "Cannot Show Class", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchClass(Class classroom){
        //For student show class
        SeverRequests serverRequests = new SeverRequests(this);
        serverRequests.fetchClassDataInBackground(classroom, new GetClassCallback(){
            public void done(Class returnedClass){
                if(returnedClass != null){
                    classLocalStore.storeClassData(returnedClass);
                    classLocalStore.setClassJoinedIn(true);
                    Toast.makeText(PopUpActivity.this, "Show Class", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PopUpActivity.this, ClassActivity.class));
                    finish();

                }
                else{
                    Toast.makeText(PopUpActivity.this, "No Class", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}
