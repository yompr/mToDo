package com.michaelpreilly.apps.mtodo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dad on 12/3/16.
 */

public class ProjectListArrayAdapter extends ArrayAdapter<Project> {

    private final List<Project> list;
    private final Activity context;

    static class ViewHolder {
        protected TextView name;


    }

    public ProjectListArrayAdapter(Activity context, List<Project> list) {
        super(context, R.layout.activity_choose_project, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_choose_project, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);

            view.setTag(viewHolder);

        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getName());

        return view;


    }
}
