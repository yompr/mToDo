package com.michaelpreilly.apps.mtodo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dad on 12/3/16.
 */

public class MTaskListArrayAdapter extends ArrayAdapter<MTask> {

    private final List<MTask> list;
    private final Activity context;

    static class ViewHolder {
        protected TextView name;
        protected TextView compDate;
        protected TextView createDate;
        protected TextView projName;
        protected TextView assignee;
    }

    public MTaskListArrayAdapter(Activity context, List<MTask> list) {
        super(context, R.layout.fragment_main, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.mtask_list_item_view, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.mTaskNameView);
            viewHolder.projName = (TextView) view.findViewById(R.id.mTaskProjView);
            viewHolder.compDate = (TextView) view.findViewById(R.id.mTaskCompDateView);
            viewHolder.createDate = (TextView) view.findViewById(R.id.mTaskCreateDateView);
            viewHolder.assignee = (TextView) view.findViewById(R.id.mTaskAssigneeView);


            view.setTag(viewHolder);

        } else {
            view = convertView;
        }
        //Log.d("MPR-ADAPTER-position",Integer.toString(position));
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");


        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getTaskName());
        holder.projName.setText(list.get(position).getProject());
        holder.assignee.setText(list.get(position).getAssignee());

        if (list.get(position).getCompletionDate() != null ) {
            String aDate = df.format(list.get(position).getCompletionDate());
            holder.compDate.setText(aDate);
        }
        if (list.get(position).getCreationDate() != null ) {
            String aDate = df.format(list.get(position).getCreationDate());
            holder.createDate.setText(aDate);
        }
        //Log.d("MPR-ADAPTER-getView",list.get(position).getTaskName());
        return view;


    }
}
