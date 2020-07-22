package com.eflexsoft.soright.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eflexsoft.soright.R;
import com.eflexsoft.soright.model.CategoryName;
import com.eflexsoft.soright.model.Post;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryName> categoryAdapters;

    Context context;

    public CategoryAdapter(List<CategoryName> categoryAdapters, Context context) {
        this.categoryAdapters = categoryAdapters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_catargory, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CategoryName categoryName = categoryAdapters.get(position);
        holder.categoryName.setText(categoryName.getName());

        createRecycleView(holder.recyclerView, categoryName.getPosts());

    }

    private void createRecycleView(RecyclerView recyclerView, List<Post> posts) {

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new PostAdapter(posts,context));

    }

    @Override
    public int getItemCount() {
        return categoryAdapters.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.catergory_name);
            recyclerView = itemView.findViewById(R.id.catargory_recycler);
        }
    }

}
