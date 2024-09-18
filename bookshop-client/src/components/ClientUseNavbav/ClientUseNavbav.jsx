import React from 'react';

function ClientUseNavbar() {
  return (
    <nav className="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
      <div className="container">
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <strong><i className="bi bi-list"></i> Danh mục sản phẩm</strong>
              </a>
              <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a className="dropdown-item" href="#">Sách giáo khoa</a></li>
                <li><a className="dropdown-item" href="#">Sách khoa học</a></li>
                <li><a className="dropdown-item" href="#">Truyện tranh</a></li>
                <li><a className="dropdown-item" href="#">Tiểu thuyết</a></li>
                <li>
                  <hr className="dropdown-divider" />
                </li>
                <li><a className="dropdown-item" href="#">Tất cả danh mục</a></li>
              </ul>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Sản phẩm mới</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Sản phẩm bán chạy</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Khuyến mãi</a>
            </li>
          </ul>
          <a className="btn btn-light me-2" href="#" role="button">Đăng ký</a>
          <a className="btn btn-primary" href="#" role="button">Đăng nhập</a>
        </div>
      </div>
    </nav>
  );
}

export default ClientUseNavbar;
