package ra.business.service.implement;

import ra.business.config.IOFile;
import ra.business.model.Product;
import ra.business.service.IProductService;

import java.util.List;
import java.util.Objects;

public class ProductService implements IProductService {
    public List<Product> products;

    public ProductService() {
        products = IOFile.readFromFile(IOFile.PRODUCT_PATH);
    }

    @Override
    public List<Product> findALl() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        for (Product p:products) {
            if (p.getProductId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean save(Product product) {
        if (findById(product.getProductId()) != null){
            products.set(products.indexOf(findById(product.getProductId())), product);
        }else {
            products.add(product);
        }
        IOFile.writeToFile(IOFile.PRODUCT_PATH, products);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        products.remove(findById(id));
        IOFile.writeToFile(IOFile.PRODUCT_PATH, products);
    }

    @Override
    public Long getNewId() {
        Long idMax = 0L;
        for (Product p:products) {
            if (idMax < p.getProductId()) idMax =p.getProductId();
        }
        return (idMax+1);
    }

    @Override
    public boolean exitsByProductName(String productName) {
        for (Product p:products) {
            if (p != null && Objects.equals(p.getProductName(), productName)){
                return true;
            }
        }
        return false;
    }
}
