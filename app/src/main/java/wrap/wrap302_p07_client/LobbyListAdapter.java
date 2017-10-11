package wrap.wrap302_p07_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Antin on 10/10/2017.
 */
public class LobbyListAdapter extends ArrayAdapter<Player>
{
    private boolean lobby; //is the adapter being used for the lobby or the game scene?

    public LobbyListAdapter(Context context, List<Player> playerList, boolean lobby)
    {
        super(context, R.layout.player_listview_item, playerList);
        this.lobby = lobby;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater inflater = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.player_listview_item, parent, false);
        }

        ((TextView) view.findViewById(R.id.player_listview_item_txtPlayerName)).setText(getItem(position).title);

        if (lobby)
        {
            view.findViewById(R.id.player_listview_item_brain_icon).setVisibility(View.GONE);
            view.findViewById(R.id.player_listview_item_brain_txt).setVisibility(View.GONE);
            view.findViewById(R.id.player_listview_item_shotgun_icon).setVisibility(View.GONE);
            view.findViewById(R.id.player_listview_item_shotgun_text).setVisibility(View.GONE);
        }
        else
        {
            view.findViewById(R.id.player_listview_item_brain_icon).setVisibility(View.VISIBLE);
            view.findViewById(R.id.player_listview_item_brain_txt).setVisibility(View.VISIBLE);
            view.findViewById(R.id.player_listview_item_shotgun_icon).setVisibility(View.VISIBLE);
            view.findViewById(R.id.player_listview_item_shotgun_text).setVisibility(View.VISIBLE);

            ((TextView) view.findViewById(R.id.player_listview_item_brain_txt)).setText(getItem(position).brains + " (+" + getItem(position).extraBrains + ")");
            ((TextView) view.findViewById(R.id.player_listview_item_shotgun_text)).setText("" + getItem(position).shotguns);
        }

        return view;
    }
}
