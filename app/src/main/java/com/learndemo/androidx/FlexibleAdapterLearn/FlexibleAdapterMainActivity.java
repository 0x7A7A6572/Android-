package com.learndemo.androidx.FlexibleAdapterLearn;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learndemo.androidx.R;
import com.learndemo.androidx.util.AssetsReader;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.SelectableAdapter;


public class FlexibleAdapterMainActivity extends AppCompatActivity {

    public static FlexibleAdapter<Gun> mAdapter;
    public static boolean isMultipleSelectionMode;
    //  private ActionMode mActionMode;
    Context THIS;
    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        THIS = this;
        setContentView(R.layout.flexible_adapter_activity_main);
// Optional but strongly recommended: Compose the initial list
        List<Gun> myItems = getDatabaseList();

// Initialize the Adapter
        mAdapter = new FlexibleAdapter<>(myItems);
        mAdapter.setMode(SelectableAdapter.Mode.SINGLE);//初始化单选模式
        mAdapter.addListener(onLongClickListenerAdapter1);
        mAdapter.addListener(clickListenerAdapter1);
        recyclerView = findViewById(R.id.mRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemViewCacheSize(0);//设置缓存
    }

    private void toggleSelection(int position) {
        // Mark the position selected
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            mAdapter.setMode(SelectableAdapter.Mode.IDLE);
        } else {
            setContextTitle(count);
            mAdapter.setMode(SelectableAdapter.Mode.MULTI);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void setContextTitle(int count) {
//        mActionMode.setTitle(String.valueOf(count) + " " + (count == 1 ?
//                getString(R.string.action_selected_one) :
//                getString(R.string.action_selected_many)));
    }


    private FlexibleAdapter.OnItemClickListener clickListenerAdapter1 =
            new FlexibleAdapter.OnItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position) {
                    if (mAdapter.getMode() == SelectableAdapter.Mode.MULTI && position != RecyclerView.NO_POSITION) {
                        // Mark the position selected
                        toggleSelection(position);

                        return true;
                    } else {
Toast.makeText(context,"单选模式点击",Toast.LENGTH_SHORT).show();
                        // Handle the item click listener
                        // We don't need to activate anything
                        return false;
                    }
                }
            };

    private FlexibleAdapter.OnItemLongClickListener onLongClickListenerAdapter1 =
            new FlexibleAdapter.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(int position) {
                    if (mAdapter.getMode() != SelectableAdapter.Mode.MULTI) {
                        //mActionMode = startActionMode(new SelectionCallBack(mAdapter,THIS));
                        toggleSelection(position);
                    } else {
                        mAdapter.clearSelection();
                        toggleSelection(-1);
                    }

                    Toast.makeText(context, "长按事件", Toast.LENGTH_SHORT).show();
                }
            };


    public List<Gun> getDatabaseList() {
        List<Gun> list = new ArrayList<>();
        String[] names = new String[]{
                "意大利伯莱塔92F型手枪",
                "奥地利格洛克17型手枪",
                "美国柯尔特M2000型手枪",
                "德国P229型手枪",
                "中国QSG92式手枪",
                "德国HKP7型手枪",
                "美国鲁格P85式手枪",
                "美国M1911A1式手枪",
                "捷克CZ83型手枪",
                "前苏联托卡列夫手枪"};
        String[] introduction = AssetsReader.getText("introduction.txt", context).split("。");
        Log.v("split", introduction[0]);
        for (int i = 1; i <= 10; i++) {
            list.add(new Gun(names[i - 1], introduction[i - 1], AssetsReader.getImage(context, "33752726_" + i + ".jpg")));
        }
        return list;
    }


}
