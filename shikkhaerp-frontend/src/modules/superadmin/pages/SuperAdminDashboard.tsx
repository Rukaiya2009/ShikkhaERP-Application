import React, { useState } from 'react'
import { BuildingOfficeIcon, UsersIcon, AcademicCapIcon, CurrencyDollarIcon } from '@heroicons/react/24/outline'
import { KPICard } from '../components/KPICard'
import { SchoolsTable } from '../components/SchoolsTable'
import { RevenueChart } from '../components/RevenueChart'
import { ActivityFeed } from '../components/ActivityFeed'
import { useSuperAdminStore } from '../store/superadminStore'
import { LoadingSpinner } from '@/shared/components/ui/LoadingSpinner'

export const SuperAdminDashboard: React.FC = () => {
  const { metrics, schools, revenueData, activities, isLoading, error, fetchMetrics, fetchSchools, fetchRevenueData, fetchActivities } = useSuperAdminStore()
  const [timeRange, setTimeRange] = useState<'30d'>('30d')

  React.useEffect(() => {
    fetchMetrics()
    fetchSchools()
    fetchRevenueData()
    fetchActivities()
  }, [])

  if (isLoading && !metrics) return <div className="flex justify-center p-12"><LoadingSpinner /></div>
  if (error) return <div className="text-red-600 p-8">Error: {error}</div>

  const kpis = [
    { title: 'Total Schools', value: metrics?.totalSchools?.toLocaleString() || '0', icon: <BuildingOfficeIcon className="h-6 w-6" />, trend: { value: metrics?.monthlyGrowth || 0, isPositive: true, label: 'vs last month' }, color: 'blue' as const },
    { title: 'Total Users', value: metrics?.totalUsers?.toLocaleString() || '0', icon: <UsersIcon className="h-6 w-6" />, color: 'green' as const },
    { title: 'Total Students', value: metrics?.totalStudents?.toLocaleString() || '0', icon: <AcademicCapIcon className="h-6 w-6" />, color: 'purple' as const },
    { title: 'Total Revenue', value: `$${metrics?.totalRevenue?.toLocaleString() || '0'}`, icon: <CurrencyDollarIcon className="h-6 w-6" />, color: 'orange' as const },
  ]

  return (
    <div className="space-y-6">
      <div><h1 className="text-2xl font-bold">Super Admin Dashboard</h1><p className="text-sm text-gray-500">Platform-wide analytics and management</p></div>
      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-4">{kpis.map((k, i) => <KPICard key={i} {...k} />)}</div>
      <div className="grid grid-cols-1 gap-6 lg:grid-cols-3">
        <div className="lg:col-span-2"><RevenueChart data={revenueData} timeRange={timeRange} onTimeRangeChange={setTimeRange} /></div>
        <div><ActivityFeed activities={activities} isLoading={isLoading} /></div>
      </div>
      <SchoolsTable schools={schools} isLoading={isLoading} />
    </div>
  )
}
