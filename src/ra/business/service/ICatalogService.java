package ra.business.service;

import ra.business.model.Catalog;

public interface ICatalogService extends IGeneric<Catalog, Long> {
    boolean exitsByCatalogName(String catalogName);
    boolean exitsByCatalogId(Long catalogId);
}
