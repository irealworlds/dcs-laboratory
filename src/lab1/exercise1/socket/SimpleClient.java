package lab1.exercise1.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SimpleClient {

    public static void main(String[] args)throws Exception{
        Socket socket = null;
        try {
            // creare obiect address care identifica adresa serverului
            InetAddress server_address =InetAddress.getByName("10.132.70.14");

            socket = new Socket(server_address,1900);

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


            for(int i = 0; i < 10; i ++) {
                out.println("mesaj " + i);
                out.flush();

                String str = in.readLine(); //trimite mesaj
                System.out.println(str); //asteapta raspuns
            }
            out.println("END"); //trimite mesaj care determina serverul sa inchida conexiunea

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            assert socket != null;
            socket.close();
        }
    }
}
