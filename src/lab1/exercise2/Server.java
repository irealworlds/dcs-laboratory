package lab1.exercise2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server implements Runnable {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            var serverSocket = new ServerSocket(this.port);
            System.out.println("Serverul asteapta conexiuni...");

            var clientSocket = serverSocket.accept();

            //creaza fluxurile de intrare iesire
            var in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            var out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(
                            clientSocket.getOutputStream())),true);

            //extrage adresa de ip si portul de pe care clientul s-a conectat
            InetSocketAddress remoteadr = (InetSocketAddress)clientSocket.getRemoteSocketAddress();
            String remotehost = remoteadr.getHostName();
            int remoteport = remoteadr.getPort();

            System.out.println("Client nou conectat: "+remotehost+":"+remoteport);

            String line;
            float x, y;
            line = in.readLine(); //citeste datele de la client
            x = Float.parseFloat(line);
            System.out.println("Server a receptionat: "+x);

            line = in.readLine(); //citeste datele de la client
            y = Float.parseFloat(line);
            System.out.println("Server a receptionat: "+y);

            out.println("ECHO " + y / x / 100); //trimite date la client
            out.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
