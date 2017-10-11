package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.MainActivity;

import java.io.*;
import java.net.Socket;

/**
 * Created by Antin on 10/10/2017.
 */
public abstract class ServerRequest extends Thread
{
    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected boolean result = false;

    @Override
    public void run()
    {
        try
        {
            socket = MainActivity.socket;

            if (MainActivity.socket == null) //a quick fix of NOTE! xD
            {
                System.out.println("");
                System.out.println("");
                System.out.println("Creating new socket... This is PROBABLY bad news if you're seeing this...");
                System.out.println("");
                System.out.println("");

                socket = new Socket("165.255.244.177", 123);
                MainActivity.socket = socket;
            }

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected abstract void handleResult();
}
