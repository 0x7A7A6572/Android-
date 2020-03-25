package com.learndemo.androidx;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.learndemo.androidx.FlexibleAdapterLearn.FlexibleAdapterMainActivity;

public class DemoAdapter  extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
    private List<Demo> mDemo;

    static class ViewHolder extends RecyclerView.ViewHolder {
       
        TextView name;
        TextView date;

        public ViewHolder(View view) {
            super(view);
       
            name = view.findViewById(R.id.demo_item_content);
            date = view.findViewById(R.id.demo_item_time);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,  int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.demo_item, parent, false);
         ViewHolder holder = new ViewHolder(view);
        
        
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Demo demo = mDemo.get(position);
        holder.name.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    Demo demo = mDemo.get(position);
                    if(position == 0){
                        Intent intent = new Intent(MainActivity.THIS, FlexibleAdapterMainActivity.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        MainActivity.THIS.startActivity(intent);
                    }
                    Toast.makeText(view.getContext(), "你点击了图片"+ demo.getTitle() + " | " +position + "| 顺带测试Gson : " + MainActivity.Dml.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    
                }
            });
        holder.name.setText(demo.getTitle());
        holder.date.setText(demo.getId());
    }

    @Override
    public int getItemCount() {
        return mDemo.size();
    }

    public DemoAdapter(List<Demo> demoList) {
        mDemo = demoList;
    }

    
    
}
