package javaWangLuo.BianCheng1.socket.Duoxiancheng;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Send extends Thread{
    private Socket s;
    public Send(Socket s){
        this.s=s;
    }
    public void run(){
        try{
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            while(true){

                Scanner sc = new Scanner(System.in);
                String str = sc.next();
                dos.writeUTF(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
