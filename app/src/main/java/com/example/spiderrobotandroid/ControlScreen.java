package com.example.spiderrobotandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ControlScreen extends AppCompatActivity {

    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control_screen);
        final EditText inputText = (EditText)findViewById(R.id.editTextMessage);
        final Button clickableSend = (Button)findViewById(R.id.button);
        clickableSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ControlScreen.send sendcode = new ControlScreen.send();
                message = inputText.getText().toString();
                //ip = inputTextIP.getText().toString();
                //controlPort = inputTextPortControl.getText().toString();
                //cameraPort = inputTextPortCamera.getText().toString();
                sendcode.execute();
            }
        });

    }

    class send extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void...params) {
            TcpSocketHandler s = ( (TcpSocketHandler) getApplicationContext());
            s.sendMessage(message);
            return null;
        }
    }
}