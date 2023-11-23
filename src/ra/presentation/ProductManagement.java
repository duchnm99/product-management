package ra.presentation;

import ra.business.model.Catalog;
import ra.business.model.Product;
import ra.business.service.ICatalogService;
import ra.business.service.IProductService;
import ra.business.util.InputMethods;
import ra.business.validate.Validate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ProductManagement {

    public static final ICatalogService catService = CatalogManagement.catalogService;
    public static void productManagement(IProductService productService){
        while (true){
            System.out.println("================ PRODUCT MANAGEMENT =====================");
            System.out.println("1. Hiển thị DS sản phẩm (SX tt thêm mới) ");
            System.out.println("2. Thêm mới 1 hoặc nhiều sản phẩm. ");
            System.out.println("3. Sửa thông tin sản phẩm theo id (Không sửa trạng thái) ");
            System.out.println("4. Ẩn/hiện sản phẩm theo id (true/false) ");
            System.out.println("5. Tìm kiếm theo tên sản phẩm ");
            System.out.println("6. Trở lại. ");
            System.out.println("=========================================================");
            System.out.print("Hãy chọn chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    displayAllProduct(productService);
                    break;
                case 2:
                    addNewProduct(productService);
                    break;
                case 3:
                    editProductById(productService);
                    break;
                case 4:
                    showHideProById(productService);
                    break;
                case 5:
                    findProByName(productService);
                    break;
                case 6:
                    break;
                default:
                    System.err.println(Validate.ERROR_ALERT);
            }
            if (choice == 6){
                break;
            }
        }
    }

    private static void displayAllProduct(IProductService productService) {
        System.out.println("Danh sách sản phẩm: ");
        List<Product> productList = productService.findALl().stream()
                .sorted(((o1, o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()))).collect(Collectors.toList());
        for (Product p: productList){
            if (p != null){
                System.out.println(p);
            }
        }
    }
    private static void addNewProduct(IProductService productService) {
        System.out.println("Thêm sản phẩm!");
        for (Catalog cat:catService.findALl()) {
            System.out.println(cat);
        }
        while (true){
            System.out.print("Chọn id danh mục cần thêm mới sản phẩm:");
            Long idCat = InputMethods.getLong();
            if (catService.exitsByCatalogId(idCat)){
                System.out.print("Nhập số sản phẩm cần thêm cho danh mục: ");
                byte n = InputMethods.getByte();
                for (int i = 0; i < n; i++) {
                    Product product = new Product();
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println("Nhập thông tín sản phẩm thứ "+ (i+1));
                    product.setProductId(productService.getNewId());
                    product.setCategoryId(idCat);
                    product.setCreateAt(now);
                    product.inputProduct(productService);
                    product.setStatus(true);
                    productService.save(product);
                }
                break;
            }else {
                System.err.println(Validate.ERROR_FIND);
            }
        }

    }
    private static void editProductById(IProductService productService) {
        displayAllProduct(productService);
        System.out.print("Chọn id sản phẩm cần sửa: ");
        Long id = InputMethods.getLong();
        Product product = new Product();
        LocalDateTime now = LocalDateTime.now();
        if (productService.findById(id) != null){
            product.setProductId(id);
            product.inputProduct(productService);
            while (true){
                System.out.print("Nhập id danh mục: ");
                Long idCat = InputMethods.getLong();
                if (catService.exitsByCatalogId(idCat)){
                    product.setCategoryId(idCat);
                    break;
                }else {
                    System.err.println("ID "+ idCat + Validate.ERROR_FIND);
                }
            }
            product.setCreateAt(productService.findById(id).getCreateAt());
            product.setUpdateAt(now);
            product.setStatus(productService.findById(id).isStatus());
            productService.save(product);
            System.out.println(Validate.SUCCESS_UPDATE);
        }else {
            System.out.println("ID "+id+ " "+ Validate.ERROR_FIND);
        }
    }
    private static void showHideProById(IProductService productService) {
        displayAllProduct(productService);
        System.out.print("Chọn id cần Ẩn/Hiện: ");
        Long id = InputMethods.getLong();
        Product product = new Product();
        if (productService.findById(id) != null){
            product = productService.findById(id);
            if(product.isStatus()){
                product.setStatus(false);
            }else {
                product.setStatus(true);
            }
            System.out.println(Validate.SUCCESS_UPDATE);
            productService.save(product);
            displayAllProduct(productService);
        }else {
            System.out.println("ID "+ id + " "+ Validate.ERROR_FIND);
        }
    }
    private static void findProByName(IProductService productService) {
        List<Product> productList = new ArrayList<>();
        System.out.println("Tìm kiếm sản phẩm theo tên (TK tương đối)");
        System.out.print("Nhập tên cần tìm: ");
        String findPName = InputMethods.getString();
        for (Product p:productService.findALl()) {
            if (p.getProductName().trim().toUpperCase().contains(findPName.trim().toUpperCase())){
                productList.add(p);
            }
        }
        System.out.println("Sản phẩm cần tìm: ");
        if (productList.isEmpty()){
            System.err.println(findPName+" "+Validate.ERROR_FIND);
        }else {
            for (Product p:productList) {
                System.out.println(p);
            }
        }
    }

}
