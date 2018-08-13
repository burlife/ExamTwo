package cm.mjd.examtwo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import cm.mjd.examtwo.R;
import cm.mjd.examtwo.adapter.ShoppingRecycleAdapter;
import cm.mjd.examtwo.bean.ShoppingBean;
import cm.mjd.examtwo.model.ModelImpel;
import cm.mjd.examtwo.presenter.PresenterImpel;
import cm.mjd.examtwo.view.IShoppingView;



public class ShoppingActivity extends AppCompatActivity implements IShoppingView,View.OnClickListener{
    private static final String TAG = "ShoppingActivity===";
    private EditText etName;
    private ImageView qiehuan;
    private String tvName;
    private ImageView s_sousou;
    private ImageView s_fan;
    private String name;
    int page=1;
    private boolean b=false;
    private String sort="0";
    private XRecyclerView recyclerView;
    private ShoppingRecycleAdapter shoppingRecycleAdapter;
    private PresenterImpel presenterImpel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        initView();
        Intent intent=getIntent();
        //判断
        if (intent!=null){
            tvName=intent.getStringExtra("name");
        }
        //String name=intent.getStringExtra("name");
        presenterImpel=new PresenterImpel();
        etName.setText(tvName);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page++;
                presenterImpel.getShop(new ModelImpel(),ShoppingActivity.this);
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenterImpel.getShop(new ModelImpel(),ShoppingActivity.this);
                recyclerView.loadMoreComplete();
            }
        });
    }

    private void initView() {
        etName=findViewById(R.id.s_name);
        s_sousou=findViewById(R.id.s_sou);
        s_fan=findViewById(R.id.s_fan);
        recyclerView=findViewById(R.id.recy_view);
     qiehuan=findViewById(R.id.qiehuan);
        s_fan.setOnClickListener(this);
        s_sousou.setOnClickListener(this);
        qiehuan.setOnClickListener(this);

    }

    @Override
    public void ShowShoppingToViews(List<ShoppingBean.DataBean> data) {
        Log.d(TAG, "数据信息---"+data);
        shoppingRecycleAdapter=new ShoppingRecycleAdapter(this,data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shoppingRecycleAdapter);

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getpage() {
        return page;
    }

    @Override
    public String getsort() {
        return sort;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.s_fan:
                  finish();
                break;
            case  R.id.s_sou:
                name=etName.getText().toString();
                presenterImpel.getShop (new ModelImpel(),ShoppingActivity.this);
                break;
            case R.id.qiehuan:
                if (b==false){
                    recyclerView.setLayoutManager(new GridLayoutManager(ShoppingActivity.this,2));
                    b=true;
                }else if (b==true){
                    recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingActivity.this));
                    b=false;
                }
                break;

        }
    }
}
