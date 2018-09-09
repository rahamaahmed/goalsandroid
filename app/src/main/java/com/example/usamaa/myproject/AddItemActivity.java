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
import android.util.Log;
import android.text.TextUtils;
import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {

    Button save;
    EditText titleField, descriptionField;
    MyHandlerDB handlerdb = new MyHandlerDB(this, null, null, 209);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);

        titleField = (EditText) findViewById(R.id.name);
        descriptionField = (EditText) findViewById(R.id.description);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = titleField.getText().toString();
                String description = descriptionField.getText().toString();

                if (TextUtils.isEmpty(title) && TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Please enter the info", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Name -  " + title, Toast.LENGTH_SHORT).show();
                    Intent intent_const = new Intent();
                    setResult(Intent_Constants.INTENT_RESULT_CODE,intent_const);
                    finish();
                    handlerdb.addGoal(title, description);
                }
            }
        });
    }
}
