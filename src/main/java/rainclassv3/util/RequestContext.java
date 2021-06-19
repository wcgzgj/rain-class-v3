package rainclassv3.util;

import java.io.Serializable;

/**
 * @ClassName RequestContext
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/6/19 下午1:36
 * @Version 1.0
 **/
public class RequestContext implements Serializable {
    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return remoteAddr.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }
}
