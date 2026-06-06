import React from 'react'
import { ArrowUpIcon, ArrowDownIcon } from '@heroicons/react/24/solid'

interface KPICardProps {
  title: string
  value: string | number
  icon: React.ReactNode
  trend?: { value: number; isPositive: boolean; label: string }
  color?: 'blue' | 'green' | 'purple' | 'orange' | 'red'
}

const colorClasses = {
  blue: 'bg-blue-50 dark:bg-blue-950/30 text-blue-600 dark:text-blue-400',
  green: 'bg-green-50 dark:bg-green-950/30 text-green-600 dark:text-green-400',
  purple: 'bg-purple-50 dark:bg-purple-950/30 text-purple-600 dark:text-purple-400',
  orange: 'bg-orange-50 dark:bg-orange-950/30 text-orange-600 dark:text-orange-400',
  red: 'bg-red-50 dark:bg-red-950/30 text-red-600 dark:text-red-400',
}

export const KPICard: React.FC<KPICardProps> = ({ title, value, icon, trend, color = 'blue' }) => {
  return (
    <div className={`rounded-xl p-6 ${colorClasses[color].split(' ')[0]} transition-all hover:scale-[1.02]`}>
      <div className="flex items-center justify-between">
        <div>
          <p className="text-sm font-medium text-gray-500">{title}</p>
          <p className="mt-2 text-3xl font-bold text-gray-900">{value}</p>
        </div>
        <div className={`rounded-full p-3 ${colorClasses[color]}`}>{icon}</div>
      </div>
      {trend && (
        <div className="mt-4 flex items-center gap-2">
          <span className={`flex items-center text-sm font-medium ${trend.isPositive ? 'text-green-600' : 'text-red-600'}`}>
            {trend.isPositive ? <ArrowUpIcon className="mr-1 h-4 w-4" /> : <ArrowDownIcon className="mr-1 h-4 w-4" />}
            {Math.abs(trend.value)}%
          </span>
          <span className="text-sm text-gray-500">{trend.label}</span>
        </div>
      )}
    </div>
  )
}
