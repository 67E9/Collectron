package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.Type;
import de.yougrowgroup.CollectronBackend.Repository.TypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTests {

    //TODO: finish TypeServiceTests

    @Mock
    TypeRepository mockTypeRepo;

    final int existingId = 666;
    final int nonExistingId = 616;
    final Type newTypeWithId = new Type(nonExistingId, "name", "description");
    final Type existingTypeWithId = new Type(existingId, "name", "description");
    final Type newTypeWithoutId = new Type(null, "name", "description");
    TypeService typeService;

    @BeforeEach
    void init(){
        typeService = new TypeServiceImpl(mockTypeRepo);
    }

    @Test
    void newTypeTest(){
        when(mockTypeRepo.existsById(existingId)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> typeService.newType(existingTypeWithId));
        when(mockTypeRepo.existsById(nonExistingId)).thenReturn(false);
        assertDoesNotThrow(()->typeService.newType(newTypeWithId));
        assertDoesNotThrow(()->typeService.newType(newTypeWithoutId));
    }

    @Test
    void updateTypeTest(){
        when(mockTypeRepo.existsById(existingId)).thenReturn(true);
        assertDoesNotThrow(() -> typeService.updateType(existingTypeWithId));
        when(mockTypeRepo.existsById(nonExistingId)).thenReturn(false);;
        assertThrows(IllegalArgumentException.class, () -> typeService.updateType(newTypeWithId));
        assertThrows(IllegalArgumentException.class, () -> typeService.updateType(newTypeWithoutId));
    }

    @Test
    void deleteTypeByIdTest(){
//        when(mockTypeRepo.existsById(existingId)).thenReturn(true);
//        assertDoesNotThrow(()->typeService.deleteTypeById(existingId));
        //TODO: fix successful delete test
        when(mockTypeRepo.existsById(nonExistingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, ()->typeService.deleteTypeById(nonExistingId));
        assertThrows(IllegalArgumentException.class, ()->typeService.deleteTypeById(null));
    }
}
