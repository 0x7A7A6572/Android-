package com.learndemo.androidx.FlexibleAdapterLearn;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.learndemo.androidx.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class Gun extends AbstractFlexibleItem<com.learndemo.androidx.FlexibleAdapterLearn.Gun.MyViewHolder>{

        private String name;
        private String introduction;
        private Bitmap img;

        public Gun(String name, String introduction, Bitmap img) {
            this.name = name;
            this.introduction = introduction;
            this.img = img;
        }

        @Override
        public boolean equals(Object inObject) {
            if (inObject instanceof com.learndemo.androidx.FlexibleAdapterLearn.Gun) {
                com.learndemo.androidx.FlexibleAdapterLearn.Gun inItem = (com.learndemo.androidx.FlexibleAdapterLearn.Gun) inObject;
                return this.name.equals(inItem.name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }


        @Override
        public int getLayoutRes() {
            return R.layout.flexible_adapter_item_flexible;
        }


        @Override
        public com.learndemo.androidx.FlexibleAdapterLearn.Gun.MyViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
           for(int i = 0;i < adapter.getItemCount(); i++){
               Log.v("adapter",adapter.getItemViewType(i) + " >>> " + i);
           }

            return new com.learndemo.androidx.FlexibleAdapterLearn.Gun.MyViewHolder(view, adapter);
        }

        @Override
        public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, com.learndemo.androidx.FlexibleAdapterLearn.Gun.MyViewHolder holder,
                                   int position,
                                   List<Object> payloads) {
            holder.gun_name.setText(name);
            // Title appears disabled if item is disabled
            holder.gun_name.setEnabled(isEnabled());
            holder.gun_img.setImageBitmap(img);
            holder.gun_introduction.setText(introduction);

            boolean md = adapter.isSelected(position);
            if(md){
                holder.gun_name.setBackgroundColor(0xFF2B2B2B);
            }else{
                holder.gun_name.setBackgroundColor(0xFFFFFFFF);
            }
        }

        public class MyViewHolder extends FlexibleViewHolder {

            public TextView gun_name;
            public TextView gun_introduction;
            public ImageView gun_img;

            public MyViewHolder(View view, FlexibleAdapter adapter) {
                super(view, adapter);
                gun_name = (TextView) view.findViewById(R.id.gun_name);
                gun_introduction = (TextView) view.findViewById(R.id.gun_introduction);
                gun_img = (ImageView) view.findViewById(R.id.gun_img);
            }
        }

}
