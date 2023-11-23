package ra.presentation;

import ra.business.service.IProductService;
import ra.business.service.implement.ProductService;
import ra.business.util.InputMethods;
import ra.business.validate.Validate;

import static ra.presentation.CatalogManagement.catalogManagement;
import static ra.presentation.ProductManagement.productManagement;

public class Main {
    public static final IProductService productService = new ProductService(); // đây là 1 instance duy nhất có phạm vi
    public static void main(String[] args) {
        while (true){
            System.out.println("================= ADMIN MANAGEMENT ================");
            System.out.println("1. Quản lý danh mục. ");
            System.out.println("2. Quản lý sản phẩm. ");
            System.out.println("3. Thoát chương trình. ");
            System.out.print("Hãy chọn chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    catalogManagement();
                    break;
                case 2:
                    productManagement(productService);
                    break;
                case 3:
                    System.out.println("Chương trình đã tắt!");
                    System.exit(0);
                    break;
                default:
                    System.err.println(Validate.ERROR_ALERT);
            }
        }
    }
}