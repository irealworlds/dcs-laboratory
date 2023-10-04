package lab1.exercise1.http;

import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer extends Thread {
    //portul standard
    private final static int PORT = 3000;

    private final String iniContext="~/";
    private boolean alive;

    private ServerSocket ss;

    //constructor
    HttpServer()throws Exception{
        System.out.println("Start server http.");
        ss = new ServerSocket(PORT);
        alive=true;
        start();
    }

    public void run(){
        while(alive){

            //asteapta conexiuni
            try{
                System.out.println("Server asteapta...");
                new RequestProcessor(ss.accept(),iniContext);

            }catch(IOException e){System.err.println("EROARE CONECTARE:"+e.getMessage());}
            //..reia bucla de asteptare dupa ce am creat un fir pentru client
        }
        System.out.println("STOP SERVER");
    }
    public static void main(String[] args)throws Exception
    {
        try{
            new HttpServer();
        }catch(Exception e){e.printStackTrace();}
    }
}
