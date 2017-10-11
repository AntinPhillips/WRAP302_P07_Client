package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.MainActivity;

/**
 * Created by Antin on 10/11/2017.
 */
public class StartGame extends ServerRequest
{
    @Override
    public void run()
    {
        super.run();
        out.println(MainActivity.START_GAME);
        out.flush();
        System.out.println("Sent START GAME PACKET");
    }

    @Override
    protected void handleResult()
    {

    }
}
