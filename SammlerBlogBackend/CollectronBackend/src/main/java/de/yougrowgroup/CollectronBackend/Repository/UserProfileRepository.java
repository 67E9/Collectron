package de.yougrowgroup.CollectronBackend.Repository;

import de.yougrowgroup.CollectronBackend.Model.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
    public List<UserProfile> findByName(String username);
    public Boolean existsByName(String username);

    public Integer deleteByName(String username);
}