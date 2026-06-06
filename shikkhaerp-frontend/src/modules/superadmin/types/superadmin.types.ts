export interface PlatformMetrics {
  totalSchools: number
  activeSchools: number
  totalUsers: number
  totalStudents: number
  totalTeachers: number
  totalRevenue: number
  monthlyGrowth: number
}

export interface School {
  id: string
  name: string
  tenantId: string
  status: 'active' | 'inactive' | 'suspended'
  adminName: string
  adminEmail: string
  totalStudents: number
  totalTeachers: number
  subscription: 'basic' | 'premium' | 'enterprise'
  joinDate: string
  lastActive: string
}

export interface UserActivity {
  id: string
  userId: string
  userName: string
  userRole: string
  schoolId: string
  schoolName: string
  action: string
  timestamp: string
  ipAddress: string
}

export interface RevenueData {
  month: string
  revenue: number
  subscriptions: number
  oneTimePayments: number
}
