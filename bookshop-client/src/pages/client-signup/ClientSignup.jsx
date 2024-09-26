import React from 'react'

import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

import { Link } from 'react-router-dom'

function ClientSignup() {
  return (
   <>
    <ClientHeader />
    <ClientUseNavbar />
    <section className="section-content" style={{ margin: '100px 0' }}>
      <div className="card mx-auto" style={{ maxWidth: '380px' }}>
        <div className="card-body">
          <h4 className="card-title mb-4">Đăng ký</h4>
          <form>
            <div className="mb-3">
              <label htmlFor="inputUsername" className="form-label">Tên đăng nhập</label>
              <input type="text" className="form-control" id="inputUsername" />
            </div>
            <div className="mb-3">
              <label htmlFor="inputName" className="form-label">Họ tên</label>
              <input type="text" className="form-control" id="inputName" />
            </div>
            <div className="mb-3">
              <label htmlFor="inputEmail" className="form-label">Email</label>
              <input type="email" className="form-control" id="inputEmail" />
            </div>
            <div className="mb-3">
              <label htmlFor="inputPhoneNumber" className="form-label">Số điện thoại</label>
              <input type="number" className="form-control" id="inputPhoneNumber" />
            </div>
            <div className="mb-3">
              <div className="form-check d-inline-block me-4">
                <input className="form-check-input" type="radio" name="radioGender" id="radioGender1" defaultChecked />
                <label className="form-check-label" htmlFor="radioGender1">Nam</label>
              </div>
              <div className="form-check d-inline-block">
                <input className="form-check-input" type="radio" name="radioGender" id="radioGender2" />
                <label className="form-check-label" htmlFor="radioGender2">Nữ</label>
              </div>
            </div>
            <div className="mb-3">
              <label htmlFor="inputAddress" className="form-label">Địa chỉ</label>
              <input type="text" className="form-control" id="inputAddress" />
            </div>
            <div className="mb-3">
              <label htmlFor="inputPassword" className="form-label">Mật khẩu</label>
              <input type="password" className="form-control" id="inputPassword" />
            </div>
            <div className="mb-3 form-check">
              <input className="form-check-input" type="checkbox" value="" id="checkboxPolicy" defaultChecked />
              <label className="form-check-label" htmlFor="checkboxPolicy">
                Đồng ý với <Link to="/terms">điều khoản sử dụng</Link>
              </label>
            </div>
            <button type="submit" className="btn btn-primary w-100">Đăng ký</button>
          </form>
        </div>
      </div>
      <p className="text-center mt-4">
        Đã có tài khoản? <Link to="/login">Đăng nhập</Link>
      </p>
    </section>
    <ClientFooter />
   </>
  )
}

export default ClientSignup