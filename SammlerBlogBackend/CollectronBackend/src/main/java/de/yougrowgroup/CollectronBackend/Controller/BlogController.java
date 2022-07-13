package de.yougrowgroup.CollectronBackend.Controller;

import de.yougrowgroup.CollectronBackend.Entity.BlogPost;
import de.yougrowgroup.CollectronBackend.Service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/blogPost")
public class BlogController {
    private final BlogPostService blogPostService;

    public BlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/{id}")
    public BlogPost findBlogPostById(@PathVariable Integer id) {
        return blogPostService.findBlogPostById(id);
    }

    @GetMapping("")
    public List<BlogPost> findAllBlogPosts() {
        return blogPostService.findAllBlogPosts();
    }

    public List<BlogPost> findBlogPostByTitleContainsIgnoreCase(String title){
        return blogPostService.findBlogPostsByTitleContainsIgnoreCase(title);
    }

    public List<BlogPost> findBlogPostByArticleContainsIgnoreCase(String article){
        return blogPostService.findBlogPostsByArticleContainsIgnoreCase(article);
    }

    @GetMapping("/find/{keyword}")
    public List<BlogPost> findBlogPostByTitleAndArticle(@PathVariable String keyword){
        List<BlogPost> byTitle = findBlogPostByTitleContainsIgnoreCase(keyword);
        List<BlogPost> byArticle = findBlogPostByArticleContainsIgnoreCase(keyword);
        byArticle.stream().
                filter(post -> !byTitle.contains(post))
                .forEach(byTitle::add);
        return byTitle;
    }

    @GetMapping("/collectible/{collectibleId}")
    public List<BlogPost> findBlogPostByCollectibleId(@PathVariable Integer collectibleId){
        return blogPostService.findBlogPostsByCollectibleId(collectibleId);
    }

    @PostMapping("")
    public BlogPost newBlogPost(@RequestBody @Valid BlogPost newPost) {
        try {
            newPost.setTimeStamp(LocalDateTime.now());
            blogPostService.newBlogPost(newPost);
            return newPost;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blogPost with this id already exists");
        }
    }

    @PutMapping("")
    public BlogPost updateBlogPost(@RequestBody @Valid BlogPost updatedPost) {
        try {
            blogPostService.updateBlogPost(updatedPost);
            return updatedPost;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blogPost with this id does not exist");
        }
    }

    @DeleteMapping("/{id}")
    public BlogPost deleteBlogPostByID(@PathVariable Integer id){
        try {
            BlogPost deletedPost = blogPostService.findBlogPostById(id);
            blogPostService.deleteBlogPostById(id);
            return deletedPost;
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "blogPost with this id does not exist");
        }
    }
}
