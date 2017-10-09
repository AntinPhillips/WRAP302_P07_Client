package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.MainActivity;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Antin on 10/9/2017.
 */
public class CreateGame extends Thread
{
    private MainActivity mainActivity;

    public CreateGame(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run()
    {
        try
        {
            Socket socket = new Socket("165.255.244.177", 123);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
