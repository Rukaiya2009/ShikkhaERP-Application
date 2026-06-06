import { apiClient } from '../client'
import type { PlatformMetrics, School, UserActivity, RevenueData } from '@/modules/superadmin/types/superadmin.types'

export const superAdminApi = {
  getPlatformMetrics: () => apiClient.get<PlatformMetrics>('/v1/superadmin/metrics'),
  getRevenueData: (months: number = 12) => apiClient.get<RevenueData[]>(`/v1/superadmin/revenue?months=${months}`),
  getSchools: (params?: { status?: string; page?: number; limit?: number }) =>
    apiClient.get<{ schools: School[]; total: number }>('/v1/superadmin/schools', { params }),
  createSchool: (data: Partial<School>) => apiClient.post<School>('/v1/superadmin/schools', data),
  updateSchool: (id: string, data: Partial<School>) => apiClient.put<School>(`/v1/superadmin/schools/${id}`, data),
  deleteSchool: (id: string) => apiClient.delete(`/v1/superadmin/schools/${id}`),
  suspendSchool: (id: string) => apiClient.post(`/v1/superadmin/schools/${id}/suspend`),
  activateSchool: (id: string) => apiClient.post(`/v1/superadmin/schools/${id}/activate`),
  getUserActivity: (params?: { schoolId?: string; days?: number; page?: number }) =>
    apiClient.get<{ activities: UserActivity[]; total: number }>('/v1/superadmin/activities', { params }),
}
