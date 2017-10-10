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
    public LobbyListAdapter(Context context, List<Player> playerList)
    {
        super(context, R.layout.player_listview_item, playerList);
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

        return view;
    }
}
