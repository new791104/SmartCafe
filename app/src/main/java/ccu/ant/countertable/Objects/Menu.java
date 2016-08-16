package ccu.ant.countertable.Objects;

/**
 * Created by Charlie on 2016/7/25.
 */
public class Menu {
    public int pId;         //產品ID
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

    public Menu(int pId, String pName, String pEngName, String pDescirption, int pPrice, int pCafeine_Heat, String pNews, boolean pIsNew, String pImageUrl, int pSupplyTime, boolean hasEspresso, boolean hasIce, boolean hasSugar, boolean hasMilk, boolean hasSize, boolean hasXLSize) {
        this.pId = pId;
        this.pName = pName;
        this.pEngName = pEngName;
        this.pDescirption = pDescirption;
        this.pPrice = pPrice;
        this.pCafeine_Heat = pCafeine_Heat;
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
    }


    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpEngName() {
        return pEngName;
    }

    public void setpEngName(String pEngName) {
        this.pEngName = pEngName;
    }

    public String getpDescirption() {
        return pDescirption;
    }

    public void setpDescirption(String pDescirption) {
        this.pDescirption = pDescirption;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getpCafeine_Heat() {
        return pCafeine_Heat;
    }

    public void setpCafeine_Heat(int pCafeine_Heat) {
        this.pCafeine_Heat = pCafeine_Heat;
    }

    public String getpNews() {
        return pNews;
    }

    public void setpNews(String pNews) {
        this.pNews = pNews;
    }

    public boolean ispIsNew() {
        return pIsNew;
    }

    public void setpIsNew(boolean pIsNew) {
        this.pIsNew = pIsNew;
    }

    public String getpImageUrl() {
        return pImageUrl;
    }

    public void setpImageUrl(String pImageUrl) {
        this.pImageUrl = pImageUrl;
    }

    public int getpSupplyTime() {
        return pSupplyTime;
    }

    public void setpSupplyTime(int pSupplyTime) {
        this.pSupplyTime = pSupplyTime;
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
}
