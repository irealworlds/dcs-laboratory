package lab1.exercise1.nonblocking;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NonblockingServer {

    public static void main(String[] args) throws Exception{

//		 Create the server socket channel
        ServerSocketChannel server = ServerSocketChannel.open();
//		 nonblocking I/O
        server.configureBlocking(false);
//		 host-port 8000
        server.socket().bind(new java.net.InetSocketAddress("localhost",8000));
        System.out.println("Server waiting on port 8000");
//		 Create the selector
        Selector selector = Selector.open();
//		 Recording server to selector (type OP_ACCEPT)
        server.register(selector, SelectionKey.OP_ACCEPT);

//		 Infinite server loop

        for(;;) {
            Thread.sleep(1000);
            // Waiting for events
            System.err.println("wait for event...");
            selector.select();

            // Get keys
            Set keys = selector.selectedKeys();
            Iterator i = keys.iterator();
            System.err.println("keys size="+keys.size());
            // For each keys...
            while(i.hasNext()) {

                // Obtain the interest of the key
                SelectionKey key = (SelectionKey) i.next();

                // Remove the current key
                i.remove();


                // if isAccetable = true
                // then a client required a connection
                if (key.isAcceptable()) {
                    System.err.println("Key is of type acceptable");
                    // get client socket channel
                    SocketChannel client = server.accept();
                    // Non Blocking I/O
                    client.configureBlocking(false);
                    // recording to the selector (reading)
                    client.register(selector, SelectionKey.OP_READ);
                    continue;
                }

                // if isReadable = true
                // then the server is ready to read
                if (key.isReadable()) {
                    System.err.println("Key is of type readable");
                    SocketChannel client = (SocketChannel) key.channel();

                    // Read byte coming from the client
                    int BUFFER_SIZE = 32;
                    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    try {
                        client.read(buffer);

                    }
                    catch (Exception e) {
                        // client is no longer active
                        client.close();

                        e.printStackTrace();
                        continue;
                    }

                    // Show bytes on the console
                    buffer.flip();
                    Charset charset=Charset.forName("ISO-8859-1");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer = decoder.decode(buffer);
                    System.out.println(charBuffer.toString());
                    continue;
                }


            }
            System.err.println("after while keys size="+keys.size());
        }
    }
}
