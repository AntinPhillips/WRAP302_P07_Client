package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.MainActivity;

import java.io.*;
import java.net.Socket;

/**
 * Created by Antin on 10/10/2017.
 */
public abstract class ServerRequest extends Thread implements Serializable
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
            if (socket == null) //a quick fix of NOTE! xD But you probably won't understand what I'm fixing with this, or why it's bad, so it's fine ;)
            {
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
