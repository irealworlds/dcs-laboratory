package lab1.exercise2;

import java.io.*;

public class ServerApplication {
    static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        // Start and run the server
        var server = new Server(PORT);
        server.run();
    }
}
