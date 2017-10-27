package com.abucay.labexer_101617;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    Button btnShared, btnInternal, btnNext;
    EditText etUser, etPassword;
    SharedPreferences preferences;
    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShared = (Button) findViewById(R.id.btn_shared);
        btnInternal = (Button) findViewById(R.id.btn_internal);
        btnNext = (Button) findViewById(R.id.btn_next);

        etUser = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

    }


    public void sharedPref(View view){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", etUser.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

    }

    public void internalStorage(View view){

        String message = etUser.getText().toString();
        String message2 = etPassword.getText().toString();

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
            fos.write(message2.getBytes());
            fos.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally{
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        Toast.makeText(this, "Message Saved!", Toast.LENGTH_SHORT).show();
    }




    public void nextWindow(View view){
        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);
    }
}
