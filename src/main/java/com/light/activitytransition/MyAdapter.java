package com.light.activitytransition;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

    private Handler handler;

    public MyAdapter(Context context,List<ColorBean> dataSet,Handler handler) {
        super();

        this.context = context;
        this.dataSet = dataSet;
        this.handler = handler;

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

        final int index = position;

        holder.tvColorName.setText(dataSet.get(position).getColorName());
        holder.imgColor.setBackgroundColor(dataSet.get(position).getColorRes());

        holder.tvColorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message msg = new Message();
                msg.arg1 = dataSet.get(index).getColorRes();
                msg.what = DetialActivity.CHANGE_COLOR;
                handler.sendMessage(msg);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
