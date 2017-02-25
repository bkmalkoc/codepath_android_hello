package codepath.demos.helloworlddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by burak.malkoc on 12/20/2016.
 */

public class CustomAdapter extends ArrayAdapter<List> {

    public CustomAdapter(Context context, ArrayList<List> list) {
        super(context, 0, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        List list = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent);
        }
        TextView txtView = (TextView) convertView.findViewById(R.id.listTxtView);
        txtView.setText(list.name);

        return convertView;
    }
}
