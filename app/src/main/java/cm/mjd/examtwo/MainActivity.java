package cm.mjd.examtwo;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import cm.mjd.examtwo.adapter.ErAdapter;
import cm.mjd.examtwo.adapter.ZuoAdapter;
import cm.mjd.examtwo.bean.Yean;
import cm.mjd.examtwo.bean.Zean;
import cm.mjd.examtwo.presenter.FenPresenter;
import cm.mjd.examtwo.view.IFenView;

public class MainActivity extends AppCompatActivity implements IFenView {
    public ExpandableListView yev;
    public RecyclerView zrv;
    public FenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);
        //拿到P层
        presenter = new FenPresenter(this);
        presenter.ShowPer();
        //默认展示第一条
        presenter.FlShowYou(1);
        //找控件
        zrv = findViewById(R.id.zrv);
        yev = findViewById(R.id.yev);
    }

    @Override
    public void onZuo(final Zean zean) {
        zrv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //拿到适配器
        ZuoAdapter adapter= new ZuoAdapter(MainActivity.this,zean);
        zrv.setAdapter(adapter);
        //调用点击事件
        adapter.setOnItemClickListener(new ZuoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = zean.getData().get(position).getCid();
                presenter.FlShowYou(id);
            }
        });
    }

    @Override
    public void onYou(Yean yean) {
        //拿到适配器
        ErAdapter adapter = new ErAdapter(MainActivity.this,yean);
        yev.setAdapter(adapter);
        //父级列表默认全部展开
        int groupCount = yev.getCount();
        for (int i=0; i<groupCount; i++)
        {
            yev.expandGroup(i);
        }

    }
}
