package com.learndemo.androidx;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
import java.util.ArrayList;
import android.icu.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
private List<Demo> fruitList = new ArrayList<>();
static List<Demo> Dml;
    //功能变量
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
    private SimpleDateFormat note_format = new SimpleDateFormat("yy年MM月dd日");
    private SimpleDateFormat simple_format = new SimpleDateFormat("MM/dd");
    private String data = " [{\"id\":\"20年03月24日\",\"title\":\"FlexibleAdapter--一个强大的RecyclerView开源库\",\"gg\":\"hhhh\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"},{\"date\":\"20年03月24日\",\"name\":\"Apple\"},{\"date\":\"20年03月24日\",\"name\":\"banana\"}]";
  public static Context THIS;
    DemoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		THIS = this;
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
        this.setTitle("AndroidX之旅 - zzer");
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
         adapter = new DemoAdapter(Dml);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DemoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(!adapter.isSelected.get(position)){
                    adapter.isSelected.put(position, true); // 修改map的值保存状态
                    adapter.notifyItemChanged(position);
                    adapter.selectDates.add(Dml.get(position));

                }else {
                    adapter.isSelected.put(position, false); // 修改map的值保存状态
                    adapter.notifyItemChanged(position);
                    adapter.selectDates.remove(Dml.get(position));
                }
                Toast.makeText(THIS, "已选中"+adapter.selectDates.size()+"项", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        
    }



    private void initFruits() {
        for(int i = 0; i < 10; i++) {
            Demo apple = new Demo("Apple",simple_format.format(new Date()));
            fruitList.add(apple);
            Demo banana = new Demo("banana", "");//simple_format.format(new Date()));
            fruitList.add(banana);
        }
        
        Gson gson = new Gson();
        String jsonBDID = gson.toJson(fruitList);
        Dml = (List<Demo>) gson.fromJson(data, new TypeToken<ArrayList<Demo>>() {}.getType());
        Log.v("Gson",jsonBDID);
        Log.v("Gson",Dml.toString());
    }
    

}
