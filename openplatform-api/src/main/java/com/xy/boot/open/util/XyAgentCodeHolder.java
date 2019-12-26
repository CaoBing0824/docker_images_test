package com.xy.boot.open.util;

import org.springframework.stereotype.Component;

@Component
public class XyAgentCodeHolder {

    private static ThreadLocal<String> xyAgentCodes = new ThreadLocal<String>();
    private static ThreadLocal<String> xyAgentAppSecret = new ThreadLocal<String>();

    public void setXyAgentAppSecret(String appSecret) {
        xyAgentAppSecret.set(appSecret);
    }
    public String getXyAgentAppSecret() {
        return xyAgentAppSecret.get();
    }

    public void setAgentCode(String xyCode){
        xyAgentCodes.set(xyCode);
    }

    public String getAgentCode(){
        return xyAgentCodes.get();
    }

    public void clear(){
        xyAgentCodes.remove();
    }

    public void clearAppSecret(){
        xyAgentAppSecret.remove();
    }
}
