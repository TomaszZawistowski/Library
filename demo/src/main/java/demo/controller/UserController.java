package demo.controller;

import demo.dto.UserDataTransfer;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDataTransfer> getAllUsers(){
        return userService.findAllUsersDataTransfer();
    }

    @GetMapping("/{id}")
    public UserDataTransfer getUserById(@PathVariable Long id){
        return userService.findUserDataTransferById(id);
    }

    @PostMapping()
    public UserDataTransfer createUser(@RequestBody UserDataTransfer userDataTransfer){
        return userService.addUserDataTransfer(userDataTransfer);
    }

    @PutMapping("")
    public UserDataTransfer updateUser(@RequestBody UserDataTransfer userDataTransfer){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.updateUserDataTransfer(auth.getPrincipal().toString(), userDataTransfer);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserDataTransferById(id);
    }

    @GetMapping("/search")
    public List<UserDataTransfer> searchUser(@RequestParam String name) {
        return userService.searchByUsername(name);
    }

}
