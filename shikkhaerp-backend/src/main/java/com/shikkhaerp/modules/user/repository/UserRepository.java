package com.shikkhaerp.modules.user.repository;

import com.shikkhaerp.modules.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByEmailAndEnabledTrue(String email);
    
    boolean existsByEmail(String email);
    
    List<User> findByRole(User.UserRole role);
    
    Page<User> findByRole(User.UserRole role, Pageable pageable);
    
    List<User> findByStatus(User.UserStatus status);
    
    Page<User> findByStatus(User.UserStatus status, Pageable pageable);
    
    Page<User> findByRoleAndStatus(User.UserRole role, User.UserStatus status, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchByName(@Param("keyword") String keyword);
    
    long countByRole(User.UserRole role);
    
    long countByStatus(User.UserStatus status);
}
