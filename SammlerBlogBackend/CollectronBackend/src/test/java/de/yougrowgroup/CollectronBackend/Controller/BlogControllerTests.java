package de.yougrowgroup.CollectronBackend.Controller;
import de.yougrowgroup.CollectronBackend.Entity.BlogPost;
import de.yougrowgroup.CollectronBackend.Service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogControllerTests {

    BlogController controller;
    @Mock
    BlogPostService mockBlogPostService;

    @BeforeEach
    void init(){
       controller = new BlogController(mockBlogPostService);
    }

    @Test
    void findBlogPostByTitleAndArticleTest(){
        BlogPost blogPost1 = new BlogPost(1);
        BlogPost blogPost2 = new BlogPost(2);
        BlogPost blogPost3 = new BlogPost(3);
        List<BlogPost> list1 = new ArrayList<>(List.of(blogPost1, blogPost2));
        List<BlogPost> list2 = new ArrayList<>(List.of(blogPost3, blogPost2));
        List<BlogPost> result = new ArrayList<>(List.of(blogPost1, blogPost2, blogPost3));

        when(mockBlogPostService.findBlogPostsByTitleContainsIgnoreCase(any())).thenReturn(list1);
        when(mockBlogPostService.findBlogPostsArticleContainsIgnoreCase(any())).thenReturn(list2);

        assertEquals(result, controller.findBlogPostByTitleAndArticle(any()));
    }


}
