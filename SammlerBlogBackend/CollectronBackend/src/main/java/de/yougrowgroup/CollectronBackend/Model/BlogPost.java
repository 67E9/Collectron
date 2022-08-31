package de.yougrowgroup.CollectronBackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BlogPost {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty (message = "BlogPost: title is mandatory")
    @Size(max = 255)
    private String title;
    @Column(columnDefinition = "TEXT")
    @NotEmpty (message = "BlogPost: article is mandatory")
    private String article;
    private LocalDateTime timeStamp;

    @ManyToOne
    @JsonIgnoreProperties("blogPosts")
    @JoinColumn(name= "collectible_id")
    private Collectible collectible;

    public BlogPost() {
    }

    public BlogPost(Integer id) {
        this.id = id;
    }

    public BlogPost(Integer id, String title, String article, LocalDateTime timeStamp, Collectible collectible) {
        this.id = id;
        this.title = title;
        this.article = article;
        this.timeStamp = timeStamp;
        this.collectible = collectible;
    }

    public BlogPost(String title, String article, LocalDateTime timeStamp, Collectible collectible) {
        this.title = title;
        this.article = article;
        this.timeStamp = timeStamp;
        this.collectible = collectible;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", timeStamp=" + timeStamp +
                ", collectible=" + collectible +
                '}';
    }
}
