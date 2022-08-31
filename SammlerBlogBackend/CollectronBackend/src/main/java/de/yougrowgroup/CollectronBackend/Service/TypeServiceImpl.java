package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Model.Type;
import de.yougrowgroup.CollectronBackend.Repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    //CRUD select services
    @Override
    public Type findTypeById(Integer id) {
        return typeRepository.findById(id).orElse(new Type());
    }

    @Override
    public List<Type> findAllTypes() {
        return (List<Type>) typeRepository.findAll();
    }

    @Override
    public List<Type> findTypeByName(String name) {
        return typeRepository.findTypeByName(name);
    }

    @Override
    public List<Type> findTypeByNameContainsIgnoreCase(String keywords) {
        return typeRepository.findTypeByNameContainsIgnoreCase(keywords);
    }

    @Override
    public List<Type> findTypeByDescriptionContainsIgnoreCase(String keywords) {
        return typeRepository.findTypeByDescriptionContainsIgnoreCase(keywords);
    }

    //CRUD manipulation services
    @Override
    public void newType(Type newType) {
        if (newType.getId() == null || !typeRepository.existsById(newType.getId())) {
            typeRepository.save(newType);
        } else {
            throw new IllegalArgumentException("Cannot create Type. Type with same id already exists.");
        }
    }

    @Override
    public void updateType(Type updatedType) {
        if (updatedType.getId() != null && typeRepository.existsById(updatedType.getId())) {
            typeRepository.save(updatedType);
        } else {
            throw new IllegalArgumentException("Cannot update Type. No type with such Id exists");
        }
    }

    @Override //todo: error handling if collectibles are existing
    public void deleteTypeById(Integer id) {
        if (typeRepository.existsById(id)) {
            Type type = findTypeById(id);
            if (type.getCollectibles().isEmpty()) {
                typeRepository.delete(type);
            } else {
                System.out.println("Delete aborted. Deleting type " + type.getName() + " with id " + type.getId() +
                        " would lead to database inconsistency, because there are still collectibles with this type");
                throw new IllegalArgumentException("there are still collectibles dependent on this type");
            }
        } else {
            System.out.println("Delete aborted. No type with such id.");
            throw new IllegalArgumentException("no type with such id");
        }
    }
}
