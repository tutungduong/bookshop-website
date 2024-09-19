import React from 'react'

import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

import { Link } from 'react-router-dom'

function ClientSettingPassword() {
  return (
    <>
    <ClientHeader />
    <ClientUseNavbar />
    <section className="section-pagetop bg-light">
    <div className="container">
      <h2 className="title-page">Đổi mật khẩu</h2>
    </div> {/* container.// */}
  </section> {/* section-pagetop.// */}

  <section className="section-content padding-y">
    <div className="container">
      <div className="row">

        <aside className="col-md-3 mb-md-0 mb-3">
          <nav className="list-group">
            <Link className="list-group-item" to="#"> Tài khoản </Link>
            <Link className="list-group-item" to="#"> Đơn hàng của tôi </Link>
            <Link className="list-group-item" to="#"> Sản phẩm yêu thích </Link>
            <Link className="list-group-item active" to="#"> Đổi mật khẩu </Link>
            <Link className="list-group-item" to="#"> Thiết đặt </Link>
            <Link className="list-group-item" to="#"> Đăng xuất </Link>
          </nav>
        </aside> {/* col.// */}

        <main className="col-md-9">
          <article className="card">
            <div className="card-body">
              <div className="col-lg-6">
                <form>
                  <div className="mb-3">
                    <label htmlFor="inputCurrentPassword" className="form-label">
                      Nhập mật khẩu hiện tại
                    </label>
                    <input type="password" className="form-control" id="inputCurrentPassword" />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="inputNewPassword" className="form-label">
                      Nhập mật khẩu mới
                    </label>
                    <input type="password" className="form-control" id="inputNewPassword" />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="inputNewPasswordAgain" className="form-label">
                      Nhập mật khẩu mới một lần nữa
                    </label>
                    <input type="password" className="form-control" id="inputNewPasswordAgain" />
                  </div>
                  <button type="submit" className="btn btn-primary w-100">Đổi mật khẩu</button>
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

export default ClientSettingPassword