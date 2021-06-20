//package demo.work.work2.service;
//
//import demo.work.work2.entity.OrderEntity;
//import demo.work.work2.mapper.DemoMapper;
//import demo.work.work2.utils.CSVUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
///**
// * @author xxl
// */
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private int count = 1000000;
//    private Object object = new Object();
//
//    private final DemoMapper demoMapper;
//
//    /**
//     * 每次插入一条 插入100W次
//     */
//    public void insertOne(){
//
//        long start = System.currentTimeMillis();
//        System.out.println(start);
//        for(int i=0; i<count; i++){
//            OrderEntity entity = new OrderEntity(i);
//            demoMapper.insertOne(entity);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end);
//
//        System.out.println("耗时: " + (end - start));
//    }
//
//    /**
//     * 一次插入10000条，插入100次
//     */
//    public void insertBatch(){
//        long start = System.currentTimeMillis();
//        System.out.println(start);
//        List<OrderEntity> list = new ArrayList<>(10000);
//        for(int i=0; i<count; i++){
//            OrderEntity entity = new OrderEntity(i);
//            list.add(entity);
//            if(list.size() == 10000){
//                demoMapper.insertBatch(list);
//                list.clear();
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end);
//
//        System.out.println("耗时: " + (end - start));
//    }
//
//    /**
//     * 一次插入10W条，插入10次
//     */
//    public void insertBatch1(){
//        long start = System.currentTimeMillis();
//        System.out.println(start);
//        List<OrderEntity> list = new ArrayList<>(100000);
//        for(int i=0; i<count; i++){
//            OrderEntity entity = new OrderEntity(i);
//            list.add(entity);
//            if(list.size() == 100000){
//                demoMapper.insertBatch(list);
//                list.clear();
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end);
//
//        System.out.println("耗时: " + (end - start));
//    }
//
//    /**
//     * 使用线程池，每次插入1W条
//     */
//    public void insertBatch2(){
////        ExecutorService executor = Executors.newFixedThreadPool(10);
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                Runtime.getRuntime().availableProcessors(),
//                10,
//                1L,
//                TimeUnit.MINUTES,
//                new LinkedBlockingDeque<>(100),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//
//        long start = System.currentTimeMillis();
//        System.out.println(start);
//
//        for(int i=0; i<100; i++){
//            List<OrderEntity> list = new ArrayList<>(10000);
//            for(int j=0; j<10000; j++){
//                OrderEntity entity = new OrderEntity(i*10000 + j);
//                list.add(entity);
//
//                }
//            executor.submit(() -> {
//                System.out.println(Thread.currentThread().getName());
//                long t1 = System.currentTimeMillis();
//
//                demoMapper.insertBatch(list);
//                long t2 = System.currentTimeMillis();
//
//                System.out.println("used: " + (t2-t1));
//            });
//
//        }
//
//        executor.shutdown();
//        try {
//            executor.awaitTermination(3L, TimeUnit.MINUTES);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println(end);
//        System.out.println("耗时: " + (end - start));
//    }
//
//
//    /**
//     * 生成csv文件， 直接导入数据库
//     */
//    public void createCsv(){
//        List<Object> exportData = new ArrayList<Object>();
//        exportData.add("id");
//        exportData.add("order_no");
//        exportData.add("customer_id");
//        exportData.add("product_num");
//        exportData.add("product_detail_id");
//        exportData.add("amount");
//        exportData.add("transaction_id");
//        exportData.add("amount_detail_id");
//        exportData.add("address");
//        exportData.add("status");
//        exportData.add("pay_time");
//        exportData.add("delivery_time");
//        exportData.add("create_time");
//        exportData.add("update_time");
//
//        List<String> datalist = new ArrayList<>();
//        for(int i=0; i<1000000; i++){
//            OrderEntity entity = new OrderEntity(i);
//            datalist.add(entity.toString());
//        }
//
//        String path = "C:\\Users\\xxl\\Desktop\\";
//        String fileName = "100W";
//        File file = CSVUtil.createCSVFile(exportData, datalist, path, fileName);
//        String fileName2 = file.getName();
//        System.out.println("文件名称：" + fileName2);
//
//    }
//
//}
