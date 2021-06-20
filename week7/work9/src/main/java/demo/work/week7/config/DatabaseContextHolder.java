package demo.work.week7.config;

import java.util.ArrayList;
import java.util.List;

public class DatabaseContextHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 只保存从库
     */
    private static List<String> dataSources = new ArrayList<>();

    public static void setDbType(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDbType(){
        return contextHolder.get();
    }

    public static void clearDbType(){
        contextHolder.remove();
    }

    public static void setDataSources(List<String> list){
        dataSources = list;
    }

    public static List<String> getDataSources(){
        return dataSources;
    }
}
