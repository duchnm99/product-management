package ra.business.model;

import ra.business.util.InputMethods;
import ra.business.validate.Validate;

import java.io.Serializable;

public class Catalog implements Serializable {
    private Long catalogId;
    private String catalogName;
    private String description;
    private boolean status;

    public Catalog() {
    }

    public Catalog(Long catalogId, String catalogName, String description, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.status = status;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
//        return "| "+catalogId+"  | "+catalogName+"   | "+description+"           | "+status+"       |";
        return "Catalog[ ID: " + catalogId +" | Name: "+ catalogName + " | Desc: "+ description + " | Status: "+ status+ " |";
    }


    public void inputCatalog(){
        System.out.print("Nhập tên danh mục: ");
        this.catalogName = InputMethods.getString();
        System.out.print("Nhập mô tả: ");
        this.description = InputMethods.getString();
    }
}
