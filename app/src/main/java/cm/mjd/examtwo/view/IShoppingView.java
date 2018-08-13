package cm.mjd.examtwo.view;


import java.util.List;

import cm.mjd.examtwo.bean.ShoppingBean;

/**
 * Created by 1 on 2018/7/30.
 */

public interface IShoppingView {
    void ShowShoppingToViews(List<ShoppingBean.DataBean> data);

    String getName();

    int getpage();

    String getsort();
}
