import React from 'react'
import { Link } from 'react-router-dom';

import ClientHeader from '../../components/ClientHeader/ClientHeader';
import ClientUseNavbar from '../../components/ClientUseNavbav/ClientUseNavbav';
import ClientFooter from '../../components/ClientFooter/ClientFooter';

function ClientOrder() {
  return (
    <>
    <ClientHeader />
    <ClientUseNavbar />
    <section className="section-pagetop bg-light">
    <div className="container">
      <h2 className="title-page">Đơn hàng</h2>
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
          <div className="table-responsive-xxl">
            <table className="table table-bordered table-striped table-hover align-middle">
              <thead>
                <tr>
                  <th scope="col" style={{ minWidth: "125px" }}>Mã đơn hàng</th>
                  <th scope="col" style={{ minWidth: "100px" }}>Ngày mua</th>
                  <th scope="col" style={{ minWidth: "300px" }}>Sản phẩm</th>
                  <th scope="col" style={{ minWidth: "100px" }}>Tổng tiền</th>
                  <th scope="col" style={{ minWidth: "175px" }}>Trạng thái đơn hàng</th>
                  <th scope="col">Thao tác</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">3</th>
                  <td>15/12/2021</td>
                  <td>Tiếng Việt lớp 1</td>
                  <td>10.000₫</td>
                  <td><span className="badge bg-warning text-dark">Đang giao hàng</span></td>
                  <td className="text-center text-nowrap">
                    <Link className="btn btn-primary me-2" to="#" role="button">Xem đơn hàng</Link>
                  </td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td>10/12/2021</td>
                  <td>Tiếng Việt lớp 2 ...và 1 sản phẩm khác</td>
                  <td>60.000₫</td>
                  <td><span className="badge bg-danger">Hủy đơn hàng</span></td>
                  <td className="text-center text-nowrap">
                    <Link className="btn btn-primary me-2" to="#" role="button">Xem đơn hàng</Link>
                  </td>
                </tr>
                <tr>
                  <th scope="row">1</th>
                  <td>10/12/2021</td>
                  <td>Tiếng Việt lớp 2 ...và 1 sản phẩm khác</td>
                  <td>60.000₫</td>
                  <td><span className="badge bg-success">Giao hàng thành công</span></td>
                  <td className="text-center text-nowrap">
                    <Link className="btn btn-primary me-2" to="#" role="button">Xem đơn hàng</Link>
                  </td>
                </tr>
              </tbody>
            </table>
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

        </main> {/* col.// */}

      </div> {/* row.// */}
    </div> {/* container.// */}
  </section> {/* section-content.// */}
    <ClientFooter />
  </>
  )
}

export default ClientOrder