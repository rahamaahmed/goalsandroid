package com.example.usamaa.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class EditMessageClass extends AppCompatActivity {

    String messageText;
    int position;
    MyHandlerDB handlerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);

        handlerdb = new MyHandlerDB(this, null, null, 202);
        Intent intent = getIntent();
        messageText = intent.getStringExtra(Intent_Constants.INTENT_MESSAGE_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText messageData = (EditText) findViewById(R.id.name);
        messageData.setText(messageText);
    }

    public void saveButtonClicked(View v){
        String changedMessageText = ((EditText)findViewById(R.id.name)).getText().toString();
        String changedMessageFrequency = ((Spinner)findViewById(R.id.frequency)).getSelectedItem().toString();
        String changedMessageDescription = ((EditText)findViewById(R.id.description)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.INTENT_CHANGED_MESSAGE, changedMessageText);
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_RESULT_CODE_TWO, intent);
        handlerdb.updateGoal(messageText, changedMessageText, changedMessageFrequency, changedMessageDescription);
        finish();
    }

    public void deleteButtonClicked(View v){
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_RESULT_CODE_THREE, intent);
        handlerdb.deleteGoal(messageText);
        finish();
    }
}
