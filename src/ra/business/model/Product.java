package ra.business.model;

import ra.business.service.IProductService;
import ra.business.service.implement.ProductService;
import ra.business.util.FormatDate;
import ra.business.util.InputMethods;
import ra.business.validate.Validate;
import ra.presentation.ProductManagement;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable {
     // đối tượng này là 1 instance có địa chỉ khác với cái e đang thao tác => sửa như sau
    // chú ý chi nên tạo 1 instance duy nhất

    private Long productId;
    private String productName;
    private Long categoryId;
    private String description;
    private double unitPrice;
    private int stock;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean status;


    public Product() {
    }

    public Product(Long productId, String productName, Long categoryId, String description,
                   double unitPrice, int stock, LocalDateTime createAt, LocalDateTime updateAt, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product[ ID:" +productId+" | Name: "+productName+" | CategoryID: "+ categoryId+
                " | Desc: "+description+" | unitPrice: "+unitPrice+" | Stock: "+stock+
                " | Create_At: "+ FormatDate.formatDate(createAt) +" | Update_At: "+FormatDate.formatDate(updateAt)+
                " | Status: "+status+" ]";
    }

    public void inputProduct(IProductService productService){
        while (true){
            System.out.print("Nhập tên sản phẩm: ");
            String pName = InputMethods.getString();
            if (Validate.hasLeast6Char(pName)){
                if (!productService.exitsByProductName(pName)){
                    this.productName = pName;
                    break;
                }else {
                    System.err.println(Validate.ERROR_EXITSBYNAME);
                }
            }else {
                System.err.println(Validate.ERROR_6CHAR);
            }

        }
        System.out.print("Nhập mô tả: ");
        this.description = InputMethods.getString();
        while (true){
            System.out.print("Nhập đơn giá: ");
            double price = InputMethods.getDouble();
            if (Validate.checkBigger(price)){
                this.unitPrice = price;
                break;
            }else {
                System.err.println(Validate.ERROR_NUMBER_BIG);
            }
        }
        while (true){
            System.out.print("Nhập số lượng: ");
            int s = InputMethods.getInteger();
            if (Validate.checkBiggerOrEquals(s)){
                this.stock = s;
                break;
            }else {
                System.err.println(Validate.ERROR_NUMBER_POSITIVE );
            }
        }
    }

}
