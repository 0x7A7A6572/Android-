package com.learndemo.androidx.other;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.learndemo.androidx.FlexibleAdapterLearn.Gun;
import com.learndemo.androidx.R;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.SelectableAdapter;

public class SelectionCallBack  implements ActionMode.Callback {
    private FlexibleAdapter<Gun> mAdapter;
    private ActionMode mActionMode;
    Context thisActivity;
    int menu_context;
     SelectionCallBack(FlexibleAdapter adapter,Context context){
        this.mAdapter = adapter;
        this.thisActivity = context;
        this.menu_context = R.menu.menu_context;
    }

    SelectionCallBack(FlexibleAdapter adapter,Context context,int resid){
        this.mAdapter = adapter;
        this.thisActivity = context;
        this.menu_context = resid;
    }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(menu_context, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_select_all:
                    mAdapter.selectAll();
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(thisActivity,"warning!",Toast.LENGTH_SHORT).show();

                    break;
                case R.id.action_delete:
                    mAdapter.removeAllSelectedItems();
                    Toast.makeText(thisActivity,"favourite!",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.setMode(SelectableAdapter.Mode.IDLE);
            mActionMode = null;
        }


}
