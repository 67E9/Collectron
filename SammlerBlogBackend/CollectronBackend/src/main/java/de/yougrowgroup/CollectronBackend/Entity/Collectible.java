package de.yougrowgroup.CollectronBackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
public class Collectible {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 255)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @PositiveOrZero
    private BigDecimal estimatedValue;
    private Boolean forSale = false;
    @URL
    private String ImageUrl;

    @OneToMany (mappedBy = "collectible")
    private List<BlogPost> blogPosts;

    @ManyToOne
    @JoinColumn(name="type_id")
    @JsonIgnoreProperties("collectibles")
    private Type type;

    //TODO: Feld condition ergänzen

    //constructors:
    public Collectible() {
    }

    public Collectible(Integer id, String name, String description, BigDecimal estimatedValue, Boolean forSale, String ImageUrl, List<BlogPost> blogPosts, Type type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.estimatedValue = estimatedValue;
        this.forSale = forSale;
        this.ImageUrl = ImageUrl;
        this.blogPosts = blogPosts;
        this.type = type;
    }

    public Collectible(Integer id) {
        this.id = id;
    }

    public Collectible(String name){
        this.name =name;
    }

    @Override
    public String toString() {
        return "Collectible{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedValue=" + estimatedValue +
                ", forSale=" + forSale +
                ", imageURL='" + ImageUrl + '\'' +
                ", blogPosts=" + blogPosts +
                ", type=" + type +
                '}';
    }
}
