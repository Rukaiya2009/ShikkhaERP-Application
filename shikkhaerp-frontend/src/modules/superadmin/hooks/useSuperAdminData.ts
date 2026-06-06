import { useEffect } from 'react'
import { useSuperAdminStore } from '../store/superadminStore'

export const useSuperAdminData = () => {
  const { fetchMetrics, fetchSchools, fetchRevenueData, fetchActivities, ...state } = useSuperAdminStore()

  useEffect(() => {
    fetchMetrics()
    fetchSchools()
    fetchRevenueData()
    fetchActivities()
  }, [])

  return { ...state, refetch: { fetchMetrics, fetchSchools, fetchRevenueData, fetchActivities } }
}
