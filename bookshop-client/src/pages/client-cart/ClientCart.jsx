import { Link } from 'react-router-dom';

import React from 'react'

import ClientHeader from '../../components/ClientHeader/ClientHeader';
import ClientFooter from '../../components/ClientFooter/ClientFooter';
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav';

function ClientCart() {
  return (
    <>
    <ClientHeader />
    <ClientUseNavbar />
    <section className="section-pagetop bg-light">
        <div className="container">
          <h2 className="title-page">Giỏ hàng</h2>
        </div> 
      </section> 

      <section className="section-content padding-y">
        <div className="container">
          <div className="row">

            <main className="col-lg-9 mb-lg-0 mb-3">
              <div className="card">

                <div className="table-responsive-xl">
                  <table className="cart-table table table-borderless">
                    <thead className="text-muted">
                      <tr className="small text-uppercase">
                        <th scope="col" style={{ minWidth: '280px' }}>Sản phẩm</th>
                        <th scope="col" width="150" style={{ minWidth: '150px' }}>Giá</th>
                        <th scope="col" width="150" style={{ minWidth: '150px' }}>Số lượng</th>
                        <th scope="col" width="100" style={{ minWidth: '100px' }}></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>
                          <figure className="itemside">
                            <div className="float-start me-3">
                              <img src="img/80px.png" alt="product" />
                            </div>
                            <figcaption className="info">
                              <Link to="#" className="title">Tiếng Việt lớp 1</Link>
                            </figcaption>
                          </figure>
                        </td>
                        <td>
                          <div className="price-wrap">
                            <span className="price">20.000₫</span>
                          </div>
                        </td>
                        <td>
                          <input type="number" value="1" min="1" className="form-control" />
                        </td>
                        <td className="text-center text-nowrap">
                          <Link to="#" className="btn btn-success">Cập nhật</Link>
                          <Link to="#" className="btn btn-danger ms-1">Xóa</Link>
                        </td>
                      </tr>
                      <tr>
                        <td>
                          <figure className="itemside">
                            <div className="float-start me-3">
                              <img src="img/80px.png" alt="product" />
                            </div>
                            <figcaption className="info">
                              <Link to="#" className="title">Tiếng Việt lớp 2</Link>
                            </figcaption>
                          </figure>
                        </td>
                        <td>
                          <div className="price-wrap">
                            <span className="price">30.000₫</span>
                          </div>
                        </td>
                        <td>
                          <input type="number" value="1" min="1" className="form-control" />
                        </td>
                        <td className="text-center text-nowrap">
                          <Link to="#" className="btn btn-success">Cập nhật</Link>
                          <Link to="#" className="btn btn-danger ms-1">Xóa</Link>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div> {/* table.responsive-md.// */}

                <div className="card-body border-top">
                  <Link to="#" className="btn btn-primary float-end">Đặt hàng</Link>
                  <Link to="#" className="btn btn-light">Tiếp tục mua sắm</Link>
                </div> {/* card-body.// */}

              </div> {/* card.// */}
            </main> {/* col.// */}

            <aside className="col-lg-3">

              <div className="card mb-3">
                <div className="card-body">
                  <p className="card-title">Hình thức giao hàng</p>
                  <form action="">
                    <div className="form-check mb-2">
                      <input className="form-check-input" type="radio" name="delivery_method" id="delivery_method_1" defaultChecked />
                      <label className="form-check-label" htmlFor="delivery_method_1">Giao tiêu chuẩn</label>
                    </div>
                    <div className="form-check mb-2">
                      <input className="form-check-input" type="radio" name="delivery_method" id="delivery_method_2" />
                      <label className="form-check-label" htmlFor="delivery_method_2">Giao nhanh</label>
                    </div>
                  </form>
                </div> {/* card-body.// */}
              </div> {/* card.// */}

              <div className="card mb-3">
                <div className="card-body">
                  <p className="card-title">Mã giảm giá</p>
                  <form action="">
                    <div className="input-group mb-3">
                      <input type="text" className="form-control" placeholder="" />
                      <button className="btn btn-primary" type="button">Áp dụng</button>
                    </div>
                  </form>
                </div> {/* card-body.// */}
              </div> {/* card.// */}

              <div className="card">
                <div className="card-body">
                  <dl className="row mb-0">
                    <dt className="col-xxl-6 col-lg-12 col-6">Tạm tính:</dt>
                    <dd className="col-xxl-6 col-lg-12 col-6 text-end mb-3">50.000₫</dd>
                    <dt className="col-xxl-6 col-lg-12 col-6">Phí vận chuyển:</dt>
                    <dd className="col-xxl-6 col-lg-12 col-6 text-end mb-3">10.000₫</dd>
                    <dt className="col-xxl-6 col-lg-12 col-6">Tổng cộng:</dt>
                    <dd className="col-xxl-6 col-lg-12 col-6 text-end mb-3"><strong>60.000₫</strong></dd>
                  </dl>
                </div> {/* card-body.// */}
              </div> {/* card.// */}

            </aside> {/* col.// */}

          </div> {/* row.// */}
        </div> {/* container */}
      </section>
      <ClientFooter />
      </>
    )
};

export default ClientCart