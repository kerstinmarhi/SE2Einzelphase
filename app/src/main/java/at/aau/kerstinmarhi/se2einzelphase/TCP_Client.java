package at.aau.kerstinmarhi.se2einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_Client implements Runnable{

    private static final String SERVER_HOST = "se2-isys.aau.at";
    private static final int SERVER_PORT = 53212;

    private Socket s;
    private DataOutputStream out;
    private BufferedReader in;
    private String message;

    private String data;

    public TCP_Client(String data){
        this.data=data;
    }

    @Override
    public void run() {
        try {

            s=new Socket(SERVER_HOST,SERVER_PORT);
            out=new DataOutputStream(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.writeBytes(data+'\n');
            message=in.readLine();

            s.close();

        } catch(UnknownHostException e) {
            System.out.println("Unknown host: se2-isys.aau.at");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage(){
        return message;
    }
}
