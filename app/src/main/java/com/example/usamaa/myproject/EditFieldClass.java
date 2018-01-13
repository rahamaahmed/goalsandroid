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
    Spinner frequency;
    MyHandlerDB handlerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);

        name = (EditText) findViewById(R.id.name);
        save = (Button) findViewById(R.id.save);
        frequency = (Spinner) findViewById(R.id.frequency);
        description = (EditText) findViewById(R.id.description);
        handlerdb = new MyHandlerDB(this, null, null, 202);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Name -  " + name.getText().toString() + " \n" + "Frequency -  " + frequency.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,00);
                calendar.set(Calendar.MINUTE,00);
                calendar.set(Calendar.SECOND,00);
                Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                if (frequency.getSelectedItem().toString()=="Daily") {
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                }
                else if (frequency.getSelectedItem().toString()=="Weekly") {
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7,pendingIntent);
                }

                String messageText = ((EditText)findViewById(R.id.name)).getText().toString();
                if(messageText.equals("")){

                }
                else{
                    Intent intent_const = new Intent();
                   //Toast.makeText(getApplicationContext(), "msg -  " + messageText + " \n" + "intent const -  " +  intent_const.getStringExtra(Intent_Constants.INTENT_MESSAGE_FIELD), Toast.LENGTH_SHORT).show();
                    intent_const.putExtra(Intent_Constants.INTENT_MESSAGE_FIELD, messageText);
                    setResult(Intent_Constants.INTENT_RESULT_CODE,intent_const);
                    finish();
                }

                handlerdb.addGoal(name.getText().toString(), frequency.getSelectedItem().toString(), description.getText().toString());


            }
        });
    }
}
