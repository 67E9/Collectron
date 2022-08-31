package de.yougrowgroup.CollectronBackend.Controller;

import de.yougrowgroup.CollectronBackend.Model.PasswordPair;
import de.yougrowgroup.CollectronBackend.Model.UserProfile;
import de.yougrowgroup.CollectronBackend.Model.UserProfileWrapper;
import de.yougrowgroup.CollectronBackend.Service.UserProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserProfileManager userService;

    @GetMapping("")
    public List<UserProfile> findAllUsers(){
        return userService.loadAllUsers();
    }

    @GetMapping("/{username}")
    public UserProfile findUser(@PathVariable String username){
        try {
            return userService.loadUserProfileByUsername(username);
        } catch (UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public UserProfile newUser(@RequestBody UserProfile newUser){
        try{
            userService.createUser(new UserProfileWrapper(newUser));
            return userService.loadUserProfileByUsername(newUser.getName());
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword (@RequestBody PasswordPair passwords){
        try {
            userService.changePassword(passwords.getOldPassword(), passwords.getNewPassword());
            return new ResponseEntity<String>("password changed successfully", HttpStatus.OK);
        } catch (AccessDeniedException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/role/{username}")
    public ResponseEntity<String> changeRole (@PathVariable String username, @RequestBody String newRole){
        try {
            userService.changeRole(username, newRole);
            return new ResponseEntity<String>("The user role of " + username + " was changed to: " + newRole, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{username}")
    public UserProfile deleteUser(@PathVariable String username){
        try{
            UserProfile user = new UserProfile();
            if (userService.userExists(username)){
                 user = userService.loadUserProfileByUsername(username);
            }
            userService.deleteUser(username);

            return user;
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/login")
    public UserProfile getUserDataOnLogin(Principal user){
        if (userService.userExists(user.getName())){
            return userService.loadUserProfileByUsername(user.getName());
        } else {
            return new UserProfile(null, "", "", "");
        }
    }
}
