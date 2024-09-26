import React from 'react';

function ClientHeader() {
  return (
    <header className="section-header">
      <section className="header-main border-bottom">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-lg-3 py-3">
              <a className="text-body" href="./home.html">
                <h3>Shop Bán Sách</h3>
              </a>
            </div>
            <div className="col-lg-4 col-xl-5">
              <form action="#" className="search">
                <div className="input-group w-100">
                  <input type="text" className="form-control" placeholder="Nhập từ khóa cần tìm ..." />
                  <button className="btn btn-primary" type="button">
                    <i className="bi bi-search"></i>
                  </button>
                </div>
              </form>
            </div>
            <div className="col-lg-5 col-xl-4">
              <ul className="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                <li>
                  <a href="#" className="nav-link text-body">
                    <i className="bi bi-person d-block text-center fs-3"></i>
                    Tài khoản
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link text-body">
                    <i className="bi bi-list-check d-block text-center fs-3"></i>
                    Đơn hàng
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link text-body">
                    <i className="bi bi-cart d-block text-center fs-3"></i>
                    Giỏ hàng
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </header>
  );
}

export default ClientHeader;
