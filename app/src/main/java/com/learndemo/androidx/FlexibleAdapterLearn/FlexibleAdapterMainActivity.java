package com.learndemo.androidx.FlexibleAdapterLearn;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
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
import eu.davidea.flexibleadapter.items.IFlexible;


public class FlexibleAdapterMainActivity extends AppCompatActivity implements
        ActionMode.Callback, FlexibleAdapter.OnItemLongClickListener {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.flexible_adapter_activity_main);
// Optional but strongly recommended: Compose the initial list
        List<Gun> myItems = getDatabaseList();

// Initialize the Adapter
        FlexibleAdapter<Gun> adapter = new FlexibleAdapter<>(myItems);


        adapter.addListener(onLongClickListenerAdapter1);
        adapter.addListener(clickListenerAdapter1);

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.mRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private FlexibleAdapter.OnItemClickListener clickListenerAdapter1 =
            new FlexibleAdapter.OnItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position) {
                    Toast.makeText(context, "点击事件", Toast.LENGTH_SHORT).show();
                    return false;
                }
            };

    private FlexibleAdapter.OnItemLongClickListener onLongClickListenerAdapter1 =
            new FlexibleAdapter.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(int position) {
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
        String[] introduction = AssetsReader.getText("introduction.txt",context).split("。");
        Log.v("split",introduction[0]);
        for (int i = 1; i <= 10; i++) {
            list.add(new Gun(names[i-1], introduction[i-1], AssetsReader.getImage(context, "33752726_" + i + ".jpg")));
        }
        return list;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public void onItemLongClick(int position) {

    }
}
