package cm.mjd.examtwo.model;

import java.util.List;
import java.util.Map;

import cm.mjd.examtwo.IShoppingListener;

/**
 * Created by 1 on 2018/7/30.
 */

public interface IModel {
    void shop(String url, Map<String, String> map, IShoppingListener iShoppingListener);
}
