package de.yougrowgroup.CollectronBackend.Service;

import de.yougrowgroup.CollectronBackend.Model.UserProfile;
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

import java.util.ArrayList;
import java.util.List;

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
            userProfileRepository.save(new UserProfile(null, user.getUsername(), role, pw));
        } else {
            throw new IllegalArgumentException("Cannot save new user. User with the name " + user.getUsername() + " already exists");
        }

    }

    @Override
    @Deprecated
    public void updateUser(UserDetails user) {
     //not in use
        System.out.println("updateUser(), which is predefined in the UserDetailsManager Interface is currently not in use " +
                "here. Use the methods changeRole() and changePassword() instead.");
    }

    @Override
    public void deleteUser(String username) {
        if (userExists(username)){
            if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(username)) {
                Integer userId = userProfileRepository.findByName(username).get(0).getId();
                userProfileRepository.deleteById(userId);
            } else {
                throw new IllegalArgumentException("You cannot delete your own account. Use different admin account to do so.");
            }
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
                UserProfile dbUser = userProfileRepository.findByName(username).get(0);
                String hash = dbUser.getPassword();
                if (passwordEncoder.matches(oldPassword, hash)){
                    dbUser.setPassword(passwordEncoder.encode(newPassword));
                    userProfileRepository.save(dbUser);
                    return;
                }
                throw new AccessDeniedException("Can't change password as the old password does not match the password in the database");
            }
            throw new AccessDeniedException("Can't change password as the user " + username + " does not exist in the database.");
        }

    }

    public void changeRole(String username, String newRole) {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(username)) {
            if (newRole.equals("ROLE_ADMIN")||newRole.equals("ROLE_VIEWER")) {
                UserProfile user = userProfileRepository.findByName(username).get(0);
                user.setRole(newRole);
                userProfileRepository.save(user);
            } else {
                throw new IllegalArgumentException(newRole + " is not a recognized role in this application.");
            }
        } else {
            throw new IllegalArgumentException("You cannot change the role of your own account. Use a different ADMIN-account.");
        }
    }

    @Override
    public boolean userExists(String username) {
        return userProfileRepository.existsByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileService.loadUserByUsername(username);
    }

    public UserProfile loadUserProfileByUsername(String username) throws UsernameNotFoundException{
        if (userProfileRepository.existsByName(username)) {
            return userProfileRepository.findByName(username).get(0);
        } else {
            throw new UsernameNotFoundException("No user with name " + username +  "found!");
        }
    }

    public List<UserProfile> loadAllUsers() {
        List<UserProfile> result = new ArrayList<UserProfile>();
        userProfileRepository.findAll().forEach(result::add);
        return result;
    }
}
