package cm.mjd.examtwo.model;

import com.google.gson.Gson;
import java.util.Map;

import cm.mjd.examtwo.IShoppingListener;
import cm.mjd.examtwo.bean.ShoppingBean;
import cm.mjd.examtwo.utils.HttpUtils;

public class ModelImpel implements IModel {

    //搜索列表
    @Override
    public void shop(String url, Map<String, String> map, final IShoppingListener iShoppingListener) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils ();

        httpUtils.okPost (url,map);

        httpUtils.setOkLoadListener (new HttpUtils.OkLoadListener () {
            @Override
            public void okLoadSuccess(String json) {
                Gson gson = new Gson ();

                ShoppingBean shoppingBean = gson.fromJson (json, ShoppingBean.class);

                if(shoppingBean.getCode ().equals ("0")){
                       iShoppingListener.onSuccess (json);
                }else{
                    iShoppingListener.onError (json);
                }
            }

            @Override
            public void okLoadError(String error) {

                iShoppingListener.onError (error);
            }
        });
    }
}
