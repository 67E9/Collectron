package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.BlogPost;
import de.yougrowgroup.CollectronBackend.Entity.Collectible;
import de.yougrowgroup.CollectronBackend.Repository.BlogPostRepository;
import de.yougrowgroup.CollectronBackend.Repository.CollectibleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private final CollectibleRepository collectibleRepository;


    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, CollectibleRepository collectibleRepository) {
        this.blogPostRepository = blogPostRepository;
        this.collectibleRepository = collectibleRepository;
    }

    @Override
    public BlogPost findBlogPostById(Integer id) {
        return blogPostRepository.findById(id).orElse(new BlogPost());
    }

    @Override
    public List<BlogPost> findAllBlogPosts() {
        List<BlogPost> result = new ArrayList<>();
        blogPostRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<BlogPost> findBlogPostsByTitleContainsIgnoreCase(String keyword) {
        return blogPostRepository.findBlogPostByTitleContainsIgnoreCase(keyword);
    }

    public List<BlogPost> findBlogPostsByArticleContainsIgnoreCase(String keyword) {
        return blogPostRepository.findBlogPostByArticleContainsIgnoreCase(keyword);
    }

    @Override
    public void newBlogPost(BlogPost newPost) {
        if ( newPost.getId() == null  || !blogPostRepository.existsById(newPost.getId())) {
            blogPostRepository.save(newPost);
        } else {
            throw new IllegalArgumentException("Cannot create. BlogPost with same BlogPost id already exists.");
        }
    }

    @Override
    public List<BlogPost> findBlogPostsByCollectibleId(Integer collectibleId) {
        Collectible collectible = collectibleRepository.findById(collectibleId).orElse(null);
        if (collectible != null) {
            return blogPostRepository.findBlogPostByCollectible(collectible);
        }
        return new ArrayList<BlogPost>();
    }

    @Override
    public void updateBlogPost(BlogPost updatedPost) {
        if (updatedPost.getId() != null && blogPostRepository.existsById(updatedPost.getId())) {
            blogPostRepository.save(updatedPost);
        } else {
            throw new IllegalArgumentException("Cannot update. BlogPost with this id does not exist");
        }
    }

    @Override
    public void deleteBlogPostById(Integer id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cannot delete. BlogPost with this id does not exist");
        }
    }

}

