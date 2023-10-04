package lab1.exercise1.http;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class RequestProcessor extends Thread {
    private PrintWriter outStr;
    private BufferedReader inStr;
    private Socket s;
    private DataOutputStream dout;
    private String iniContext;

    public RequestProcessor(Socket s, String iContext){
        try{
            outStr = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
            inStr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            dout = new DataOutputStream(s.getOutputStream());
            iniContext = iContext;
            this.s = s;
            start();
        } catch(IOException e) {
            System.err.println("EROARE CONECTARE: "+e.getMessage());
        }
    }

    public void run(){
        try{
            String fileName=null;
            String request = inStr.readLine();
            System.out.print(request);
            if(request.lastIndexOf("GET")==0) fileName = interpretGET(request);
            else throw new Exception("BAU");
            byte[] data = readFile(fileName);
            dout.write(data);
            dout.flush();

        }
        catch(IOException e){outStr.println("<HTML><BODY><P>403 Forbidden<P></BODY></HTML>");}
        catch(Exception e2){outStr.println("<HTML><BODY><P>"+e2.getMessage()+"<P></BODY></HTML>");}
        finally{
            try{s.close();}catch(Exception e){}
        }
    }

    private String interpretGET(String rqst) throws Exception{
        StringTokenizer strT = new StringTokenizer(rqst);
        String tmp="";
        String fileN=iniContext;
        tmp=strT.nextToken();
        if(!tmp.equals("GET")) throw new Exception("Comanda GET invalida .");

        tmp=strT.nextToken();
        if((tmp.equals("/")) || (tmp.endsWith("/"))) {
            fileN = fileN+tmp+"index.htm";
            System.err.println("CERERE:"+fileN);
            return fileN;
        }

        fileN = fileN+ tmp;
        System.err.println("CERERE:"+fileN);
        return fileN;
    }

    private byte[] readFile(String fileN) throws Exception{
        fileN.replace('/','\\');
        File f = new File(fileN);
        if(!f.canRead()) throw new Exception("Fisierul "+fileN+" nu poate fi citit");
        FileInputStream fstr = new FileInputStream(f);
        byte[] data = new byte[fstr.available()];
        fstr.read(data);
        return data;
    }
}
