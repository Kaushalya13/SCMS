package lk.ijse.scms.dto.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    private String order_id;
    private String itemCode;
    private String itemType;
    private Double unitPrice;
    private Integer qty;
    private Double total;
    private Button btn;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String order_id, String itemCode, String itemType, Double unitPrice, Integer qty, Double total, Button btn) {
        this.order_id = order_id;
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.btn = btn;
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "order_id='" + order_id + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                ", btn=" + btn +
                '}';
    }
}
