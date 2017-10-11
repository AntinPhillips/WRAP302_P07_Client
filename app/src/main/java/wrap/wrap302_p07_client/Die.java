package wrap.wrap302_p07_client;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


/**
 * Created by Antin on 10/11/2017.
 */
public class Die
{
    public int type; //green, yellow or red : 0,1,2
    public int value; //brains, runner, shotgun: 0,1,2

    public Die(int type, int value)
    {
        this.type = type;
        this.value = value;
    }

    public Drawable getImage(Context context)
    {
        TypedArray imgIDs = context.getResources().obtainTypedArray(R.array.die_icons);
        Drawable drawable = imgIDs.getDrawable(type*3 + value);
        imgIDs.recycle();

        return drawable;
    }
}
