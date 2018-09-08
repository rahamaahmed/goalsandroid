package com.example.usamaa.myproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class EditFieldClass extends AppCompatActivity {

    Button save;
    EditText name, description;
    MyHandlerDB handlerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);

        name = (EditText) findViewById(R.id.name);
        save = (Button) findViewById(R.id.save);
        description = (EditText) findViewById(R.id.description);
        handlerdb = new MyHandlerDB(this, null, null, 203);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Name -  " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                }

                String messageText = ((EditText)findViewById(R.id.name)).getText().toString();
                if(messageText.equals("")){

                }
                else{
                    Intent intent_const = new Intent();
                    intent_const.putExtra(Intent_Constants.INTENT_MESSAGE_FIELD, messageText);
                    setResult(Intent_Constants.INTENT_RESULT_CODE,intent_const);
                    finish();
                }

                handlerdb.addGoal(name.getText().toString(), description.getText().toString());


            }
        });
    }
}
