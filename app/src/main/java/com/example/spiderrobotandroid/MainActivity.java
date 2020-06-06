package com.example.spiderrobotandroid;

import androidx.appcompat.app.AppCompatActivity;

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
    public String port;
    public String message;
    public Socket s;
    public PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText inputText = (EditText)findViewById(R.id.editTextMessage);
        final EditText inputTextIP = (EditText)findViewById(R.id.editTextIP);
        final EditText inputTextPort = (EditText)findViewById(R.id.editTextPort);
        final Button clickable = (Button)findViewById(R.id.button);
        final Button clickableConnection = (Button)findViewById(R.id.button2);
        clickable.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                send sendcode = new send();
                message = inputText.getText().toString();
                ip = inputTextIP.getText().toString();
                port = inputTextPort.getText().toString();
                sendcode.execute();
            }
        });
        clickableConnection.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openConnection conn = new openConnection();
                ip = inputTextIP.getText().toString();
                port = inputTextPort.getText().toString();
                conn.execute();
            }
        });
    }
    class send extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void...params) {

            pw.write(message);
            pw.flush();
            return null;
        }
    }

    class openConnection extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params) {
            try {
                s = new Socket(ip, Integer.parseInt(port));
                pw = new PrintWriter(s.getOutputStream());
            } catch (UnknownHostException e) {
                System.out.println("Fail");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Fail");
                e.printStackTrace();
            }
            return null;
        }
    }
}