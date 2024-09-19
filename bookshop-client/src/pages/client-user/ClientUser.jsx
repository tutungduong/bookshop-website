import React from 'react'

import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

import { Link } from 'react-router-dom'

function ClientUser() {
  return (
   <>
   <ClientHeader />
    <ClientUseNavbar />
    <section className="section-pagetop bg-light">
        <div className="container">
          <h2 className="title-page">Tài khoản</h2>
        </div>
      </section>

      <section className="section-content padding-y">
        <div className="container">
          <div className="row">
            <aside className="col-md-3 mb-md-0 mb-3">
              <nav className="list-group">
                <Link className="list-group-item active" to="/account"> Tài khoản </Link>
                <Link className="list-group-item" to="/orders"> Đơn hàng của tôi </Link>
                <Link className="list-group-item" to="/wishlist"> Sản phẩm yêu thích </Link>
                <Link className="list-group-item" to="/change-password"> Đổi mật khẩu </Link>
                <Link className="list-group-item" to="/settings"> Thiết đặt </Link>
                <Link className="list-group-item" to="/logout"> Đăng xuất </Link>
              </nav>
            </aside>

            <main className="col-md-9">
              <article className="card">
                <div className="card-body">
                  <div>
                    <h5>Nguyễn Thị A</h5>
                    <p>nguyenthia@gmail.com</p>
                  </div>
                  <hr />
                  <div>
                    <p className="bi bi-map d-block lh-lg">
                      Địa chỉ:
                      <br />
                      Đường Nguyễn Duy Trinh, Phường Bình Trưng Đông, Quận 2, TP.HCM
                    </p>
                  </div>
                  <article className="card-group">
                    <figure className="card bg-light">
                      <div className="p-3">
                        <h4 className="title">2</h4>
                        <span>Sản phẩm trong giỏ</span>
                      </div>
                    </figure>
                    <figure className="card bg-light">
                      <div className="p-3">
                        <h4 className="title">5</h4>
                        <span>Đơn hàng</span>
                      </div>
                    </figure>
                    <figure className="card bg-light">
                      <div className="p-3">
                        <h4 className="title">5</h4>
                        <span>Sản phẩm đang giao</span>
                      </div>
                    </figure>
                    <figure className="card bg-light">
                      <div className="p-3">
                        <h4 className="title">10</h4>
                        <span>Sản phẩm đã nhận</span>
                      </div>
                    </figure>
                  </article>
                </div>
              </article>
            </main>
          </div>
        </div>
      </section>
    <ClientFooter />
   </>
  )
}

export default ClientUser