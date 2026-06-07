package com.shikkhaerp.dashboard.service;

import com.shikkhaerp.dashboard.dto.*;
import com.shikkhaerp.modules.student.repository.StudentRepository;
import com.shikkhaerp.modules.teacher.repository.TeacherRepository;
import com.shikkhaerp.modules.user.entity.User;
import com.shikkhaerp.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public DashboardSummaryDto getSummary() {
        long totalStudents = studentRepository.count();
        long totalTeachers = teacherRepository.count();
        long totalUsers = userRepository.count();
        
        return DashboardSummaryDto.builder()
                .totalStudents(totalStudents)
                .totalTeachers(totalTeachers)
                .totalParents(0L)
                .totalClasses(0L)
                .totalUsers(totalUsers)
                .totalRevenue(0.0)
                .monthlyRevenue(0.0)
                .pendingFees(0L)
                .todayAttendance(0L)
                .attendancePercentage(0.0)
                .build();
    }

    public List<RecentActivityDto> getRecentActivities() {
        List<RecentActivityDto> activities = new ArrayList<>();
        
        userRepository.findAll().stream()
                .limit(10)
                .forEach(user -> {
                    RecentActivityDto activity = RecentActivityDto.builder()
                            .id(user.getId())
                            .action("USER_REGISTERED")
                            .user(user.getName())
                            .userRole(user.getRole().name())
                            .details("New " + user.getRole().name() + " registered")
                            .timestamp(user.getCreatedAt())
                            .build();
                    activities.add(activity);
                });
        
        return activities;
    }

    public ChartDataDto getEnrollmentTrend() {
        List<String> labels = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun");
        List<Long> datasets = new ArrayList<>();
        
        Random random = new Random();
        for (int i = 0; i < labels.size(); i++) {
            datasets.add((long) (random.nextInt(100) + 50));
        }
        
        return ChartDataDto.builder()
                .labels(labels)
                .datasets(datasets)
                .build();
    }

    public ChartDataDto getRevenueTrend() {
        List<String> labels = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun");
        List<Long> datasets = new ArrayList<>();
        
        Random random = new Random();
        for (int i = 0; i < labels.size(); i++) {
            datasets.add((long) (random.nextInt(50000) + 10000));
        }
        
        return ChartDataDto.builder()
                .labels(labels)
                .datasets(datasets)
                .build();
    }

    public List<ClassDistributionDto> getClassDistribution() {
        List<ClassDistributionDto> distribution = new ArrayList<>();
        
        String[] classes = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", 
                            "Class 6", "Class 7", "Class 8", "Class 9", "Class 10"};
        long[] counts = {25, 30, 28, 32, 27, 35, 29, 31, 26, 24};
        
        for (int i = 0; i < classes.length; i++) {
            distribution.add(ClassDistributionDto.builder()
                    .className(classes[i])
                    .studentCount(counts[i])
                    .build());
        }
        
        return distribution;
    }

    public GenderRatioDto getGenderRatio() {
        return GenderRatioDto.builder()
                .male(150L)
                .female(120L)
                .other(5L)
                .malePercentage(54.5)
                .femalePercentage(43.6)
                .build();
    }

    public List<UserDto> getRecentUsers(int limit) {
        List<UserDto> recentUsers = new ArrayList<>();
        
        userRepository.findAll().stream()
                .limit(limit)
                .forEach(user -> {
                    UserDto dto = UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .role(user.getRole().name())
                            .status(user.isEnabled() ? "ACTIVE" : "INACTIVE")
                            .createdAt(user.getCreatedAt())
                            .build();
                    recentUsers.add(dto);
                });
        
        return recentUsers;
    }

    public SystemHealthDto getSystemHealth() {
        return SystemHealthDto.builder()
                .databaseStatus("UP")
                .serverStatus("UP")
                .uptime(System.currentTimeMillis())
                .cpuUsage(25.5)
                .memoryUsage(45.2)
                .activeSessions(12)
                .build();
    }
}