import React from 'react'
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Legend } from 'recharts'
import { RevenueData } from '../types/superadmin.types'

interface Props { data: RevenueData[]; timeRange: string; onTimeRangeChange: (range: any) => void }

export const RevenueChart: React.FC<Props> = ({ data }) => {
  return (
    <div className="rounded-lg border border-gray-200 bg-white p-4">
      <h3 className="mb-4 text-lg font-semibold">Revenue Overview</h3>
      <ResponsiveContainer width="100%" height={320}>
        <AreaChart data={data}>
          <defs><linearGradient id="rev" x1="0" y1="0" x2="0" y2="1"><stop offset="5%" stopColor="#3B82F6" stopOpacity={0.3}/><stop offset="95%" stopColor="#3B82F6" stopOpacity={0}/></linearGradient></defs>
          <CartesianGrid strokeDasharray="3 3"/>
          <XAxis dataKey="month"/>
          <YAxis tickFormatter={(v) => `$${v/1000}k`}/>
          <Tooltip/>
          <Legend/>
          <Area type="monotone" dataKey="revenue" name="Revenue" stroke="#3B82F6" fill="url(#rev)"/>
        </AreaChart>
      </ResponsiveContainer>
    </div>
  )
}
