package cm.mjd.examtwo.view;

import cm.mjd.examtwo.bean.Yean;
import cm.mjd.examtwo.bean.Zean;

public interface IFenView {
    void onZuo(Zean zean);//左侧请求成功
    void onYou(Yean yean);//右侧请求成功
}
