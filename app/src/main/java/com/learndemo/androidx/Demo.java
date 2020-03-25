package com.learndemo.androidx;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class Demo implements IFlexible {
    
        private String title;
        private String id;
        private String CodeUrl;

        public Demo(String name, String date) {
            this.title = name;
            this.id = date;
        }

        public String getTitle() {
            return title;
        }

        public String getId() {
            return id;
        }


    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void setHidden(boolean hidden) {

    }

    @Override
    public int getSpanSize(int spanCount, int position) {
        return 0;
    }

    @Override
    public boolean shouldNotifyChange(IFlexible newItem) {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void setSelectable(boolean selectable) {

    }

    @Override
    public String getBubbleText(int position) {
        return null;
    }

    @Override
    public boolean isDraggable() {
        return false;
    }

    @Override
    public void setDraggable(boolean draggable) {

    }

    @Override
    public boolean isSwipeable() {
        return false;
    }

    @Override
    public void setSwipeable(boolean swipeable) {

    }

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public void onViewDetached(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onViewAttached(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void unbindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {

    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return null;
    }
}
