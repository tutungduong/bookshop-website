import React from 'react'
import { Link } from 'react-router-dom';

import ClientHeader from '../../components/ClientHeader/ClientHeader';
import ClientFooter from '../../components/ClientFooter/ClientFooter';
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav';

function ClientCategory() {
  return (
    <>
    <ClientHeader />
    <ClientUseNavbar />
  <section className="section-pagetop bg-light">
    <div className="container">
      <h2 className="title-page">Sách giáo khoa</h2>
      <nav>
        <ol className="breadcrumb">
          <li className="breadcrumb-item" aria-current="page"><Link to="#">Trang chủ</Link></li>
          <li className="breadcrumb-item active" aria-current="page">Sách giáo khoa</li>
        </ol>
      </nav>
    </div>
  </section>

  <section className="section-content padding-y">
    <div className="container">
      <div className="row">
        <aside className="col-md-4 col-lg-3 mb-md-0 mb-3">
          <div className="card">
            <article className="filter-group">
              <header className="card-header my-1">
                <a data-bs-toggle="collapse" href="#collapse_1" aria-expanded="true" aria-controls="collapse_1">
                  <i className="float-end bi bi-chevron-down"></i>
                  <h6 className="title fw-bold">Nhà xuất bản</h6>
                </a>
              </header>
              <div className="filter-content collapse show" id="collapse_1">
                <div className="card-body pt-0">
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_brand_1" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_brand_1">NXB Giáo dục</label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_brand_2" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_brand_2">NXB Đại học Sư phạm Hà Nội</label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_brand_3" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_brand_3">NXB Đại học Sư phạm TPHCM</label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_brand_4" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_brand_4">NXB Đại học Quốc gia Hà Nội</label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_brand_5" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_brand_5">NXB Đại học Huế</label>
                  </div>
                </div>
              </div>
            </article>
            <article className="filter-group">
              <header className="card-header my-1">
                <a data-bs-toggle="collapse" href="#collapse_2" aria-expanded="true" aria-controls="collapse_2">
                  <i className="float-end bi bi-chevron-down"></i>
                  <h6 className="title fw-bold">Giá bán</h6>
                </a>
              </header>
              <div className="filter-content collapse show" id="collapse_2">
                <div className="card-body pt-0">
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_price_1" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_price_1">Dưới 20.000₫</label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_price_2" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_price_2">Từ 20.000₫ đến 100.000₫</label>
                  </div>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="checkbox_price_3" defaultChecked />
                    <label className="form-check-label" htmlFor="checkbox_price_3">Trên 100.000₫</label>
                  </div>
                </div>
              </div>
            </article>
          </div>
        </aside>

        <main className="col-md-8 col-lg-9">
          <header className="border-bottom mb-4 pb-3">
            <div className="form-inline d-flex justify-content-between align-items-center">
              <span>30 sản phẩm</span>
              <div>
                <select className="form-select">
                  <option>Bán chạy nhất</option>
                  <option>Mới nhất</option>
                  <option>Đánh giá cao nhất</option>
                  <option>Giá thấp nhất</option>
                </select>
              </div>
            </div>
          </header>

          <div className="row item-grid">
            <div className="col-lg-4 col-md-6">
              <div className="card p-3 mb-4">
                <Link to="#" className="img-wrap text-center">
                  <img className="img-fluid" src="img/200px.png" alt="product" />
                </Link>
                <figcaption className="info-wrap mt-2">
                  <Link to="#" className="title">Tên một sản phẩm</Link>
                  <div className="price mt-1 fw-bold">450.000₫</div>
                </figcaption>
              </div>
            </div>
            {/* Repeat similar blocks for more products */}
          </div>

          <nav className="mt-4">
            <ul className="pagination">
              <li className="page-item disabled"><Link className="page-link" to="#">Trang trước</Link></li>
              <li className="page-item active"><Link className="page-link" to="#">1</Link></li>
              <li className="page-item"><Link className="page-link" to="#">2</Link></li>
              <li className="page-item"><Link className="page-link" to="#">3</Link></li>
              <li className="page-item"><Link className="page-link" to="#">Trang sau</Link></li>
            </ul>
          </nav>
        </main>
      </div>
    </div>
  </section>
  <ClientFooter />
    </>
  )
}

export default ClientCategory