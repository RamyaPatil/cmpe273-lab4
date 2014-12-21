package edu.sjsu.cmpe.cache.client;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by ramya on 12/20/14.
 */

class PutCallBackImpl implements Callback<JsonNode> {
    private final String serverUrl;
    private final Map<String, Boolean> myMap;
    public PutCallBackImpl(String serverUrl, Map<String, Boolean> myMap){
        this.serverUrl = serverUrl;
        this.myMap = myMap;
    }
    public void failed(UnirestException e) {
        System.out.println("Request Failed to node with url "+ serverUrl);
        myMap.put(serverUrl, false);
    }
    public void completed(HttpResponse<JsonNode> response) {
        if(response.getStatus()==200){
            System.out.println("Request Succeeded for node "+ serverUrl);
            myMap.put(serverUrl,true);
        }else{
            myMap.put(serverUrl,false);
        }
    }
    public void cancelled() {
        System.out.println("The request has been cancelled");
    }
}