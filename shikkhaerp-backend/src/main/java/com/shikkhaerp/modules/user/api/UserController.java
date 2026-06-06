package com.shikkhaerp.modules.user.api;

import com.shikkhaerp.core.dto.ApiResponse;
import com.shikkhaerp.modules.user.entity.User;
import com.shikkhaerp.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    public ApiResponse<User> createUser(@Valid @RequestBody User user) {
        return ApiResponse.success(userService.createUser(user), "User created successfully!");
    }
    
    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.success(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable String id) {
        return ApiResponse.success(userService.getUserById(id));
    }
    
    @GetMapping("/email/{email}")
    public ApiResponse<User> getUserByEmail(@PathVariable String email) {
        return ApiResponse.success(userService.getUserByEmail(email));
    }
    
    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return ApiResponse.success(userService.updateUser(id, user), "User updated successfully!");
    }
    
    @PutMapping("/{id}/enable")
    public ApiResponse<Void> enableUser(@PathVariable String id) {
        userService.enableUser(id);
        return ApiResponse.success(null, "User enabled successfully!");
    }
    
    @PutMapping("/{id}/disable")
    public ApiResponse<Void> disableUser(@PathVariable String id) {
        userService.disableUser(id);
        return ApiResponse.success(null, "User disabled successfully!");
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.success(null, "User deleted successfully!");
    }
    
    @GetMapping("/role/{role}")
    public ApiResponse<List<User>> getUsersByRole(@PathVariable String role) {
        return ApiResponse.success(userService.getUsersByRole(User.UserRole.valueOf(role)));
    }
    
    @GetMapping("/count")
    public ApiResponse<Long> getUserCount() {
        return ApiResponse.success(userService.getUserCount());
    }
}