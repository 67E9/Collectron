package de.yougrowgroup.CollectronBackend.Repository;

import de.yougrowgroup.CollectronBackend.Model.Collectible;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectibleRepository extends CrudRepository<Collectible, Integer> {

    /**
     * Finds a collectible by a given namen
     * @param name Collectible name
     * @return Returns a list of name matched collectibles
     */
    List<Collectible> findCollectibleByName(String name);

    /**
     * Gives the total list of all collectibles
     * @return all Collectibles
     */
    List<Collectible> findAll();

    /**
     * By giving the type of collection, a resp list will be given.
     * @param typeName type string
     * @return all Collectibles of a specific type
     */
    List<Collectible> findCollectibleByTypeName(String typeName);

    /**
     * Requesting by forSale boolean = true will give a list of all collectibles, which are for sale
     * @param forSale gives a boolean value
     * @return all collectibles that are for sale
     */
    List<Collectible> findCollectibleByForSale(Boolean forSale);

    /**
     * Filters by a keyword the names of collectibles and returns a list.
     * @param keywords the filter parameter
     * @return a filtered collectible list
     */
    List<Collectible> findCollectibleByNameContainsIgnoreCase(String keywords);

    /**
     * Filters by a keyword the description of collectibles and returns a list.
     * @param keywords the filter parameter
     * @return a filtered collectible list
     */
    List<Collectible> findCollectibleByDescriptionContainsIgnoreCase(String keywords);
}
