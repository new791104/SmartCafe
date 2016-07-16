package ccu.ant.countertable.Fragment;

public interface OnHeadlineSelectedListener {

//======================>與Activity溝通區塊,可以當作制式化
    /**
     * Called by HeadlinesFragment when a item is selected
     */
//這裡可以設定傳遞的資料型態
    public void onRemoveSelected(int data_int);
    public void onAddSelected();
}
