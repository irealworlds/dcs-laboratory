package lab1.exercise1.socket;

import java.io.*;
import java.net.Socket;

public class MultithreadedServerWorker extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Initializeaza firul de executie
     * @param socket
     * @throws IOException
     */
    public MultithreadedServerWorker(Socket socket) throws IOException
    {
        this.socket = socket;

        //init flux de intrare prin care se vor citi datele de la client
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //inti flux de iesire prin care datele vor fi trimise catre client
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter( socket.getOutputStream())));

        //sunt folosite doua fluxuri orientate pa caracter

    }

    public void run()
    {
        try {

            while (true)
            {
                //asteapta receptionarea unui mesaj de la client
                String str = in.readLine();

                //daca mesajul este egal cu "END" opreste firul ce trateaza clientul
                if (str.equals("END")) break;
                //afiseaza mesajul pe ecran
                System.out.println("Echoing: " + str);

                //trimte catre client mesajul receptionat, adaugand in fata sirul "echo"
                out.println("echo "+str);
                out.flush();

            }//.while
            System.out.println("closing...");

        }
        catch(IOException e) {System.err.println("IO Exception");}
        finally {
            try {
                socket.close();
            }catch(IOException e) {System.err.println("Socket not closed");}
        }
    }//.run
}
