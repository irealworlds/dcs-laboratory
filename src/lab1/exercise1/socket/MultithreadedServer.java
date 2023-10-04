package lab1.exercise1.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {
    public static final int PORT = 1900;

    void startServer()
    {
        ServerSocket ss = null;
        try
        {

            ss = new ServerSocket(PORT);
            while (true)
            {
                //astept conexiuni
                Socket socket = ss.accept();

                //un client s-a conectat...starteaza firul de tratare a clientului
                new MultithreadedServerWorker(socket);
            }

        } catch(IOException ex) {
            System.err.println("Eroare :"+ex.getMessage());
        }
        finally
        {
            try{ ss.close(); }catch(IOException ex2){}
        }
    }

    public static void main(String args[])
    {
        MultithreadedServer smf = new MultithreadedServer();
        smf.startServer();
    }
}
