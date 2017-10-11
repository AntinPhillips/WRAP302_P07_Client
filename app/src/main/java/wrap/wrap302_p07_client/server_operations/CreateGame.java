package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.MainActivity;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Antin on 10/9/2017.
 */
public class CreateGame extends ServerRequest
{
    private MainActivity mainActivity;
    private int numPlayers;
    private String roomCode;
    private String name;

    public CreateGame(MainActivity mainActivity, int numPlayers, String name)
    {
        this.mainActivity = mainActivity;
        this.numPlayers = numPlayers;
        this.name = name;
    }

    @Override
    public void run()
    {
        super.run();

        try
        {
            out.println(MainActivity.CREATE_GAME);
            out.println(numPlayers);
            out.println(name);
            String inLine = in.readLine();

            if (inLine.equals(MainActivity.JOIN_SUCCESSFUL))
                result = true;

            //read room code
            roomCode = in.readLine();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        handleResult();
    }

    @Override
    protected void handleResult()
    {
        mainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mainActivity.createGame(result, roomCode);
            }
        });
    }
}
