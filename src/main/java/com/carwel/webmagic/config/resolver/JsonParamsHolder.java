
package com.carwel.webmagic.config.resolver;

import com.alibaba.fastjson.JSONObject;


public class JsonParamsHolder {

    private static ThreadLocal<JSONObject> jsonParams = new ThreadLocal<>();

    public static JSONObject getJsonParams() {
        return jsonParams.get();
    }

    public static void clear() {
        jsonParams.remove();
    }

    public static void setJsonParams(JSONObject json) {
        jsonParams.set(json);
    }
}
