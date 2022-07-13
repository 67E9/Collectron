package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.BlogPost;
import de.yougrowgroup.CollectronBackend.Entity.Collectible;
import de.yougrowgroup.CollectronBackend.Repository.CollectibleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CollectibleServiceTests {
    @Mock
    CollectibleRepository mockCollectibleRepo;

    CollectibleService collectibleService;

    final int existingId = 666;
    final int nonExistingId = 616;
    final Collectible newCollectibleWithId = new Collectible(nonExistingId);
    final Collectible collectibleWithoutId = new Collectible();
    final Collectible existingCollectibleWithId = new Collectible(existingId);



    @BeforeEach
    void init(){
        collectibleService = new CollectibleServiceImpl(mockCollectibleRepo);
    }

    @Test
    void updateCollectibleTest(){
        assertThrows(IllegalArgumentException.class, () -> collectibleService.updateCollectible(collectibleWithoutId));
        when(mockCollectibleRepo.existsById(nonExistingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> collectibleService.updateCollectible(newCollectibleWithId));
        when(mockCollectibleRepo.existsById(existingId)).thenReturn(true);
        assertDoesNotThrow(()->collectibleService.updateCollectible(existingCollectibleWithId));
    }

    @Test
    void deleteCollectibleByIdTest(){
        when(mockCollectibleRepo.existsById(nonExistingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> collectibleService.deleteCollectibleById(newCollectibleWithId.getId()));
//        when(mockCollectibleRepo.existsById(existingId)).thenReturn(true);
//        assertDoesNotThrow(()->collectibleService.deleteCollectibleById(existingCollectibleWithId.getId()));
        //TODO: fix successful delete test
    }

    @Test
    void newBlogPostTest(){
        when(mockCollectibleRepo.existsById(existingId)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> collectibleService.newCollectible(existingCollectibleWithId));
        when(mockCollectibleRepo.existsById(nonExistingId)).thenReturn(false);
        assertDoesNotThrow(()->collectibleService.newCollectible(newCollectibleWithId));
        assertDoesNotThrow(()->collectibleService.newCollectible(collectibleWithoutId));
    }

}
