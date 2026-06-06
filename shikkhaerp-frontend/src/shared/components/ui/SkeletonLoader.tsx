import React from 'react'

export const SkeletonLoader: React.FC<{ className?: string }> = ({ className = 'h-4 w-full' }) => {
  return <div className={`animate-pulse rounded bg-gray-200 ${className}`}></div>
}
