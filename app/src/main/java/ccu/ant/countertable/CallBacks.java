package ccu.ant.countertable;

/**
 * Created by Charlie on 2016/8/2.
 */
public interface CallBacks{
        /**
         * 这个是小李知道答案时要调用的函数告诉小王，也就是回调函数
         * @param result 是答案
         */
        public String response(String result);
}
