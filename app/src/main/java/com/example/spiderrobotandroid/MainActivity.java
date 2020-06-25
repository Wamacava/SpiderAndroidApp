package com.example.spiderrobotandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    public String ip;
    public String controlPort;
    public String cameraPort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText inputTextIP = (EditText)findViewById(R.id.editTextIP);
        final EditText inputTextPortControl = (EditText)findViewById(R.id.editTextPort);
        final EditText inputTextPortCamera = (EditText)findViewById(R.id.editTextPortCamera);
        final Button clickableConnection = (Button)findViewById(R.id.button2);

        clickableConnection.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openConnection conn = new openConnection();
                ip = inputTextIP.getText().toString();
                controlPort = inputTextPortControl.getText().toString();
                conn.execute();
                openControlScreen();
            }
        });
    }

    public void openControlScreen(){
        Intent intent = new Intent(this, ControlScreen.class);
        startActivity(intent);
    }


    class openConnection extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params) {
            TcpSocketHandler s = ( (TcpSocketHandler) getApplicationContext());
            s.openSocket(ip, Integer.parseInt(controlPort));
            return null;
        }
    }
}