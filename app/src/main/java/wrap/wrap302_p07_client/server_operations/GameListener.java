package wrap.wrap302_p07_client.server_operations;

import wrap.wrap302_p07_client.Die;
import wrap.wrap302_p07_client.GameActivity;
import wrap.wrap302_p07_client.MainActivity;
import wrap.wrap302_p07_client.Player;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Antin on 10/11/2017.
 */
public class GameListener extends ServerRequest
{
    private GameActivity gameActivity;

    public GameListener(GameActivity gameActivity)
    {
        this.gameActivity = gameActivity;
    }

    @Override
    public void run()
    {
        super.run();

        try
        {
            //ARE YOU READY!? :D
            out.println(MainActivity.PLAYER_READY);

            //number of players in the game
            int numPlayers = Integer.parseInt(in.readLine());

            //which index is mine?
            int myPos = Integer.parseInt(in.readLine());

            //read-in the names
            final ArrayList<Player> players = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++)
                players.add(new Player(in.readLine()));

            players.get(myPos).title += " (Me)";

            gameActivity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameActivity.setPlayers(players);
                }
            });

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        boolean go = true;
        while (go)
        {
            try
            {
                final int curPlayerPos = Integer.parseInt(in.readLine());
                gameActivity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        gameActivity.selectPlayer(curPlayerPos);
                    }
                });

                String inLine = in.readLine();

                switch (inLine)
                {
                    case MainActivity.ROLL:
                    {
                        in.readLine(); //Read in the MainActivity.DICE_VALUES

                        final ArrayList<Die> dice = new ArrayList<>();

                        for (int i = 0; i < 3; i++)
                        {
                            int type = Integer.parseInt(in.readLine());
                            int value = Integer.parseInt(in.readLine());
                            dice.add(new Die(type, value));
                        }

                        gameActivity.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                gameActivity.setDice(dice);
                            }
                        });

                        String inLine2 = in.readLine();

                        switch (inLine2)
                        {
                            case MainActivity.END_TURN:
                            {
                                //do nothing i guess?
                                gameActivity.runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        gameActivity.resetExtraBrains(curPlayerPos);
                                    }
                                });
                                break;
                            }
                            case MainActivity.GAME_OVER:
                            {
                                final int winnerPos = Integer.parseInt(in.readLine());
                                gameActivity.runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        gameActivity.winner(winnerPos);
                                    }
                                });

                                //stop this listener
                                go = false;
                                break;
                            }
                            case MainActivity.SAME_TURN:
                            {
                                //do nothing i guess?
                                break;
                            }
                        }

                        break;
                    }
                    case MainActivity.END_TURN:
                    {
                        //do nothing i guess?
                        break;
                    }
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        handleResult();
    }

    @Override
    protected void handleResult()
    {
        //not much to do? maybe close the activity?
    }
}
