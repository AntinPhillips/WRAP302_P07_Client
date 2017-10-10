package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.LobbyActivity;
import wrap.wrap302_p07_client.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antin on 10/10/2017.
 */
public class AddPlayers extends ServerRequest
{
    private LobbyActivity lobbyActivity;
    private List<Player> players;

    public AddPlayers(LobbyActivity lobbyActivity, Socket socket)
    {
        this.socket = socket;
        this.lobbyActivity = lobbyActivity;
    }

    @Override
    public void run()
    {
        super.run();

        System.out.println("Attempting to add players...");

        try
        {
            players = new ArrayList<>();

            Integer numPlayers = Integer.parseInt(in.readLine());
            System.out.println(numPlayers);

            for (int i = 0; i < numPlayers; i++)
                players.add(new Player(in.readLine()));

            result = true;
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        handleResult();
    }

    @Override
    protected void handleResult()
    {
        System.out.println("Handling add player result");
        lobbyActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                lobbyActivity.setPlayers(result, players);
            }
        });
    }
}
