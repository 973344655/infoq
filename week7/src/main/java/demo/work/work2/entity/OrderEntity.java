package demo.work.work2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderEntity {

    private String id;
    private String orderNo;
    private String customerId;

    private int productNum;
    private String productDetailId;
    private double amount;
    private String transactionId;
    private String amountDetailId;

    private String address;

    private int status;

    private String payTime;
    private String deliveryTime;

    private String createTime;
    private String updateTime;

    public OrderEntity(int i){
        String time = String.valueOf(System.currentTimeMillis());
        this.createTime = time;
        this.updateTime = time;
        this.status = 0;
        this.orderNo = UUID.randomUUID().toString();

        String index = Integer.toString(i);
        this.customerId = index;
        this.productDetailId = index;
        this.transactionId = index;
        this.amountDetailId = index;

        this.address = "收获地址: " + index;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\"").append(this.id == null ? "" : this.id).append("\",");
        sb.append("\"").append(this.orderNo).append("\",");
        sb.append("\"").append(this.customerId).append("\",");
        sb.append("\"").append(this.productNum).append("\",");
        sb.append("\"").append(this.productDetailId).append("\",");
        sb.append("\"").append(this.amount).append("\",");
        sb.append("\"").append(this.transactionId).append("\",");
        sb.append("\"").append(this.amountDetailId).append("\",");
        sb.append("\"").append(this.address).append("\",");
        sb.append("\"").append(this.status).append("\",");
        sb.append("\"").append(this.payTime == null ? "" : this.payTime).append("\",");
        sb.append("\"").append(this.deliveryTime == null ? "" : this.deliveryTime).append("\",");
        sb.append("\"").append(this.createTime).append("\",");
        sb.append("\"").append(this.updateTime).append("\",");

        return sb.toString();
    }
}
