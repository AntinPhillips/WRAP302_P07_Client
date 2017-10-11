package wrap.wrap302_p07_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import wrap.wrap302_p07_client.server_operations.GameListener;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity
{
    private LobbyListAdapter adapter;
    private ArrayList<Player> players;
    private Player curPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        adapter = new LobbyListAdapter(this, new ArrayList<Player>(), false);
        ((ListView) findViewById(R.id.game_player_listview)).setAdapter(adapter);

        new GameListener(this).start();
    }

    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
        adapter.addAll(players);
    }

    public void setDice(ArrayList<Die> dice)
    {
        //change pictures
        ((ImageView) findViewById(R.id.game_die_1)).setImageDrawable(dice.get(0).getImage(this));
        ((ImageView) findViewById(R.id.game_die_2)).setImageDrawable(dice.get(1).getImage(this));
        ((ImageView) findViewById(R.id.game_die_3)).setImageDrawable(dice.get(2).getImage(this));

        //update values
        for (Die die : dice)
        {
            if (die.value == 0)
                curPlayer.extraBrains++;
            else if (die.value == 2)
                curPlayer.shotguns++;
        }

        adapter.notifyDataSetChanged();
    }

    public void selectPlayer(int curPlayerPos)
    {
        //did the player change? if so, we must set the shotguns to 0
        if (curPlayer == players.get(curPlayerPos))
            return;

        curPlayer.shotguns = 0;
        curPlayer = players.get(curPlayerPos);

        adapter.notifyDataSetChanged();
    }

    public void resetExtraBrains(int curPlayerPos)
    {
        players.get(curPlayerPos).extraBrains = 0;
        adapter.notifyDataSetChanged();
    }

    public void winner(int winnerPos)
    {
        Toast.makeText(this, players.get(winnerPos).title + " has won the game! :D", Toast.LENGTH_SHORT).show();
        finish();
    }
}
