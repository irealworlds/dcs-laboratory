package lab2.datagram;

import java.io.*;
import java.net.*;


public class Client {
    public static void main(String[] args) throws IOException {
//        if (args.length != 1) {
//             System.out.println("Usage: java QuoteClient <hostname>");
//             return;
//        }

        // get a datagram socket
        var socket = new DatagramSocket();

        // send request
        var buf = new byte[256];
        var address = InetAddress.getByName("localhost");
        var packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);

        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // display response
        String received = new String(packet.getData());
        System.out.println("Quote of the Moment: " + received);

        socket.close();
    }
}