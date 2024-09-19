import React from 'react'
import { Link } from 'react-router-dom';

import ClientHeader from '../../components/ClientHeader/ClientHeader';
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav';
import ClientFooter from '../../components/ClientFooter/ClientFooter';

function ClientOrderDetail() {
  return (
   <>
    <ClientHeader />
    <ClientUseNavbar />
    <section className="section-pagetop bg-light">
        <div className="container">
          <h2 className="title-page">Thông tin đơn hàng #1</h2>
        </div> {/* container.// */}
      </section> {/* section-pagetop.// */}

      <section className="section-content padding-y">
        <div className="container">
          <div className="row">

            <aside className="col-md-3 mb-md-0 mb-3">
              <nav className="list-group">
                <Link className="list-group-item" to="#"> Tài khoản </Link>
                <Link className="list-group-item active" to="#"> Đơn hàng của tôi </Link>
                <Link className="list-group-item" to="#"> Sản phẩm yêu thích </Link>
                <Link className="list-group-item" to="#"> Đổi mật khẩu </Link>
                <Link className="list-group-item" to="#"> Thiết đặt </Link>
                <Link className="list-group-item" to="#"> Đăng xuất </Link>
              </nav>
            </aside> {/* col.// */}

            <main className="col-md-9">

              <article className="card mb-4">

                <header className="card-header">
                  <strong className="d-inline-block me-4">Mã đơn hàng: 1</strong>
                  <span>Ngày mua: 10/12/2021</span>
                </header> {/* card-header.// */}

                <div className="card-body pb-0">
                  <div className="row">
                    <div className="col-lg-8">
                      <h6 className="text-muted">Địa chỉ người nhận</h6>
                      <p className="lh-lg">
                        Nguyễn Thị A <br />
                        Số điện thoại: 0919944382 <br />
                        Địa chỉ: Đường Nguyễn Duy Trinh, Phường Bình Trưng Đông, Quận 2, TP.HCM
                      </p>
                    </div>
                    <div className="col-lg-4">
                      <h6 className="text-muted">Hình thức thanh toán</h6>
                      <span className="text-success">
                        <i className="fab fa-lg fa-cc-visa"></i>
                        Giao tiêu chuẩn
                      </span>
                      <p className="lh-lg">
                        Tạm tính: 50.000₫ <br />
                        Phí vận chuyển: 10.000₫ <br />
                        <strong>Tổng cộng: 60.000₫</strong>
                      </p>
                    </div>
                  </div> {/* row.// */}
                </div> {/* card-body.// */}

                <hr className="m-0" />

                <div className="table-responsive">
                  <table className="cart-table table table-borderless">
                    <thead className="text-muted">
                      <tr className="small text-uppercase">
                        <th scope="col" style={{ minWidth: "280px" }}>Sản phẩm</th>
                        <th scope="col" width="150" style={{ minWidth: "150px" }}>Giá</th>
                        <th scope="col" width="150" style={{ minWidth: "150px" }}>Số lượng</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>
                          <figure className="itemside">
                            <div className="float-start me-3"><img src="img/80px.png" alt="Sản phẩm" /></div>
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
                        <td>1</td>
                      </tr>
                      <tr>
                        <td>
                          <figure className="itemside">
                            <div className="float-start me-3"><img src="img/80px.png" alt="Sản phẩm" /></div>
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
                        <td>1</td>
                      </tr>
                    </tbody>
                  </table>
                </div> {/* table.responsive-md.// */}

                <div className="card-footer py-3">
                  <Link to="#" className="btn btn-primary me-2">Theo dõi đơn hàng</Link>
                  <Link to="#" className="btn btn-danger">Hủy đơn hàng</Link>
                </div> {/* card-footer.// */}

              </article>

            </main> {/* col.// */}

          </div> {/* row.// */}
        </div> {/* container.// */}
      </section> {/* section-content.// */}
    <ClientFooter />
   </>
  )
}

export default ClientOrderDetail