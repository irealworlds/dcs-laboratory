package lab2.chatapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Chat
 */
public class ChatApp {
    private static final int PORT = 4445;
    private static final int MAX_PACKET_SIZE = 1024;

    private DatagramSocket socket;
    private InetAddress address;

    private final JTextArea messageArea;
    private final JTextField messageField;

    public ChatApp() {
        try {
            socket = new DatagramSocket(PORT);
            address = InetAddress.getByName("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Chat App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        messageField = new JTextField();
        messageField.addActionListener(e -> {
            sendMessage(messageField.getText());
            messageField.setText("");
        });

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            sendMessage(messageField.getText());
            messageField.setText("");
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        var receiveThread = new Thread(() -> {
            while (true) {
                byte[] buffer = new byte[MAX_PACKET_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(packet);
                    String message = new String(packet.getData(), 0, packet.getLength());
                    messageArea.append("Received: " + message + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveThread.start();
    }

    private void sendMessage(String message) {
        byte[] buffer = message.getBytes();
        var packet = new DatagramPacket(buffer, buffer.length, address, PORT);

        try {
            socket.send(packet);
            messageArea.append("Sent: " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatApp();
    }
}