package lk.ijse.scms.dto.tm;

public class ItemTM {
    String itemCode;
    String itemType;
    String description;
    Double unitPrice;
    Integer qtyOnStock;

    public ItemTM() {
    }

    public ItemTM(String itemCode, String itemType, String description, Double unitPrice, Integer qtyOnStock) {
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnStock = qtyOnStock;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQtyOnStock() {
        return qtyOnStock;
    }

    public void setQtyOnStock(Integer qtyOnStock) {
        this.qtyOnStock = qtyOnStock;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "itemCode='" + itemCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnStock=" + qtyOnStock +
                '}';
    }
}