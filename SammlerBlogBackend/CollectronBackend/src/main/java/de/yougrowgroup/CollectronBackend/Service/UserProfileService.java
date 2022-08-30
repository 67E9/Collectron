package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.UserProfile;
import de.yougrowgroup.CollectronBackend.Entity.UserProfileWrapper;
import de.yougrowgroup.CollectronBackend.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements UserDetailsService {

    @Autowired
    private UserProfileRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserProfileWrapper(repo.findByName(username).get(0));
    }
}
