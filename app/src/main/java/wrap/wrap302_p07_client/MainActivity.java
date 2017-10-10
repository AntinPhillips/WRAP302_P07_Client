package wrap.wrap302_p07_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import wrap.wrap302_p07_client.server_operations.CreateGame;
import wrap.wrap302_p07_client.server_operations.JoinGame;

import java.net.Socket;

public class MainActivity extends AppCompatActivity
{
    //define some connection constants
    public static final String JOIN_GAME = "1";
    public static final String CREATE_GAME = "2";
    public static final String INVALID_ROOM_CODE = "3";
    public static final String ROOM_FULL = "4";
    public static final String JOIN_SUCCESSFUL = "5";
    public static final String START_GAME = "6";
    public static final String NEW_PLAYER = "7";
    public static final String GAME_STARTING = "8";
    public static final String PLAYER_READY = "9";

    public static Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_create_game_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int numPlayers = Integer.parseInt(((EditText) findViewById(R.id.main_num_players_edtTxt)).getText().toString());
                if (numPlayers < 2 || numPlayers > 6)
                {
                    Toast.makeText(MainActivity.this, "2-6 Players please.", Toast.LENGTH_SHORT).show();
                    return;
                }

                new CreateGame(MainActivity.this, numPlayers).start();
            }
        });

        findViewById(R.id.main_join_game_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String roomCode = ((EditText) findViewById(R.id.main_room_code_edtTxt)).getText().toString();
                new JoinGame(MainActivity.this, roomCode).start();
            }
        });
    }

    public void createGame(boolean result, String roomCode)
    {
        if (!result)
            Toast.makeText(this, "Creation of game failed.", Toast.LENGTH_SHORT).show();

        if (result)
        {
            Intent intent = new Intent(this, LobbyActivity.class);
            intent.putExtra("Type", CREATE_GAME);
            intent.putExtra("RoomCode", roomCode);
            startActivity(intent);
        }
    }

    public void joinGame(boolean result)
    {
        if (!result)
            Toast.makeText(this, "Failed to join game.", Toast.LENGTH_SHORT).show();

        if (result)
        {
            Intent intent = new Intent(this, LobbyActivity.class);
            intent.putExtra("Type", JOIN_GAME);
            intent.putExtra("RoomCode", ((EditText) findViewById(R.id.main_room_code_edtTxt)).getText().toString());
            startActivity(intent);
        }
    }
}
