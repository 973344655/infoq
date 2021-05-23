package demo.work.work6.httpclient;


import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        String url = "http://127.0.0.1:8888";
        Map<String,String> params = new HashMap<>();
        params.put("key1","value1");
        System.out.println(HttpClientUtil.get(url,params));
    }


}
