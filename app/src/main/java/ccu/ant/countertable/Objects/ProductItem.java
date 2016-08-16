package ccu.ant.countertable.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Egoist on 2016/7/8.
 * TODO 產品object
 */
public class ProductItem {

    //櫃台APP
    public boolean isFinish = false;
    //

    public static final String[] SpeciesTW = {
            "小杯","中杯","大杯","特大杯" //0,1,2,3
            ,"濃度普通","濃度加厚"        //4,5
            ,"正常冰","多冰","去冰"       //6,7,8
            ,"正常糖","多糖","去糖"       //9,10,11
            ,"正常奶","多奶","去奶"};     //12,13,14
    public static final String[] SpeciesEN = {
            "S","M","L","XL"                //0,1,2,3
            ,"1*Espresso","2*Espresso"      //4,5
            ,"1*Ice","2*Ice","no*Ice"       //6,7,8
            ,"1*Sugar","2*Sugar","no*Sugar" //9,10,11
            ,"1*Milk","2*Milk","no*Milk"};  //12,13,14

    public static final String[] SupplyTimeTW = {
            "全時段 供應","早餐(06:00~10:00)","午餐(10:00~13:00)","下午茶(13:00~17:00)","晚餐(17:00~20:00)"
    };

    public static final String[] SupplyTimeEN = {
            "Full-time supply","Breakfast(06:00~10:00)","Lunch(10:00~13:00)","Afternoon(13:00~17:00)","Dinner(17:00~20:00)"
    };

    //規格ID to 規格字串
    public static String Species_ID2Name(int sID,boolean isTW){
        if(sID>=0 && sID<(isTW?SpeciesTW.length:SpeciesEN.length)){
            return  isTW? SpeciesTW[sID].toString():SpeciesEN[sID].toString();
        }
        return "沒有規格";
    }

    //規格字串 to 規格ID
    public static int Species_Name2ID(String sName,boolean isTW){
        for(int i =0;i< (isTW?SpeciesTW.length:SpeciesEN.length);i++){
            if(isTW?SpeciesTW[i].equals(sName):SpeciesEN[i].equals(sName)) return i;
        }
        return -1;
    }

    //規格編號(int[]) to 規格字串(String[])
    public static String[] Species_IDlist2Namelist(int sID[],boolean isTW){
        List<String> sn = new ArrayList<String>();
        for(int i=0;i<sID.length;i++){sn.add(Species_ID2Name(sID[i],isTW));}
        String[] sarray = new String[sn.size()];
        sarray = sn.toArray(sarray);
        return sarray;
    }

    //供餐時段 ID轉字串
    public static String SupplyTime_ID2Name(int sID,boolean isTW){
        if(sID>=0 && sID<(isTW?SupplyTimeTW.length:SupplyTimeEN.length)){
            return  isTW? SupplyTimeTW[sID].toString():SupplyTimeEN[sID].toString();
        }
        return "沒有供餐時段";
    }


    public String pType;         //產品屬性("coffee,drink,pastry,meal")
    public String pName;    //產品名稱
    public String pEngName; //產品英文名稱
    public String pDescirption;//產品描述
    public int pPrice;         //產品價格
    public int pCafeine_Heat;  //產品咖啡因 or 熱量
    public String pNews;       //產品優惠 or 新聞
    public boolean pIsNew;     //是否為新產品
    public String pImageUrl;   //產品圖片
    public int pSupplyTime;    //供餐時段 (0全時段 1早餐 2午餐 3下午餐 4晚餐)
    public boolean hasEspresso;//是否可調整濃度
    public boolean hasIce;     //是否有含冰
    public boolean hasSugar;   //是否可調糖份
    public boolean hasMilk;    //是否有含奶
    public boolean hasSize;    //是否有尺寸(大中小)
    public boolean hasXLSize;  //是否有特大尺寸
    //shopping car 會用到的部分
    public int sPCS = 1;    //單品購買數量
    public String sTag = ""; //單品備註 (濃度 冰塊 糖分 奶量) [大杯;三倍糖;三倍冰;]


