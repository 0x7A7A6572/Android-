package com.learndemo.androidx;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Build;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.learndemo.androidx.FlexibleAdapterLearn.FlexibleAdapterMainActivity;

public class DemoAdapter  extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
    private List<Demo> mDemo;

    public static HashMap<Integer, Boolean> isSelected;//存放选择的状态

    public static List<Demo> selectDates = new ArrayList<>();
    /*
     * 单机和长按接口
     *
     */
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        CheckBox checkbox;
        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.demo_item_content);
            date = view.findViewById(R.id.demo_item_time);
            checkbox = view.findViewById(R.id.demo_item_checkbox);
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
                    //Toast.makeText(view.getContext(), "你点击了图片"+ demo.getTitle() + " | " +position + "| 顺带测试Gson : " + MainActivity.Dml.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    
                }
            });
        holder.name.setText(demo.getTitle());
        holder.date.setText(demo.getId());

        holder.checkbox.setChecked(isSelected.get(position));
        holder.itemView.setSelected(isSelected.get(position));

            if (mOnItemClickListener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mOnItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                    }
                });
            }



    }

    @Override
    public int getItemCount() {
        return mDemo.size();
    }

    public DemoAdapter(List<Demo> demoList) {
        mDemo = demoList;
        init();
    }



    // 初始化 设置所有item都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < mDemo.size(); i++) {
            isSelected.put(i, false);
        }
    }

    /*多选方案*/
    public void all(View view){
        selectDates.clear();

        for (int i = 0; i < mDemo.size(); i++) {
            isSelected.put(i, true);
            selectDates.add(mDemo.get(i));
        }
        notifyDataSetChanged();
        // mTvCount.setText("已选中"+selectDatas.size()+"项");
    }
    /**
     * 反选
     * @param view
     */
    public void inverse(View view){
        for (int i=0; i<mDemo.size(); i++) {
            if(isSelected.get(i)){
                isSelected.put(i,false);
                selectDates.remove(mDemo.get(i));
            } else {
                isSelected.put(i,true);
                selectDates.add(mDemo.get(i));
            }
        }
       notifyDataSetChanged();
        //mTvCount.setText("已选中"+selectDatas.size()+"项");

    }
    /**
     * 取消已选
     * @param view
     */
    public void cancel(View view){
        for (int i=0; i<mDemo.size(); i++) {
            if(isSelected.get(i)){
                isSelected.put(i,false);
                selectDates.remove(mDemo.get(i));
            }
        }
        notifyDataSetChanged();
        // mTvCount.setText("已选中"+selectDatas.size()+"项");
    }


}
