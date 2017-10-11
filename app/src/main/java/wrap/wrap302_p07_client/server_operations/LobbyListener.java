package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.LobbyActivity;
import wrap.wrap302_p07_client.MainActivity;
import wrap.wrap302_p07_client.Player;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Antin on 10/10/2017.
 */
public class LobbyListener extends ServerRequest
{
    private LobbyActivity lobbyActivity;

    public LobbyListener(LobbyActivity lobbyActivity, Socket socket)
    {
        this.socket = socket;
        this.lobbyActivity = lobbyActivity;
    }

    @Override
    public void run()
    {
        super.run();

        while (true)
        {
            try
            {
                String inLine = in.readLine();
                switch (inLine)
                {
                    case MainActivity.NEW_PLAYER:
                    {
                        final Player player = new Player(in.readLine());
                        lobbyActivity.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                lobbyActivity.addPlayer(player);
                            }
                        });

                        break;
                    }
                    case MainActivity.GAME_STARTING:
                    {
                        lobbyActivity.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                lobbyActivity.startGame();
                            }
                        });

                        System.out.println("LOBBY LISTENER WANTS TO START GAME");

                        //and stop this listener :)
                        return;
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //handleResult();
    }

    @Override
    protected void handleResult()
    {
        //probably not much to do? maybe start the game thread?
    }
}
