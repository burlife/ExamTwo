package cm.mjd.examtwo.presenter;

import android.util.Log;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cm.mjd.examtwo.IShoppingListener;
import cm.mjd.examtwo.api.HttpConfig;
import cm.mjd.examtwo.bean.ShoppingBean;
import cm.mjd.examtwo.model.IModel;
import cm.mjd.examtwo.presenter.IPresenter;
import cm.mjd.examtwo.view.IShoppingView;


public class PresenterImpel implements IPresenter {
    private static final String TAG = "PresenterImpel----";
private static  String url01="http://www.zhaoapi.cn/product/searchProducts";
    @Override
    public void getShop(IModel iModel, final IShoppingView iShoppingView) {
        Map<String,String> map = new HashMap<String,String> ();

        map.put ("keywords",iShoppingView.getName ());

        map.put ("page",iShoppingView.getpage ()+"");

        map.put ("sort",iShoppingView.getsort ());

        iModel.shop (HttpConfig.url, map, new IShoppingListener() {
            @Override
            public void onSuccess(String json) {

                Gson gson = new Gson ();

                ShoppingBean shoppingBean = gson.fromJson (json, ShoppingBean.class);

                List<ShoppingBean.DataBean> list = shoppingBean.getData ();

                iShoppingView.ShowShoppingToViews (list);
            }

            @Override
            public void onError(String error) {
                Log.d (TAG, "onError: 失败-------");
            }
        });
    }
}
