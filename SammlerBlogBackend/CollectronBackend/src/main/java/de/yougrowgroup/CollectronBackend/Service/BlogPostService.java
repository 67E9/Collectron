package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.BlogPost;

import java.util.List;

public interface BlogPostService {
    //CRUD METHODS:
    void newBlogPost (BlogPost blogPost);

    BlogPost findBlogPostById(Integer id);
    List<BlogPost> findAllBlogPosts();
    List<BlogPost> findBlogPostsByTitleContainsIgnoreCase(String keyword);
    List<BlogPost> findBlogPostsByArticleContainsIgnoreCase(String keyword);
    List<BlogPost> findBlogPostsByCollectibleId (Integer collectibleId);

    void updateBlogPost (BlogPost updatedPost);

    void deleteBlogPostById (Integer id);


}
