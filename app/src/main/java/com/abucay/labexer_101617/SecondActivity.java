package com.abucay.labexer_101617;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    Button btnLoadShared;
    Button btnLoadInternal;
    Button btnClear;
    Button btnBack;
    TextView tvInfo;
    SharedPreferences preferences;
    FileOutputStream fos;
    FileInputStream fis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnLoadShared = (Button) findViewById(R.id.btn_loadshared);
        btnLoadInternal = (Button) findViewById(R.id.btn_loadinternal);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);

        tvInfo = (TextView) findViewById(R.id.tv_info);



    }



    public void loadShared(View view){
        SharedPreferences preferences = this.getSharedPreferences("Preferences", MODE_PRIVATE);
        String user = preferences.getString("username","");
        String pwd = preferences. getString("password", "");
        tvInfo.setText("Username is " + user + " and password is " + pwd);
    }


    public void loadInternal(View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        tvInfo.setText(buffer.toString());
    }

    public void clearInfo(View view){

        tvInfo.setText("");
    }


    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);;
        startActivity(intent);
    }
}
