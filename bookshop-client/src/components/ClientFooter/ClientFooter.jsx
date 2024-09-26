import React from 'react';

function ClientFooter() {
  return (
    <footer className="section-footer">
      <section className="footer-top py-5 bg-light">
        <div className="container">
          <div className="row">
            <aside className="col-sm-6 col-lg-3">
              <h6 className="pb-2">Giới thiệu</h6>
              <ul className="list-unstyled">
                <li><a href="#">Về Shop</a></li>
                <li><a href="#">Tuyển dụng</a></li>
                <li><a href="#">Chính sách thanh toán</a></li>
                <li><a href="#">Chính sách bảo mật</a></li>
                <li><a href="#">Giải quyết khiếu nại</a></li>
                <li><a href="#">Hợp tác</a></li>
              </ul>
            </aside>
            <aside className="col-sm-6 col-lg-3">
              <h6 className="pb-2">Dịch vụ khách hàng</h6>
              <ul className="list-unstyled">
                <li><a href="#">Trung tâm trợ giúp</a></li>
                <li><a href="#">Thông tin đơn hàng</a></li>
                <li><a href="#">Hướng dẫn mua hàng</a></li>
                <li><a href="#">Theo dõi đơn hàng</a></li>
              </ul>
            </aside>
            <aside className="col-sm-6 col-lg-3">
              <h6 className="pb-2">Kết nối với chúng tôi</h6>
              <ul className="list-unstyled">
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">Instagram</a></li>
                <li><a href="#">LinkedIn</a></li>
              </ul>
            </aside>
            <aside className="col-sm-6 col-lg-3">
              <h6 className="pb-2">Đăng ký nhận tin</h6>
              <form action="#">
                <div className="input-group">
                  <input type="email" className="form-control" placeholder="Email của bạn" />
                  <button className="btn btn-primary" type="button">Đăng ký</button>
                </div>
              </form>
            </aside>
          </div>
        </div>
      </section>
      <section className="footer-bottom py-2 bg-dark text-white">
        <div className="container text-center">
          <p>&copy; 2024 Shop Bán Sách. All rights reserved.</p>
        </div>
      </section>
    </footer>
  );
}

export default ClientFooter;
