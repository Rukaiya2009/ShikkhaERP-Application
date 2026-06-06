import React from 'react'
import { UserGroupIcon, BuildingOfficeIcon, CreditCardIcon } from '@heroicons/react/24/outline'
import { UserActivity } from '../types/superadmin.types'

interface Props { activities: UserActivity[]; isLoading: boolean }

const getIcon = (action: string) => {
  if (action.includes('school')) return <BuildingOfficeIcon className="h-5 w-5" />
  if (action.includes('payment')) return <CreditCardIcon className="h-5 w-5" />
  return <UserGroupIcon className="h-5 w-5" />
}

export const ActivityFeed: React.FC<Props> = ({ activities, isLoading }) => {
  if (isLoading) return <div className="p-4 text-center">Loading...</div>
  return (
    <div className="rounded-lg border border-gray-200 bg-white p-4">
      <h3 className="mb-4 text-lg font-semibold">Recent Activity</h3>
      <div className="space-y-4">
        {activities.slice(0, 5).map(a => (
          <div key={a.id} className="flex gap-3">
            <div className="rounded-full bg-gray-100 p-2">{getIcon(a.action)}</div>
            <div><p className="text-sm"><span className="font-medium">{a.userName}</span> {a.action}</p><p className="text-xs text-gray-500">{a.schoolName} • {new Date(a.timestamp).toLocaleDateString()}</p></div>
          </div>
        ))}
      </div>
    </div>
  )
}
