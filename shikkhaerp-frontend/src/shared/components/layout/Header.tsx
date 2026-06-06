import React from 'react'
import { BellIcon, UserCircleIcon } from '@heroicons/react/24/outline'

export const Header: React.FC = () => {
  return (
    <header className="flex h-16 items-center justify-between border-b border-gray-200 bg-white px-6">
      <div className="text-lg font-semibold text-gray-800">Super Admin Dashboard</div>
      <div className="flex items-center gap-4">
        <BellIcon className="h-6 w-6 text-gray-500 hover:text-gray-700" />
        <UserCircleIcon className="h-8 w-8 text-gray-500 hover:text-gray-700" />
      </div>
    </header>
  )
}
