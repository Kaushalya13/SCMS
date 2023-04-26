package lk.ijse.scms.dto;

public class PlaceOrderDTO {
    private String order_id;
    private String itemCode;
    private Double unitPrice;
    private Integer qty;
    private Double total;

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String order_id, String itemCode, Double unitPrice, Integer qty, Double total) {
        this.order_id = order_id;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PlaceOrderDTO{" +
                "order_id='" + order_id + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
