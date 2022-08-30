package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Entity.UserProfile;
import de.yougrowgroup.CollectronBackend.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UserProfileManager implements UserDetailsManager {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user) {
        if (!userExists(user.getUsername())){
            String pw = passwordEncoder.encode(user.getPassword());
            String role = user.getAuthorities().toArray()[0].toString();
            userProfileRepository.save(new UserProfile(null, user.getUsername(), pw, role));
        } else {
            throw new IllegalArgumentException("Cannot save new user. User with the name " + user.getUsername() + " already exists");
        }

    }

    @Override
    public void updateUser(UserDetails user) {
     //not in use
    }

    @Override
    public void deleteUser(String username) {
        if (userExists(username) && !SecurityContextHolder.getContext().getAuthentication().getName().equals(username)){
            userProfileRepository.deleteByName(username);
        } else {
            throw new IllegalArgumentException("Cannot delete. User with the name " + username + " does not exist.");
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
        } else {
            String username = currentUser.getName();
            if (userExists(username)){
                if (passwordEncoder.matches(oldPassword, currentUser.getCredentials().toString())){
                    UserProfile dbUser = userProfileRepository.findByName(username).get(0);
                    dbUser.setPassword(passwordEncoder.encode(newPassword));
                }
                throw new AccessDeniedException("Can't change password as the old password does not match the password in the database");
            }
            throw new AccessDeniedException("Can't change password as the user " + username + " does not exist in the database.");
        }

    }

    public void changeRole(String username, String newRole) {
        UserProfile user = userProfileRepository.findByName(username).get(0);
        user.setRole(newRole); //TODO add validation for correct roles
        userProfileRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return userProfileRepository.existsByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileService.loadUserByUsername(username);
    }
}
