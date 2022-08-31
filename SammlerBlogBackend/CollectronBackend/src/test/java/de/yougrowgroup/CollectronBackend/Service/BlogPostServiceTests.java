package de.yougrowgroup.CollectronBackend.Service;
import de.yougrowgroup.CollectronBackend.Model.BlogPost;
import de.yougrowgroup.CollectronBackend.Model.Collectible;
import de.yougrowgroup.CollectronBackend.Repository.BlogPostRepository;
import de.yougrowgroup.CollectronBackend.Repository.CollectibleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BlogPostServiceTests {
    @Mock
    BlogPostRepository mockBlogRepo;
    @Mock
    CollectibleRepository mockCollectibleRepo;
    BlogPostService blogPostService;

    final int existingId = 666;
    final int nonExistingId = 616;
    final Collectible collectible1 = new Collectible(1);
    final BlogPost newBlogPostWithId = new BlogPost(nonExistingId, "title", "article", LocalDateTime.now(), collectible1);
    final BlogPost existingBlogPostWithId = new BlogPost(existingId, "title", "article", LocalDateTime.now(), collectible1);
    final BlogPost blogPostWithoutId = new BlogPost( "title", "article", LocalDateTime.now(), collectible1);

    @BeforeEach
    void init(){
         blogPostService = new BlogPostServiceImpl(mockBlogRepo, mockCollectibleRepo);
    }

    @Test
    void newBlogPostTest(){
        when(mockBlogRepo.existsById(existingId)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> blogPostService.newBlogPost(existingBlogPostWithId));
        when(mockBlogRepo.existsById(nonExistingId)).thenReturn(false);
        assertDoesNotThrow(() -> blogPostService.newBlogPost(newBlogPostWithId));
        assertDoesNotThrow(() -> blogPostService.newBlogPost(blogPostWithoutId));
    }

    @Test
    void updateBlogPostTest(){
        assertThrows(IllegalArgumentException.class, () -> blogPostService.updateBlogPost(blogPostWithoutId));
        when(mockBlogRepo.existsById(nonExistingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> blogPostService.updateBlogPost(newBlogPostWithId));
        when(mockBlogRepo.existsById(existingId)).thenReturn(true);
        assertDoesNotThrow(() -> blogPostService.updateBlogPost(existingBlogPostWithId));
    }

    @Test
    void DeleteBlogPostTest(){
        when(mockBlogRepo.existsById(nonExistingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> blogPostService.deleteBlogPostById(newBlogPostWithId.getId()));
        when(mockBlogRepo.existsById(existingId)).thenReturn(true);
        assertDoesNotThrow(() -> blogPostService.deleteBlogPostById(existingBlogPostWithId.getId()));
    }

    @Test
    void findBlogPostByCollectibleIdTest(){
        when(mockCollectibleRepo.findById(1)).thenReturn(Optional.empty());
        assertEquals(new ArrayList<BlogPost>(), blogPostService.findBlogPostsByCollectibleId(1));

        when(mockCollectibleRepo.findById(2)).thenReturn(Optional.of(collectible1));
        when(mockBlogRepo.findBlogPostByCollectible(collectible1)).thenReturn(Arrays.asList(existingBlogPostWithId));
        assertEquals(Arrays.asList(existingBlogPostWithId), blogPostService.findBlogPostsByCollectibleId(2));
    }
}


