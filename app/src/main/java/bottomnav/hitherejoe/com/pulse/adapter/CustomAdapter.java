package bottomnav.hitherejoe.com.pulse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bottomnav.hitherejoe.com.pulse.R;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    List<String> strName;

    public CustomAdapter(Context context, List<String> strName) {
        this.mContext= context;
        this.strName = strName;
    }

    public int getCount() {
        return strName.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.txt_title);
        textView.setText(strName.get(position));

        return view;
    }
}
