package wrap.wrap302_p07_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import wrap.wrap302_p07_client.server_operations.AddPlayers;
import wrap.wrap302_p07_client.server_operations.LobbyListener;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity
{
    private LobbyListAdapter adapter;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        players = new ArrayList<>();
        adapter = new LobbyListAdapter(this, players);
        ((ListView) findViewById(R.id.lobby_player_listview)).setAdapter(adapter);

        Intent intent = getIntent();

        String roomCode = intent.getStringExtra("RoomCode");
        ((TextView) findViewById(R.id.lobby_room_code_txt)).setText(roomCode);

        String type = intent.getStringExtra("Type");
        switch (type)
        {
            case MainActivity.CREATE_GAME:
            {
                adapter.add(new Player("Me"));
                findViewById(R.id.lobby_start_game_btn).setVisibility(View.VISIBLE);
                findViewById(R.id.lobby_start_game_btn).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Toast.makeText(LobbyActivity.this, "Start game?", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case MainActivity.JOIN_GAME:
            {
                findViewById(R.id.lobby_start_game_btn).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.lobby_bottom_txt)).setText(R.string.waiting_host);
                new AddPlayers(this, MainActivity.socket).start();
                break;
            }

            default:
            {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }

        new LobbyListener(this, MainActivity.socket).start();
    }

    public void setPlayers(boolean result, List<Player> players)
    {
        if (!result)
        {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter.clear();
        adapter.addAll(players);
        adapter.add(new Player("Me"));
    }

    public void addPlayer(Player player)
    {
        adapter.add(player);
    }
}
