package ra.presentation;

import ra.business.model.Catalog;
import ra.business.service.ICatalogService;
import ra.business.service.implement.CatalogService;
import ra.business.util.InputMethods;
import ra.business.validate.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CatalogManagement {
    public static final ICatalogService catalogService = new CatalogService();
    public static void catalogManagement(){
        while (true){
            System.out.println("================ CATALOG MANAGEMENT =================");
            System.out.println("1. Hiển thị danh mục (SX tt thêm mới) ");
            System.out.println("2. Thêm mới danh mục. ");
            System.out.println("3. Tìm kiếm danh mục theo tên (TK tương đối) ");
            System.out.println("4. Sửa thông tin danh mục (Không sửa trạng thái) ");
            System.out.println("5. Ẩn/hiện danh mục theo id (true/false) ");
            System.out.println("6. Trở lại. ");
            System.out.println("======================================================");
            System.out.print("Hãy chọn chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    displayAllCatalog();
                    break;
                case 2:
                    addNewCatalog();
                    break;
                case 3:
                    findCatalogByName();
                    break;
                case 4:
                    editCatalogById();
                    break;
                case 5:
                    showHideById();
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

    private static void displayAllCatalog() {
        System.out.println("Danh sách danh mục: ");
        List<Catalog> sortNew = catalogService.findALl().stream().sorted((o1, o2) -> Math.toIntExact(o2.getCatalogId() - o1.getCatalogId())).collect(Collectors.toList());
        for (Catalog cat:sortNew) {
            if (cat != null){
                System.out.println(cat);
            }
        }
    }
    private static void addNewCatalog() {
        System.out.println("Thêm mới danh mục!");
        Catalog catalog = new Catalog();
        catalog.setCatalogId(catalogService.getNewId());
        catalog.setStatus(true);
        catalog.inputCatalog();
        while (true){
            if (!catalogService.exitsByCatalogName(catalog.getCatalogName())){
                break;
            }
            System.out.print(catalog.getCatalogName() + " "+ Validate.ERROR_EXITSBYNAME);
            catalog.setCatalogName(InputMethods.getString());
        }
        catalogService.save(catalog);
        System.out.println(Validate.SUCCESS_ADD);
    }
    private static void findCatalogByName() {
        List<Catalog> listFind = new ArrayList<>();
        System.out.println("Tìm kiếm danh mục theo tên (TK tương đối)");
        System.out.print("Nhập tên cần tìm: ");
        String findCatName = InputMethods.getString();
        for (Catalog cat:catalogService.findALl()) {
            if (cat.getCatalogName().toUpperCase().contains(findCatName.toUpperCase())){
                listFind.add(cat);
            }
        }
        System.out.println("Danh mục cần tìm: ");
        if (listFind.isEmpty()){
            System.err.println(findCatName+" "+Validate.ERROR_FIND);
        }else {
            for (Catalog c:listFind) {
                System.out.println(c);
            }
        }
    }
    private static void editCatalogById() {
        displayAllCatalog();
        System.out.print("Chọn id danh mục cần sửa: ");
        Long idEdit = InputMethods.getLong();
        Catalog catalog = new Catalog();
        if (catalogService.findById(idEdit) != null){
            catalog.setCatalogId(idEdit);
            catalog.setStatus(catalogService.findById(idEdit).isStatus());
            catalog.inputCatalog();
            while (true){
                if (!catalogService.exitsByCatalogName(catalog.getCatalogName())){
                    break;
                }
                System.out.println(catalog.getCatalogName() + " "+ Validate.ERROR_EXITSBYNAME);
                catalog.inputCatalog();
            }
            catalogService.save(catalog);
            System.out.println(Validate.SUCCESS_UPDATE);
        }else {
            System.out.println("ID "+ idEdit + " "+ Validate.ERROR_FIND);
        }

    }
    private static void showHideById() {
        displayAllCatalog();
        System.out.print("Chọn id cần Ẩn/Hiện: ");
        Long id = InputMethods.getLong();
        Catalog catalog = new Catalog();
        if (catalogService.findById(id) != null){
            catalog = catalogService.findById(id);
            if (catalog.isStatus()){
                catalog.setStatus(false);
            }else {
                catalog.setStatus(true);
            }
            System.out.println(Validate.SUCCESS_UPDATE);
            catalogService.save(catalog);
            displayAllCatalog();
        }else {
            System.err.println("ID "+ id + " "+ Validate.ERROR_FIND);
        }
    }

}
