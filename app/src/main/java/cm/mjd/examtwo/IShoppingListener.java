package cm.mjd.examtwo;

/**
 * Created by 1 on 2018/7/30.
 */

public interface IShoppingListener {
    //成功
    void onSuccess(String json);

    //失败
     void onError(String error);
}
