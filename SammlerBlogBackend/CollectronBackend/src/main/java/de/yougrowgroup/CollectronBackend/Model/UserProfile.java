package de.yougrowgroup.CollectronBackend.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true) //TODO: handle persistance error on non unique name
    private String name;

    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
