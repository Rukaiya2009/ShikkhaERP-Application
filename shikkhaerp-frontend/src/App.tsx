import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { SuperAdminDashboard } from '@/modules/superadmin/pages/SuperAdminDashboard'
import { MainLayout } from '@/layouts/MainLayout'
import { AuthLayout } from '@/layouts/AuthLayout'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<AuthLayout />}>
          {/* Auth routes go here later */}
        </Route>
        <Route element={<MainLayout />}>
          <Route path="/" element={<SuperAdminDashboard />} />
          <Route path="/dashboard/super-admin" element={<SuperAdminDashboard />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
