package wrap.wrap302_p07_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
                Intent intent = new Intent(MainActivity.this, LobbyActivity.class);
                startActivity(intent);
            }
        });
    }
}
