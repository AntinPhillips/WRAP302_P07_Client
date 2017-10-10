package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.LobbyActivity;

/**
 * Created by Antin on 10/10/2017.
 */
public class StartGame extends ServerRequest
{
    private LobbyActivity lobbyActivity;

    public StartGame(LobbyActivity lobbyActivity)
    {
        this.lobbyActivity = lobbyActivity;
    }

    @Override
    protected void handleResult()
    {

    }
}
