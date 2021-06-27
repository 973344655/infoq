

## work02(必做题第一题)

参照视频,使用shardingsphere-proxy

结果在当前路径下:  **增删改查.jpg 里面**

配置文件在当前路径下:  **config-sharding.yaml 里面**

增删改查，都会根据配置的规则，自动转发到实际的**库.表** 上进行操作



建表(0-15), 库infoq2和infoq3
```
CREATE TABLE `infoq_orders_0` (
  `id` bigint(12) NOT NULL,
  `order_no` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单编号',
  `customer_id` int(11) DEFAULT NULL COMMENT '买家信息',
  `product_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `product_detail_id` bigint(12) DEFAULT NULL COMMENT '商品详情',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '总支付金额',
  `transaction_id` bigint(12) DEFAULT NULL COMMENT '交易详情',
  `amount_detail_id` bigint(12) DEFAULT NULL COMMENT '金额详情',
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货地址',
  `status` tinyint(1) DEFAULT NULL COMMENT '订单状态',
  `pay_time` bigint(13) DEFAULT NULL COMMENT '付款时间',
  `delivery_time` bigint(13) DEFAULT NULL COMMENT '发货时间',
  `create_time` bigint(13) DEFAULT NULL,
  `update_time` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
```
