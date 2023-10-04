package lab1.exercise2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ApplicationTest {
    static final int PORT = 3000;

    public static void main(String[] args) throws IOException {


        // Start the client
        var serverAddress = InetAddress.getByName("127.0.0.1");
        var socket = new Socket(serverAddress, PORT);

        // construieste fluxul de intrare prin care sunt receptionate datele de la server
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

        // construieste fluxul de iesire prin care datele sunt trimise catre server
        // Output is automatically flushed
        // by PrintWriter:
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream())),true);

        out.println(12);
        out.println(52);

        String str = in.readLine(); //trimite mesaj
        System.out.println("Raspuns primit:" + str); //asteapta raspuns
    }
}
