import React, { useState } from 'react'
import { EyeIcon, PencilIcon, TrashIcon, PlusIcon } from '@heroicons/react/24/outline'
import { School } from '../types/superadmin.types'

interface SchoolsTableProps {
  schools: School[]
  isLoading: boolean
}

export const SchoolsTable: React.FC<SchoolsTableProps> = ({ schools, isLoading }) => {
  const [search, setSearch] = useState('')
  const [statusFilter, setStatusFilter] = useState<string>('all')

  const filtered = schools.filter(s =>
    s.name.toLowerCase().includes(search.toLowerCase()) &&
    (statusFilter === 'all' || s.status === statusFilter)
  )

  const statusBadge = (status: School['status']) => {
    const classes = {
      active: 'bg-green-100 text-green-800',
      inactive: 'bg-gray-100 text-gray-800',
      suspended: 'bg-red-100 text-red-800',
    }
    return <span className={`rounded-full px-2 py-1 text-xs font-medium ${classes[status]}`}>{status}</span>
  }

  if (isLoading) return <div className="flex justify-center p-8">Loading schools...</div>

  return (
    <div className="rounded-lg border border-gray-200 bg-white">
      <div className="flex flex-wrap items-center justify-between gap-4 border-b p-4">
        <h2 className="text-lg font-semibold">Schools Management</h2>
        <div className="flex gap-2">
          <input type="text" placeholder="Search..." value={search} onChange={e => setSearch(e.target.value)} className="rounded border px-3 py-1 text-sm" />
          <select value={statusFilter} onChange={e => setStatusFilter(e.target.value)} className="rounded border px-3 py-1 text-sm">
            <option value="all">All</option>
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
            <option value="suspended">Suspended</option>
          </select>
          <button className="flex items-center gap-1 rounded bg-blue-600 px-3 py-1 text-sm text-white"><PlusIcon className="h-4 w-4" /> Add</button>
        </div>
      </div>
      <div className="overflow-x-auto">
        <table className="w-full">
          <thead className="bg-gray-50">
            <tr><th className="px-6 py-3 text-left text-xs">School</th><th className="px-6 py-3 text-left text-xs">Admin</th><th className="px-6 py-3 text-left text-xs">Students</th><th className="px-6 py-3 text-left text-xs">Status</th><th className="px-6 py-3 text-right text-xs">Actions</th></tr>
          </thead>
          <tbody>
            {filtered.map(s => (
              <tr key={s.id} className="border-t">
                <td className="px-6 py-4 text-sm font-medium">{s.name}</td>
                <td className="px-6 py-4 text-sm">{s.adminName}<div className="text-xs text-gray-500">{s.adminEmail}</div></td>
                <td className="px-6 py-4 text-sm">{s.totalStudents}</td>
                <td className="px-6 py-4">{statusBadge(s.status)}</td>
                <td className="px-6 py-4 text-right"><button className="text-blue-600 mr-2"><EyeIcon className="h-4 w-4" /></button><button className="text-red-600"><TrashIcon className="h-4 w-4" /></button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
