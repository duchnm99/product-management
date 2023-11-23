package ra.business.service.implement;

import ra.business.config.IOFile;
import ra.business.model.Catalog;
import ra.business.service.ICatalogService;

import java.util.List;
import java.util.Objects;

public class CatalogService implements ICatalogService {

    private final List<Catalog> catalogs;
    public CatalogService() {
        catalogs = IOFile.readFromFile(IOFile.CATALOG_PATH);
    }

    @Override
    public boolean exitsByCatalogName(String catalogName) {
        for (Catalog cat:catalogs) {
            if (cat != null && Objects.equals(cat.getCatalogName(), catalogName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean exitsByCatalogId(Long catalogId) {
        for (Catalog cat:catalogs) {
            if (cat != null && Objects.equals(cat.getCatalogId(), catalogId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Catalog> findALl() {
        return catalogs;
    }

    @Override
    public Catalog findById(Long id) {
        return catalogs.stream().filter(cat -> cat.getCatalogId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean save(Catalog catalog) {
        Catalog oldCatalog = findById(catalog.getCatalogId());
        if (oldCatalog != null){
            catalogs.set(catalogs.indexOf(oldCatalog), catalog);
        }else {
            catalogs.add(catalog);
        }
        IOFile.writeToFile(IOFile.CATALOG_PATH, catalogs);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        catalogs.remove(findById(id));
        IOFile.writeToFile(IOFile.CATALOG_PATH, catalogs);
    }

    @Override
    public Long getNewId() {
        return catalogs.stream().map(Catalog::getCatalogId).max(Long::compare).orElse(0L)+1;
    }
}
