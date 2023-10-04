package lab1.exercise1.serialising;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialisingServer extends Thread {
    public void run(){

        try{
            ServerSocket ss = new ServerSocket(1977);
            Socket s = ss.accept();
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Serializable p = (Serializable) ois.readObject();
            System.out.println("Serverul a receptionat obiectul:"+p);
            s.close();
            ss.close();
        }catch(Exception e){e.printStackTrace();}

    }
}
