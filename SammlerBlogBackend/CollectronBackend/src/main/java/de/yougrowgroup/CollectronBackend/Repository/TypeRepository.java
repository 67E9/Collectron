package de.yougrowgroup.CollectronBackend.Repository;

import de.yougrowgroup.CollectronBackend.Model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {

    /**
     * Finds a type by a given namen
     * @param name Type name
     * @return Returns a list of name matched type
     */
    List<Type> findTypeByName(String name);


    /**
     * Filters by a keyword the names of type and returns a list.
     * @param keywords the filter parameter
     * @return a filtered type list
     */
    List<Type> findTypeByNameContainsIgnoreCase(String keywords);

    /**
     * Filters by a keyword the description of type and returns a list.
     * @param keywords the filter parameter
     * @return a filtered type list
     */
    List<Type> findTypeByDescriptionContainsIgnoreCase(String keywords);
}
