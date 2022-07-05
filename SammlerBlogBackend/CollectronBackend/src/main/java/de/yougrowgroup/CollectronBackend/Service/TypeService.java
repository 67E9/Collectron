package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.Type;

import java.util.List;

public interface TypeService {

    //CRUD select services for Type
    Type findTypeById(Integer id);

    List<Type> findAllTypes();

    List<Type> findTypeByName(String name);

    List<Type> findTypeByNameContainsIgnoreCase(String keywords);
    List<Type> findTypeByDescriptionContainsIgnoreCase(String keywords);

    //CRUD manipulation methods:

    void newType (Type newType);

    void updateType (Type type);

    void deleteTypeById (Integer id);

}
