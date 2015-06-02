package com.light.activitytransition;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by light on 15/6/2.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ColorBean> dataSet;

    private Context context;

    private LayoutInflater layoutInflater;

    public MyAdapter(Context context) {
        super();

        this.context = context;
       // this.dataSet = dataSet;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvColorName;

        private ImageView imgColor;

        public ViewHolder(View itemView) {
            super(itemView);

            tvColorName = (TextView)itemView.findViewById(R.id.item_tv);
            imgColor = (ImageView)itemView.findViewById(R.id.item_img);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(layoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvColorName.setText("RED");

    }

    @Override
    public int getItemCount() {
        return 5;
    }


}
