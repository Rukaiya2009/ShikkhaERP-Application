import { create } from 'zustand'
import { PlatformMetrics, School, UserActivity, RevenueData } from '../types/superadmin.types'
import { superAdminApi } from '@/api/endpoints/superadmin.api'

interface SuperAdminState {
  metrics: PlatformMetrics | null
  schools: School[]
  activities: UserActivity[]
  revenueData: RevenueData[]
  isLoading: boolean
  error: string | null
  schoolsPage: number
  schoolsTotal: number
  schoolsHasMore: boolean

  fetchMetrics: () => Promise<void>
  fetchSchools: (reset?: boolean) => Promise<void>
  fetchActivities: () => Promise<void>
  fetchRevenueData: () => Promise<void>
  setSchoolsPage: (page: number) => void
  clearError: () => void
}

export const useSuperAdminStore = create<SuperAdminState>((set, get) => ({
  metrics: null,
  schools: [],
  activities: [],
  revenueData: [],
  isLoading: false,
  error: null,
  schoolsPage: 1,
  schoolsTotal: 0,
  schoolsHasMore: true,

  fetchMetrics: async () => {
    set({ isLoading: true, error: null })
    try {
      const res = await superAdminApi.getPlatformMetrics()
      set({ metrics: res.data, isLoading: false })
    } catch (err) {
      set({ error: 'Failed to load metrics', isLoading: false })
    }
  },

  fetchSchools: async (reset = false) => {
    const page = reset ? 1 : get().schoolsPage
    set({ isLoading: true })
    try {
      const res = await superAdminApi.getSchools({ page, limit: 20 })
      set((state) => ({
        schools: reset ? res.data.schools : [...state.schools, ...res.data.schools],
        schoolsTotal: res.data.total,
        schoolsHasMore: res.data.schools.length === 20,
        schoolsPage: page + 1,
        isLoading: false,
      }))
    } catch {
      set({ error: 'Failed to load schools', isLoading: false })
    }
  },

  fetchActivities: async () => {
    set({ isLoading: true })
    try {
      const res = await superAdminApi.getUserActivity({ days: 30 })
      set({ activities: res.data.activities, isLoading: false })
    } catch {
      set({ error: 'Failed to load activities', isLoading: false })
    }
  },

  fetchRevenueData: async () => {
    set({ isLoading: true })
    try {
      const res = await superAdminApi.getRevenueData()
      set({ revenueData: res.data, isLoading: false })
    } catch {
      set({ error: 'Failed to load revenue data', isLoading: false })
    }
  },

  setSchoolsPage: (page: number) => set({ schoolsPage: page }),
  clearError: () => set({ error: null }),
}))