    public ProductItem(String pName, String pEngName, String stag, int pPrice, int spcs, String pDescirption) {

        this.pName = pName;
        this.pEngName = pEngName;
        this.sTag = stag;
        this.pPrice = pPrice;
        this.sPCS = spcs;
        this.pDescirption = pDescirption;

        /*
        this.pId = pId;
        this.pNews = pNews;
        this.pIsNew = pIsNew;
        this.pImageUrl = pImageUrl;
        this.pSupplyTime = pSupplyTime;
        this.hasEspresso = hasEspresso;
        this.hasIce = hasIce;
        this.hasSugar = hasSugar;
        this.hasMilk = hasMilk;
        this.hasSize = hasSize;
        this.hasXLSize = hasXLSize;
        */

    }

    public int getpSupplyTime() {
        return pSupplyTime;
    }

    public void setpSupplyTime(int pSupplyTime) {
        this.pSupplyTime = pSupplyTime;
    }

    public String getpImageUrl() {
        return pImageUrl;
    }

    public void setpImageUrl(String pImageUrl) {
        this.pImageUrl = pImageUrl;
    }

    public int getpCafeine_Heat() {
        return pCafeine_Heat;
    }

    public void setpCafeine_Heat(int pCafeine_Heat) {
        this.pCafeine_Heat = pCafeine_Heat;
    }

    public String getpDescirption() {
        return pDescirption;
    }

    public void setpDescirption(String pDescirption) {
        this.pDescirption = pDescirption;
    }

    public String getpEngName() {
        return pEngName;
    }

    public void setpEngName(String pEngName) {
        this.pEngName = pEngName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public boolean ispIsNew() {
        return pIsNew;
    }

    public void setpIsNew(boolean pIsNew) {
        this.pIsNew = pIsNew;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpNews() {
        return pNews;
    }

    public void setpNews(String pNews) {
        this.pNews = pNews;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public boolean isHasEspresso() {
        return hasEspresso;
    }

    public void setHasEspresso(boolean hasEspresso) {
        this.hasEspresso = hasEspresso;
    }

    public boolean isHasIce() {
        return hasIce;
    }

    public void setHasIce(boolean hasIce) {
        this.hasIce = hasIce;
    }

    public boolean isHasSugar() {
        return hasSugar;
    }

    public void setHasSugar(boolean hasSugar) {
        this.hasSugar = hasSugar;
    }

    public boolean isHasMilk() {
        return hasMilk;
    }

    public void setHasMilk(boolean hasMilk) {
        this.hasMilk = hasMilk;
    }

    public boolean isHasSize() {
        return hasSize;
    }

    public void setHasSize(boolean hasSize) {
        this.hasSize = hasSize;
    }

    public boolean isHasXLSize() {
        return hasXLSize;
    }

    public void setHasXLSize(boolean hasXLSize) {
        this.hasXLSize = hasXLSize;
    }

    public int getsPCS() {
        return sPCS;
    }

    public void setsPCS(int sPCS) {
        this.sPCS = sPCS;
    }

    public String getsTag() {
        return sTag;
    }

    public void setsTag(String sTag) {
        this.sTag = sTag;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "pType='" + pType + '\'' +
                ", pName='" + pName + '\'' +
                ", pEngName='" + pEngName + '\'' +
                ", pDescirption='" + pDescirption + '\'' +
                ", pPrice=" + pPrice +
                ", pCafeine_Heat=" + pCafeine_Heat +
                ", pNews='" + pNews + '\'' +
                ", pIsNew=" + pIsNew +
                ", pImageUrl='" + pImageUrl + '\'' +
                ", pSupplyTime=" + pSupplyTime +
                ", hasEspresso=" + hasEspresso +
                ", hasIce=" + hasIce +
                ", hasSugar=" + hasSugar +
                ", hasMilk=" + hasMilk +
                ", hasSize=" + hasSize +
                ", hasXLSize=" + hasXLSize +
                ", sPCS=" + sPCS +
                ", sTag='" + sTag + '\'' +
                '}';
    }
}
