import React from 'react'
import { NavLink } from 'react-router-dom'
import { HomeIcon, BuildingOfficeIcon, UserGroupIcon, ChartBarIcon } from '@heroicons/react/24/outline'

const navigation = [
  { name: 'Dashboard', href: '/', icon: HomeIcon },
  { name: 'Schools', href: '/schools', icon: BuildingOfficeIcon },
  { name: 'Users', href: '/users', icon: UserGroupIcon },
  { name: 'Reports', href: '/reports', icon: ChartBarIcon },
]

export const Sidebar: React.FC = () => {
  return (
    <aside className="w-64 bg-gray-900 text-white">
      <div className="p-4 text-xl font-bold">ShikkhaERP</div>
      <nav className="mt-8">
        {navigation.map((item) => (
          <NavLink
            key={item.name}
            to={item.href}
            className={({ isActive }) =>
              `flex items-center gap-3 px-4 py-3 text-sm transition-colors ${
                isActive ? 'bg-gray-800 text-white' : 'text-gray-300 hover:bg-gray-800 hover:text-white'
              }`
            }
          >
            <item.icon className="h-5 w-5" />
            {item.name}
          </NavLink>
        ))}
      </nav>
    </aside>
  )
}
