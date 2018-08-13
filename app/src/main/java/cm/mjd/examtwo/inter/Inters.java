package cm.mjd.examtwo.inter;

import cm.mjd.examtwo.bean.Yean;
import cm.mjd.examtwo.bean.Zean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Inters {
    /**
     * 左面接口
     */
    @GET("product/getCatagory")
    Observable<Zean> Zuo();
    /**
     * 右面接口
     */
    @GET("product/getProductCatagory")
    Observable<Yean> You(@Query("cid") int cid);
}
