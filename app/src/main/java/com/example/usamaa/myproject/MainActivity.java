package com.example.usamaa.myproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.usamaa.myproject.Intent_Constants.INTENT_CHANGED_MESSAGE;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String messageText;
    int position;
    MyHandlerDB handlerdb = new MyHandlerDB(this, null, null, 209);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        arrayList = handlerdb.getTitles();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditItemActivity.class);
                intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
                String title = (String) parent.getItemAtPosition(position);
                intent.putExtra(Intent_Constants.INTENT_TITLE_DATA, title);
                startActivityForResult(intent,Intent_Constants.INTENT_REQUEST_CODE_TWO);
            }
        });
    }

    public void onClick(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AddItemActivity.class);
        startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==Intent_Constants.INTENT_REQUEST_CODE){
            //add item
            arrayList.clear();
            arrayList.addAll(handlerdb.getTitles());
            arrayAdapter.notifyDataSetChanged();
        }
        else if(resultCode==Intent_Constants.INTENT_REQUEST_CODE_TWO){
            //edit item
            messageText = data.getStringExtra(INTENT_CHANGED_MESSAGE);
            position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION,-1);
            arrayList.remove(position);
            arrayList.add(position,messageText);
            arrayAdapter.notifyDataSetChanged();
        }
        else if(resultCode==Intent_Constants.INTENT_REQUEST_CODE_THREE){
            //delete item
            position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION,-1);
            arrayList.remove(position);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}