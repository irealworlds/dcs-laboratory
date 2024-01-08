package lab2.urlhttp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ContentDownloader {

    public static void main (String[] args) {
        String url = "http://utcluj.ro/index.html";
//        Open the URL for reading
        try {
            URL u = new URL(args[0]);
            try {
                Object o = u.getContent( );
                System.out.println("I got a " + o.getClass().getName( ));
            } // end try
            catch (IOException e) {
                System.err.println(e);
            }
        } // end try

        catch (MalformedURLException e) {
            System.err.println(args[0] + " is not a parseable URL");
        }


    } // end main
}