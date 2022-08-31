package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Model.Collectible;
import de.yougrowgroup.CollectronBackend.Repository.CollectibleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectibleServiceImpl implements CollectibleService {
    private final CollectibleRepository collectibleRepository;

    public CollectibleServiceImpl(CollectibleRepository collectibleRepository) {
        this.collectibleRepository = collectibleRepository;
    }

    //CRUD manipulation services:
    @Override
    public void updateCollectible(Collectible updatedCollectible) {
        if (updatedCollectible.getId() != null && collectibleRepository.existsById(updatedCollectible.getId())){
            collectibleRepository.save(updatedCollectible);
        } else {
            throw new IllegalArgumentException("Cannot update. Collectible with this id does not exist");
        }
    }

    @Override //todo: error handling if blogPosts are existing
    public void deleteCollectibleById(Integer id) {
        if (collectibleRepository.existsById(id)) {
            Collectible collectible = findCollectibleById(id);

            if(collectible.getBlogPosts().isEmpty()) {
                collectibleRepository.delete(collectible);
            } else {
                System.out.println("Delete aborted. Deleting collectible " + collectible.getName() + " with id " + collectible.getId() +
                        " would lead to database inconsistency, because there are still blog posts dependent on this collectible");
                throw new IllegalArgumentException("there are still blog posts dependent on this collectible");
            }
        } else {
            System.out.println("Delete aborted. No collectible with such id.");
            throw new IllegalArgumentException("no collectible with such id");
        }
    }

    @Override
    public void newCollectible(Collectible newCollectible) {
        if (newCollectible.getId() == null  ||!collectibleRepository.existsById(newCollectible.getId())) {
            collectibleRepository.save(newCollectible);
        } else {
            throw new IllegalArgumentException("Cannot create Collectible. Collectible with same id already exists.");
        }
    }

    //CRUD select services:
    @Override
    public Collectible findCollectibleById(Integer id) {
        return collectibleRepository.findById(id).orElse(new Collectible());
    }
    @Override
    public List<Collectible> findCollectibleByName(String name) {
        return collectibleRepository.findCollectibleByName(name);
    }

    @Override
    public List<Collectible> findAllCollectibles() {
        return collectibleRepository.findAll();
    }

    @Override
    public List<Collectible> findCollectibleByTypeName(String typeName) {
        return collectibleRepository.findCollectibleByTypeName(typeName);
    }

    @Override
    public List<Collectible> findCollectibleByForSale(Boolean forSale) {
        return collectibleRepository.findCollectibleByForSale(forSale);
    }

    @Override
    public List<Collectible> findCollectibleByNameContainsIgnoreCase(String keywords) {
        return collectibleRepository.findCollectibleByNameContainsIgnoreCase(keywords);
    }
    
    @Override
    public List<Collectible> findCollectibleByDescriptionContainsIgnoreCase(String keywords) {
        return collectibleRepository.findCollectibleByDescriptionContainsIgnoreCase(keywords);
    }
}
