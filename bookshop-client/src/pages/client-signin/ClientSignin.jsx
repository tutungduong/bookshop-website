import React from 'react'

import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

import { Link } from 'react-router-dom'

function ClientSignin() {
  return (
    <>
    <ClientHeader />
    <ClientUseNavbar />
     <section className="section-content" style={{ margin: '100px 0' }}>
      <div className="card mx-auto" style={{ maxWidth: '380px' }}>
        <div className="card-body">
          <h4 className="card-title mb-4">Đăng nhập</h4>
          <form>
            <div className="mb-3">
              <input
                name="username"
                className="form-control"
                placeholder="Tên đăng nhập"
                type="text"
                autoComplete="off"
              />
            </div>
            <div className="mb-3">
              <input
                name="password"
                className="form-control"
                placeholder="Mật khẩu"
                type="password"
                autoComplete="off"
              />
            </div>
            <button type="submit" className="btn btn-primary w-100">
              Đăng nhập
            </button>
          </form>
        </div>
      </div>
      <p className="text-center mt-4">
        Không có tài khoản? <Link to="/register">Đăng ký</Link>
      </p>
    </section>
    <ClientFooter />
    </>
  )
}

export default ClientSignin