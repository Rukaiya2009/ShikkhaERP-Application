import { superAdminApi } from '@/api/endpoints/superadmin.api'
import { PlatformMetrics, School, RevenueData } from '../types/superadmin.types'

export const superAdminService = {
  async getDashboardSummary(): Promise<PlatformMetrics | null> {
    try {
      const { data } = await superAdminApi.getPlatformMetrics()
      return data
    } catch {
      return null
    }
  },
  async exportSchoolsReport(): Promise<Blob> {
    const response = await superAdminApi.getSchools({ limit: 1000 })
    const blob = new Blob([JSON.stringify(response.data.schools, null, 2)], { type: 'application/json' })
    return blob
  },
}
