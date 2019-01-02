package kgc.pojo;

/**
 * @ClassName com.vv.pojo
 * @Author xiaohongwei
 * @Date 2019/1/1 21:00
 * @Version 1.0
 **/

public class EXGoods extends Goods {
    private Smallclass smallclass;
    private Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Smallclass getSmallclass() {
        return smallclass;
    }
    public void setSmallclass(Smallclass smallclass) {
        this.smallclass = smallclass;
    }
}
