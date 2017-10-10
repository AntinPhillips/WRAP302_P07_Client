package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.MainActivity;

import java.io.IOException;

/**
 * Created by Antin on 10/9/2017.
 */
public class JoinGame extends ServerRequest
{
    private MainActivity mainActivity;
    private String roomCode;

    public JoinGame(MainActivity mainActivity, String roomCode)
    {
        this.mainActivity = mainActivity;
        this.roomCode = roomCode;
    }

    @Override
    public void run()
    {
        super.run();

        try
        {
            out.println(MainActivity.JOIN_GAME);
            out.println(roomCode);

            String inLine = in.readLine();

            switch (inLine)
            {
                case MainActivity.JOIN_SUCCESSFUL:
                {
                    break;
                }
                case MainActivity.ROOM_FULL:
                case MainActivity.INVALID_ROOM_CODE:
                default:
                {
                    handleResult();
                    return;
                }
            }

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
        mainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mainActivity.joinGame(result);
            }
        });
    }
}
