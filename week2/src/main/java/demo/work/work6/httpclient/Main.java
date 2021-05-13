package demo.work.work6.httpclient;



public class Main {

    public static void main(String[] args){
        String url = "http://127.0.0.1:8801";
        System.out.println(HttpClientUtil.get(url));
    }


}
