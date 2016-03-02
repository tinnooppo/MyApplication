package com.example.arashi.myapplication.Activity;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arashi.myapplication.Activity.ClassActivity;
import com.example.arashi.myapplication.Activity.EditProfile;
import com.example.arashi.myapplication.Activity.MainActivity;
import com.example.arashi.myapplication.UserLocalStore;


public class home extends Activity implements View.OnClickListener  {
    TextView vName, vUsername, vEmail, vStatus;
    Button btnEdit, btnClass, btnSign_out;

//    SeverRequests serverRequest;
//    User user;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportActionBar().hide();
        vName = (TextView) findViewById(R.id.fullName_text);
        vUsername = (TextView) findViewById(R.id.username_text);
        vEmail = (TextView) findViewById(R.id.email_text);
        vStatus = (TextView) findViewById(R.id.status_text);

        btnEdit = (Button) findViewById(R.id.button_edit);
        btnClass = (Button) findViewById(R.id.button_class);
        btnSign_out= (Button) findViewById(R.id.button_sign_out);

        btnEdit.setOnClickListener(this);
        btnClass.setOnClickListener(this);
        btnSign_out.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

        onBegin();

    }

    protected void onBegin(){
        vUsername.setText(userLocalStore.getLoggedInUser().username);
        vName.setText(userLocalStore.getLoggedInUser().name);
        vEmail.setText(userLocalStore.getLoggedInUser().email);
        if(userLocalStore.getLoggedInUser().is_teacher == 1){
            vStatus.setText("Teacher");
        }
        else {
            vStatus.setText("Student");
        }

    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_edit:
                startActivity(new Intent(this, EditProfile.class));
                break;
            case R.id.button_class:
               // Toast.makeText(this,Integer.toString(userLocalStore.getLoggedInUser().user_id), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ClassActivity.class));
                break;
            case R.id.button_sign_out:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
