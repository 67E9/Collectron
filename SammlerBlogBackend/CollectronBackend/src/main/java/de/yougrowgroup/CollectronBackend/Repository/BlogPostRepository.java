package de.yougrowgroup.CollectronBackend.Repository;

import de.yougrowgroup.CollectronBackend.Model.BlogPost;
import de.yougrowgroup.CollectronBackend.Model.Collectible;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {

    List<BlogPost> findBlogPostByTitleContainsIgnoreCase(String keyword);
    List<BlogPost> findBlogPostByArticleContainsIgnoreCase(String keyword);
    List<BlogPost> findBlogPostByCollectible(Collectible collectible);
}
