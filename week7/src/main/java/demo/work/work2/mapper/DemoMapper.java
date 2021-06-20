package demo.work.work2.mapper;


import demo.work.work2.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DemoMapper {


    void insertOne(OrderEntity orderEntity);

    void insertBatch(@Param("list") List<OrderEntity> orderEntities);
}
