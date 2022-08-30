package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.Collectible;

import java.util.List;

public interface CollectibleService {
    //CRUD select methods:
    Collectible findCollectibleById(Integer id);

    List<Collectible> findCollectibleByName(String name);

    List<Collectible> findAllCollectibles();

    List<Collectible> findCollectibleByTypeName(String typeName);

    List<Collectible> findCollectibleByForSale(Boolean forSale);

    List<Collectible> findCollectibleByNameContainsIgnoreCase(String keywords);
    List<Collectible> findCollectibleByDescriptionContainsIgnoreCase(String keywords);
    
    
    //CRUD manipulation methods:
    void newCollectible (Collectible collectible);
    
    void deleteCollectibleById (Integer id);

    void updateCollectible (Collectible collectible);

}
