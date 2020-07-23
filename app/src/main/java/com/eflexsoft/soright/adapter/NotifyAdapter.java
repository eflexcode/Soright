package com.eflexsoft.soright.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eflexsoft.soright.R;
import com.eflexsoft.soright.model.Notify;

import java.util.ArrayList;
import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

    List<Notify> notifies = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.notify_layout,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Notify notify = notifies.get(position);
        holder.text.setText(notify.getText());

    }

    @Override
    public int getItemCount() {
        return notifies.size();
    }

    public void setNotifies(List<Notify> notifies) {
        this.notifies = notifies;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.order);
        }
    }
}
