package com.example.bankappzup.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bankappzup.Model.ListItens;
import com.example.bankappzup.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListItens> listItens;
    private Context context;

    public ListAdapter(List<ListItens> listItens, Context context) {
        this.listItens = listItens;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View v = LayoutInflater.from(viewGroup.getContext())
              .inflate(R.layout.list_item,viewGroup,false);
      return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    ListItens listItem = listItens.get(i);

    viewHolder.title.setText(listItem.getTitle());
        viewHolder.date.setText(listItem.getDate());
        viewHolder.desc.setText(listItem.getDesc());
        viewHolder.value.setText(listItem.getValue());

    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,date,desc,value;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            desc = (TextView) itemView.findViewById(R.id.desc);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }
}
