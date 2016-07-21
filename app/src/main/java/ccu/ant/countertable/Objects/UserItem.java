package ccu.ant.countertable.Objects;

/**櫃台系統可能不需要用到
 * Created by Egoist on 2016/7/18.
 */
public class UserItem {

    //TODO 以下為註冊時的需求 (User Register)
    public String uName;    //使用者名稱
    public String uEmail;   //使用者Email (Only)
    public String uPassword;//使用者密碼
    public String uPhone;   //使用者手機
    //TODO 以下為進系統後須補齊的需求 (User Data)
    public String uBirthday;  //使用者生日
    public String uImgurl;    //使用者 頭貼 Url
    public CouponItem[] uCoupon;//使用者

    //===============
    public String uJson_News; //新消息

    /*TODO 以下討論後為用伺服器做查詢就好*/
    //public String uMonthCup;//使用者 本月消費量(杯)
    //public String uTotalCup;//使用者 總消費量 (杯)
    //public String uJson_LikeProduct; //個人收藏的產品(JSON String)


    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(String uBirthday) {
        this.uBirthday = uBirthday;
    }

    public String getuImgurl() {
        return uImgurl;
    }

    public void setuImgurl(String uImgurl) {
        this.uImgurl = uImgurl;
    }

    public CouponItem[] getuCoupon() {
        return uCoupon;
    }

    public void setuCoupon(CouponItem[] uCoupon) {
        this.uCoupon = uCoupon;
    }

    public String getuJson_News() {
        return uJson_News;
    }

    public void setuJson_News(String uJson_News) {
        this.uJson_News = uJson_News;
    }
}
