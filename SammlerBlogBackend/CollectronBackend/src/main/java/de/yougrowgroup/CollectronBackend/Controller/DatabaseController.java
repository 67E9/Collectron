package de.yougrowgroup.CollectronBackend.Controller;

import de.yougrowgroup.CollectronBackend.Model.Collectible;
import de.yougrowgroup.CollectronBackend.Model.Type;
import de.yougrowgroup.CollectronBackend.Service.CollectibleService;
import de.yougrowgroup.CollectronBackend.Service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DatabaseController {
    private final CollectibleService collectibleService;
    private final TypeService typeService;

    public DatabaseController(CollectibleService collectibleService, TypeService typeService) {
        this.collectibleService = collectibleService;
        this.typeService = typeService;
    }

    @GetMapping("/collectible/findById/{id}")
    public Collectible findCollectibleById (@PathVariable Integer id){
        return collectibleService.findCollectibleById(id);
    }

    @GetMapping("/type/findById/{id}")
    public Type findTypeById (@PathVariable Integer id){
        return typeService.findTypeById(id);
    }

    @GetMapping("/type/findByName/{name}")
    public List<Type> findTypeByName (@PathVariable String name){
        return typeService.findTypeByName(name);
    }


    @GetMapping("/collectible/findByName/{name}")
    public List<Collectible> findCollectibleByName (@PathVariable String name){
        return collectibleService.findCollectibleByName(name);
    }

    @GetMapping("/collectible")
    public List<Collectible> findAllCollectibles() {
        return collectibleService.findAllCollectibles();
    }

    @GetMapping("/type")
    public List<Type> findAllTypes() {
        return typeService.findAllTypes();
    }

    @GetMapping("/collectible/{type}")
    public List<Collectible> findCollectibleByType(@PathVariable String type){
        return collectibleService.findCollectibleByTypeName(type);
    }

    @GetMapping("/collectible/forSale/{forSale}")
    public List<Collectible> findCollectibleByForSale(@PathVariable Boolean forSale){
        return collectibleService.findCollectibleByForSale(forSale);
    }

    private List<Collectible> findCollectiblesByNameContains(String name) {
        return collectibleService.findCollectibleByNameContainsIgnoreCase(name);
    }

    private List<Collectible> findCollectiblesByDescription(String description) {
        return collectibleService.findCollectibleByDescriptionContainsIgnoreCase(description);
    }

    @GetMapping("collectible/find_keywords/{keywords}")
    public List<Collectible> findCollectibleByNameAndDescriptionContainsIgnoreCase(@PathVariable String keywords){
        List<Collectible> foundByNameContainsCollectibles = findCollectiblesByNameContains(keywords);
        List<Collectible> foundByDescriptionContainsCollectibles = findCollectiblesByDescription(keywords);
        foundByDescriptionContainsCollectibles.stream().filter(f -> !foundByNameContainsCollectibles.contains(f))
                .forEach(foundByDescriptionContainsCollectibles::add);
        return foundByNameContainsCollectibles;
    }

    @GetMapping("collectible/findByAllFilter")
    public List<Collectible> findCollectibleByNameAndDescriptionAndForSaleAndType (
            @RequestParam("name") String name,
            @RequestParam("keyword") String keyword,
            @RequestParam("forSale") Boolean forSale,
            @RequestParam("type") String type){
        List<Collectible> foundByNameCollectibles = findCollectibleByName(name);
        List<Collectible> foundByTypeCollectibles = findCollectibleByType(type);
        foundByNameCollectibles.stream().filter(c -> !foundByTypeCollectibles.contains(c)).forEach(foundByTypeCollectibles::add);
        List<Collectible> foundByForSaleCollectibles = findCollectibleByForSale(forSale);
        foundByTypeCollectibles.stream().filter(Collectible::getForSale).forEach(foundByForSaleCollectibles::add);
        List<Collectible> foundByDescriptionContainsCollectibles = findCollectiblesByDescription(keyword);
        foundByForSaleCollectibles.stream().filter(c -> !foundByDescriptionContainsCollectibles.contains(c))
                .forEach(foundByDescriptionContainsCollectibles::add);
        return foundByForSaleCollectibles;
    }

    private List<Type> findTypeByNameContains(String name) {
        return typeService.findTypeByNameContainsIgnoreCase(name);
    }

    private List<Type> findTypeByDescriptionContains(String description) {
        return typeService.findTypeByDescriptionContainsIgnoreCase(description);
    }

    @GetMapping("/type/findByKey/{keywords}")
    public List<Type> findTypeByNameAndDescriptionContains(@PathVariable String keywords){
        System.out.println("hello world");
        List<Type> byName = findTypeByNameContains(keywords);
        List<Type> byDescription = findTypeByDescriptionContains(keywords);
        byName.stream().
                filter(t -> !byDescription.contains(t))
                .forEach(byDescription::add);
        return byDescription;
    }

    //manipulation mapping
    @DeleteMapping("/collectible/{id}")
    public Collectible deleteCollectibleById(@PathVariable Integer id) {
        try {
            Collectible deletedCollectible = collectibleService.findCollectibleById(id);
            collectibleService.deleteCollectibleById(id);
            return deletedCollectible;
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @PutMapping("collectible")
    public Collectible updateCollectible(@Validated @RequestBody Collectible collectible) {
        try {
            collectibleService.updateCollectible(collectible);
            return collectible;
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "no collectible with such ID exists.");
        }
    }

    @PostMapping("/collectible")
    public Collectible newCollectible(@Validated @RequestBody Collectible newCollectible) {
        try {
            collectibleService.newCollectible((newCollectible));
            return newCollectible;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "collectible with this id already exists");
        }
    }

    @DeleteMapping("/type/{id}")
    public Type deleteTypeById(@PathVariable Integer id) {
        try {
            Type deletedType = typeService.findTypeById(id);
            typeService.deleteTypeById(id);
            return deletedType;
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @PutMapping("type")
    public Type updateType(@Validated @RequestBody Type type) {
        try{
            typeService.updateType(type);
            return type;
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "no type with such id exists");
        }
    }

    @PostMapping("/type")
    public Type newType(@Validated @RequestBody Type newType) {
        try {
            typeService.newType((newType));
            return newType;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "existing ID");
        }
    }
}
