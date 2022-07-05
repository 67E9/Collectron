package de.yougrowgroup.CollectronBackend.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Type {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 255)
    private String name;
    @Column(columnDefinition = "TEXT")
    @Size(max = 512)
    private String description;

    @OneToMany (mappedBy = "type")
    private List<Collectible> collectibles;


    public Type(Integer id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", collectibles=" + collectibles +
                '}';
    }
}
