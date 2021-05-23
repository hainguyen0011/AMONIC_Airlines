package com.example.amonicairlines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Service> serviceList;

    public ServiceAdapter(Context context, int layout, List<Service> serviceList) {
        this.context = context;
        this.layout = layout;
        this.serviceList = serviceList;
    }

    @Override
    public int getCount() {
        return serviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textview_name);
        TextView textViewCost = (TextView) convertView.findViewById(R.id.textview_cost);

        Service service = serviceList.get(position);
        textViewName.setText(service.getName());
        textViewCost.setText(service.getCost());

        return convertView;
    }
}
