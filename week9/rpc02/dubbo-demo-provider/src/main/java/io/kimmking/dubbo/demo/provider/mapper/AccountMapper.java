package io.kimmking.dubbo.demo.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    @Update("update account set balance = balance - #{amount}," +
            " freeze_amount= freeze_amount + #{amount} ," +
            " balance1 = balance1 + #{amount}/7 ," +
            " freeze_amount1 = freeze_amount1 + #{amount}/7," +
            " update_time = now()" +
            " where user_id =#{id}  and  balance >= #{amount}  ")
    int rmb2Dollar(@Param("id") int id, @Param("amount") int amount);

    @Update("update account set balance = balance + #{amount}*7," +
            " freeze_amount= freeze_amount + #{amount}*7 ," +
            " balance1 = balance1 - #{amount} ," +
            " freeze_amount1 = freeze_amount1 + #{amount}," +
            " update_time = now()" +
            " where user_id =#{id}  and  balance1 >= #{amount}  ")
    int dollar2Rmb(@Param("id") int id, @Param("amount") int amount);


    @Update("update account set " +
            " freeze_amount= freeze_amount - #{amount}, " +
            " freeze_amount1= freeze_amount - #{amount}/7 " +
            " where user_id =#{id}  and freeze_amount >= #{amount} ")
    int confirm(@Param("id") int id, @Param("amount") int amount);

    @Update("update account set " +
            " freeze_amount1= freeze_amount1 - #{amount}, " +
            " freeze_amount= freeze_amount - #{amount}*7 " +
            " where user_id =#{id}  and freeze_amount1 >= #{amount} ")
    int confirm1(@Param("id") int id, @Param("amount") int amount);

    @Update("update account set balance = balance + #{amount}," +
            " freeze_amount= freeze_amount -  #{amount}, " +
            " balance1= balance -  #{amount}/7, " +
            " freeze_amount1= freeze_amount1 -  #{amount}/7 " +
            " where user_id =#{id}  and freeze_amount >= #{amount}")
    int cancel(@Param("id") int id, @Param("amount") int amount);

    @Update("update account set balance = balance1 + #{amount}," +
            " freeze_amount1= freeze_amount1 -  #{amount}, " +
            " balance= balance -  #{amount}*7, " +
            " freeze_amount= freeze_amount -  #{amount}*7 " +
            " where user_id =#{id}  and freeze_amount >= #{amount}")
    int cancel1(@Param("id") int id, @Param("amount") int amount);
}
