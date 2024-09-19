import React from 'react'
import { Link } from 'react-router-dom'

import ClientHeader from '../../components/ClientHeader/ClientHeader'
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav'
import ClientFooter from '../../components/ClientFooter/ClientFooter'

function ClientProduct() {
  return (
    <>
    <ClientHeader />
    <ClientUseNavbar/>
    <section className="section-pagetop-2 bg-light">
        <div className="container">
          <nav>
            <ol className="breadcrumb">
              <li className="breadcrumb-item" aria-current="page">
                <Link to="/">Trang chủ</Link>
              </li>
              <li className="breadcrumb-item" aria-current="page">
                <Link to="/sach-giao-khoa">Sách giáo khoa</Link>
              </li>
              <li className="breadcrumb-item active" aria-current="page">Tiếng Việt lớp 1</li>
            </ol>
          </nav>
        </div>
      </section>

      <section className="section-content padding-y">
        <div className="container">
          <div className="row">
            <aside className="col-md-5 col-lg-6 mb-md-0 mb-4">
              <div id="productImageCarousel" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-indicators">
                  <button type="button" data-bs-target="#productImageCarousel" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#productImageCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                  <button type="button" data-bs-target="#productImageCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div className="carousel-inner">
                  <div className="carousel-item active">
                    <img src="img/570px-1.png" className="d-block w-100" alt="..." />
                  </div>
                  <div className="carousel-item">
                    <img src="img/570px-2.png" className="d-block w-100" alt="..." />
                  </div>
                  <div className="carousel-item">
                    <img src="img/570px-3.png" className="d-block w-100" alt="..." />
                  </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#productImageCarousel" data-bs-slide="prev">
                  <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span className="visually-hidden">Trước</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#productImageCarousel" data-bs-slide="next">
                  <span className="carousel-control-next-icon" aria-hidden="true"></span>
                  <span className="visually-hidden">Sau</span>
                </button>
              </div>
            </aside>

            <main className="col-md-7 col-lg-6">
              <h2 className="title">Tiếng Việt lớp 1</h2>
              <div className="rating-wrap my-3">
                <span className="rating-stars me-2">
                  <i className="bi bi-star-fill active"></i>
                  <i className="bi bi-star-fill active"></i>
                  <i className="bi bi-star-fill active"></i>
                  <i className="bi bi-star-fill active"></i>
                  <i className="bi bi-star-fill"></i>
                </span>
                <small className="label-rating text-muted me-2">20 đánh giá</small>
                <small className="label-rating text-success">
                  <i className="bi bi-bag-check-fill"></i> 150 đã mua
                </small>
              </div>
              <div className="mb-4">
                <span className="price h4">15.000đ</span>
                <span className="ms-2 text-muted text-decoration-line-through">20.000₫</span>
              </div>

              <dl className="row mb-4">
                <dt className="col-xl-4 col-sm-5 col-6">Nhà xuất bản</dt>
                <dd className="col-xl-8 col-sm-7 col-6">
                  <Link to="#">NXB Giáo dục</Link>
                </dd>
                <dt className="col-xl-4 col-sm-5 col-6">Thời gian giao hàng</dt>
                <dd className="col-xl-8 col-sm-7 col-6">2-5 ngày</dd>
                <dt className="col-xl-4 col-sm-5 col-6">Tình trạng</dt>
                <dd className="col-xl-8 col-sm-7 col-6">Còn hàng</dd>
                <dt className="col-xl-4 col-sm-5 col-6">Số lượng</dt>
                <dd className="col-xl-8 col-sm-7 col-6">
                  <input type="number" className="form-control w-50" value="1" min="1" max="1000" step="1" />
                </dd>
              </dl>

              <div>
                <Link to="#" className="btn btn-primary">Mua ngay</Link>
                <Link to="#" className="btn btn-light ms-2">Thêm vào giỏ hàng</Link>
              </div>
            </main>
          </div>
        </div>
      </section>
    <ClientFooter />
    </>
  )
}

export default ClientProduct