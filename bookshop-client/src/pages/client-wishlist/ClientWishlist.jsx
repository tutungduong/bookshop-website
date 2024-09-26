import React from 'react'

import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

import { Link } from 'react-router-dom'

function ClientWishlist() {
  return (
   <>
   <ClientHeader/>
    <ClientUseNavbar/>
    <section className="section-pagetop bg-light">
        <div className="container">
          <h2 className="title-page">Sản phẩm yêu thích</h2>
        </div>
      </section>

      <section className="section-content padding-y">
        <div className="container">
          <div className="row">
            <aside className="col-md-3 mb-md-0 mb-3">
              <nav className="list-group">
                <Link className="list-group-item" to="/account"> Tài khoản </Link>
                <Link className="list-group-item" to="/orders"> Đơn hàng của tôi </Link>
                <Link className="list-group-item active" to="/wishlist"> Sản phẩm yêu thích </Link>
                <Link className="list-group-item" to="/change-password"> Đổi mật khẩu </Link>
                <Link className="list-group-item" to="/settings"> Thiết đặt </Link>
                <Link className="list-group-item" to="/logout"> Đăng xuất </Link>
              </nav>
            </aside>

            <main className="col-md-9">
              <article className="card">
                <div className="card-body">
                  <div className="row g-3">
                    <div className="col-lg-6">
                      <figure className="d-flex align-items-center m-0">
                        <div className="aside">
                          <img src="img/80px.png" width="80" height="80" alt="Tiếng Việt lớp 1" />
                        </div>
                        <figcaption className="ps-3">
                          <Link to="#">Tiếng Việt lớp 1</Link>
                          <p className="mb-2">
                            <span className="price">320.000₫</span>
                            <span className="ms-2 text-muted text-decoration-line-through">350.000₫</span>
                          </p>
                          <button type="button" className="btn btn-primary btn-sm">
                            Thêm vào giỏ hàng
                          </button>
                          <button
                            type="button"
                            className="btn btn-danger btn-sm ms-1"
                            data-toggle="tooltip"
                            title="Xóa khỏi danh sách yêu thích"
                            data-original-title="Xóa khỏi danh sách yêu thích"
                          >
                            <i className="bi bi-trash"></i>
                          </button>
                        </figcaption>
                      </figure>
                    </div>

                    <div className="col-lg-6">
                      <figure className="d-flex align-items-center m-0">
                        <div className="aside">
                          <img src="img/80px.png" width="80" height="80" alt="Tiếng Việt lớp 2" />
                        </div>
                        <figcaption className="ps-3">
                          <Link to="#">Tiếng Việt lớp 2</Link>
                          <p className="mb-2">
                            <span className="price">320.000₫</span>
                            <span className="ms-2 text-muted text-decoration-line-through">350.000₫</span>
                          </p>
                          <button type="button" className="btn btn-primary btn-sm">
                            Thêm vào giỏ hàng
                          </button>
                          <button
                            type="button"
                            className="btn btn-danger btn-sm ms-1"
                            data-toggle="tooltip"
                            title="Xóa khỏi danh sách yêu thích"
                            data-original-title="Xóa khỏi danh sách yêu thích"
                          >
                            <i className="bi bi-trash"></i>
                          </button>
                        </figcaption>
                      </figure>
                    </div>

                    <div className="col-lg-6">
                      <figure className="d-flex align-items-center m-0">
                        <div className="aside">
                          <img src="img/80px.png" width="80" height="80" alt="Tiếng Việt lớp 3" />
                        </div>
                        <figcaption className="ps-3">
                          <Link to="#">Tiếng Việt lớp 3</Link>
                          <p className="mb-2">
                            <span className="price">320.000₫</span>
                          </p>
                          <button type="button" className="btn btn-primary btn-sm">
                            Thêm vào giỏ hàng
                          </button>
                          <button
                            type="button"
                            className="btn btn-danger btn-sm ms-1"
                            data-toggle="tooltip"
                            title="Xóa khỏi danh sách yêu thích"
                            data-original-title="Xóa khỏi danh sách yêu thích"
                          >
                            <i className="bi bi-trash"></i>
                          </button>
                        </figcaption>
                      </figure>
                    </div>
                  </div>
                </div>
              </article>
            </main>
          </div>
        </div>
      </section>
    <ClientFooter/>
   </>
  )
}

export default ClientWishlist