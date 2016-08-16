package ccu.ant.countertable.Objects;

import java.util.Date;

/**
 * Created by Egoist on 2016/7/18.
 */
public class CouponItem {

    public String     _id;    //優惠卷 ID
    public String  cOwner; //擁有者 (Email)
    public String  cType;  //優惠卷 Type
    public String  cTitle; //票面消息
    public String  cContent; //詳細資訊
    public double  cValue;   //優惠卷 價值 (折價=0~0.99 & 現金折抵 >1)
    public Date    cDeadline;//票卷Deadline

    public CouponItem( String cType, String cTitle, String cContent, double cValue, Date cDeadline) {
        this.cType = cType;
        this.cTitle = cTitle;
        this.cContent = cContent;
        this.cValue = cValue;
        this.cDeadline = cDeadline;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public double getcValue() {
        return cValue;
    }

    public void setcValue(double cValue) {
        this.cValue = cValue;
    }

    public Date getcDeadline() {
        return cDeadline;
    }

    public void setcDeadline(Date cDeadline) {
        this.cDeadline = cDeadline;
    }

    public String getcOwner() {
        return cOwner;
    }

    public void setcOwner(String cOwner) {
        this.cOwner = cOwner;
    }

    @Override
    public String toString() {
        return "CouponItem{" +
                "_id='" + _id + '\'' +
                ", cOwner='" + cOwner + '\'' +
                ", cType='" + cType + '\'' +
                ", cTitle='" + cTitle + '\'' +
                ", cContent='" + cContent + '\'' +
                ", cValue=" + cValue +
                ", cDeadline=" + cDeadline +
                '}';
    }
}
