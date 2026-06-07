package com.shikkhaerp.dashboard.api;

import com.shikkhaerp.core.dto.ApiResponse;
import com.shikkhaerp.dashboard.dto.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/v1/dashboard/admin")
public class AdminDashboardController {

    @GetMapping("/summary")
    public ApiResponse<Map<String, Object>> getSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalStudents", 1250);
        summary.put("totalTeachers", 85);
        summary.put("totalParents", 980);
        summary.put("totalClasses", 10);
        summary.put("totalUsers", 2315);
        summary.put("totalRevenue", 1250000.0);
        summary.put("monthlyRevenue", 125000.0);
        summary.put("pendingFees", 45);
        summary.put("todayAttendance", 1180);
        summary.put("attendancePercentage", 94.5);
        return ApiResponse.success(summary);
    }

    @GetMapping("/recent-activities")
    public ApiResponse<List<Map<String, Object>>> getRecentActivities() {
        List<Map<String, Object>> activities = new ArrayList<>();
        
        Map<String, Object> activity1 = new HashMap<>();
        activity1.put("action", "USER_REGISTERED");
        activity1.put("user", "John Doe");
        activity1.put("userRole", "TEACHER");
        activity1.put("details", "New teacher registered");
        activity1.put("timestamp", LocalDateTime.now().minusHours(2));
        activities.add(activity1);
        
        Map<String, Object> activity2 = new HashMap<>();
        activity2.put("action", "STUDENT_ADDED");
        activity2.put("user", "Admin");
        activity2.put("userRole", "ADMIN");
        activity2.put("details", "New student added to Class 10");
        activity2.put("timestamp", LocalDateTime.now().minusHours(5));
        activities.add(activity2);
        
        return ApiResponse.success(activities);
    }

    @GetMapping("/enrollment-trend")
    public ApiResponse<Map<String, Object>> getEnrollmentTrend() {
        Map<String, Object> data = new HashMap<>();
        data.put("labels", Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun"));
        data.put("datasets", Arrays.asList(65, 78, 82, 95, 108, 125));
        return ApiResponse.success(data);
    }

    @GetMapping("/revenue-trend")
    public ApiResponse<Map<String, Object>> getRevenueTrend() {
        Map<String, Object> data = new HashMap<>();
        data.put("labels", Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun"));
        data.put("datasets", Arrays.asList(85000, 92000, 105000, 118000, 125000, 135000));
        return ApiResponse.success(data);
    }

    @GetMapping("/class-distribution")
    public ApiResponse<List<Map<String, Object>>> getClassDistribution() {
        List<Map<String, Object>> distribution = new ArrayList<>();
        
        String[] classes = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", 
                            "Class 6", "Class 7", "Class 8", "Class 9", "Class 10"};
        int[] counts = {125, 130, 128, 132, 127, 135, 129, 131, 126, 124};
        
        for (int i = 0; i < classes.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("className", classes[i]);
            item.put("studentCount", counts[i]);
            distribution.add(item);
        }
        
        return ApiResponse.success(distribution);
    }

    @GetMapping("/gender-ratio")
    public ApiResponse<Map<String, Object>> getGenderRatio() {
        Map<String, Object> ratio = new HashMap<>();
        ratio.put("male", 680);
        ratio.put("female", 570);
        ratio.put("malePercentage", 54.4);
        ratio.put("femalePercentage", 45.6);
        return ApiResponse.success(ratio);
    }

    @GetMapping("/recent-users")
    public ApiResponse<List<Map<String, Object>>> getRecentUsers(@RequestParam(defaultValue = "5") int limit) {
        List<Map<String, Object>> users = new ArrayList<>();
        
        for (int i = 1; i <= limit; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", "user_" + i);
            user.put("name", "User " + i);
            user.put("email", "user" + i + "@example.com");
            user.put("role", i % 2 == 0 ? "TEACHER" : "STUDENT");
            user.put("status", "ACTIVE");
            user.put("createdAt", LocalDateTime.now().minusDays(i));
            users.add(user);
        }
        
        return ApiResponse.success(users);
    }

    @GetMapping("/health")
    public ApiResponse<Map<String, Object>> getSystemHealth() {
        Map<String, Object> health = new HashMap<>();
        health.put("databaseStatus", "UP");
        health.put("serverStatus", "UP");
        health.put("uptime", System.currentTimeMillis());
        health.put("cpuUsage", 25.5);
        health.put("memoryUsage", 45.2);
        health.put("activeSessions", 12);
        return ApiResponse.success(health);
    }
}
