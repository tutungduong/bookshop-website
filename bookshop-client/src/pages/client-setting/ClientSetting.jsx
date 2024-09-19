import React from 'react'
import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

import { Link } from 'react-router-dom'

function ClientSetting() {
  return (
    <>
     <ClientHeader />
     <ClientUseNavbar />
     <section className="section-pagetop bg-light">
        <div className="container">
          <h2 className="title-page">Thiết đặt</h2>
        </div> {/* container.// */}
      </section> {/* section-pagetop.// */}

      <section className="section-content padding-y">
        <div className="container">
          <div className="row">
            <aside className="col-md-3 mb-md-0 mb-3">
              <nav className="list-group">
                <Link className="list-group-item" to="#">Tài khoản</Link>
                <Link className="list-group-item" to="#">Đơn hàng của tôi</Link>
                <Link className="list-group-item" to="#">Sản phẩm yêu thích</Link>
                <Link className="list-group-item" to="#">Đổi mật khẩu</Link>
                <Link className="list-group-item active" to="#">Thiết đặt</Link>
                <Link className="list-group-item" to="#">Đăng xuất</Link>
              </nav>
            </aside> {/* col.// */}

            <main className="col-md-9">
              <article className="card">
                <div className="card-body">
                  <div className="col-lg-6">
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
                          <input className="form-check-input" type="radio" name="radioGender" id="radioGender1" checked />
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
                      <button type="submit" className="btn btn-primary w-100">Cập nhật thông tin mới</button>
                    </form>
                  </div>
                </div> {/* card-body.// */}
              </article>
            </main> {/* col.// */}
          </div> {/* row.// */}
        </div> {/* container.// */}
      </section> {/* section-content.// */}
      <ClientFooter />
    </>
  )
}

export default ClientSetting