package com.example.spiderrobotandroid;

import android.app.Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Description of what is going on here:
 * https://www.intertech.com/Blog/androids-application-class/
 */

public class TcpSocketHandler extends Application {

     private Socket s;
     private PrintWriter pw;

     public void openSocket(String ip, int port)
     {
         try {
             s = new Socket(ip, port);
             pw = new PrintWriter(s.getOutputStream());
         } catch (UnknownHostException e) {
             System.out.println("Fail");
             e.printStackTrace();
         } catch (IOException e) {
             System.out.println("Fail");
             e.printStackTrace();
         }
     }

     public void sendMessage(String message){
         pw.write(message);
         pw.flush();
     }


}
