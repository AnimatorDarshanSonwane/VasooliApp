package com.example.vasool_drive.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.vasool_drive.R;
import com.example.vasool_drive.classes.DataModel;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        public TextView txtNumber;
        TextView txtName;
        CheckBox checkBox;
    }

    public CustomAdapter(ArrayList data, Context context) {
        super(context, R.layout.contacts_list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }


    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.ContactsName);
            viewHolder.txtNumber = (TextView) convertView.findViewById(R.id.ContactsNumber);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        DataModel item = (DataModel) getItem(position);
        viewHolder.txtName.setText(item.name);
        viewHolder.txtNumber.setText(item.number);
        viewHolder.checkBox.setChecked(item.checked);


        return result;
    }
}
