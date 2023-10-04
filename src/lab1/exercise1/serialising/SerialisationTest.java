package lab1.exercise1.serialising;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SerialisationTest {
    public static void main(String[] args) throws Exception{
        //starteaza serverul
        (new SerialisingServer()).start();

        //conectare la server
        Socket s = new Socket(InetAddress.getByName("127.0.0.1"),1977);

        //construieste fluxul de iesire
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

        Person p = new Person("Alin", 14);
        //trimite un obiect prin fluxul de iesire
        System.out.println("Clientul trimite obiectul: "+p);
        oos.writeObject(p);
        //inchide conexiunea/
        s.close();

    }
}
