import React from 'react';
import { Link } from 'react-router-dom'; // Import Link từ react-router-dom
import ManagerPath from '../../constants/ManagerPath';

const AdminNavbar = () => (
  <nav className="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
    <div className="container">
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <Link className="nav-link" to={`${ManagerPath.USER}`}>
              <i className="bi bi-people"></i> Quản lý người dùng
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link"  to={`${ManagerPath.CATEGORY}`}>
              <i className="bi bi-tags"></i> Quản lý thể loại
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link"  to={`${ManagerPath.PRODUCT}`}>
              <i className="bi bi-book"></i> Quản lý sản phẩm
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link"  to={`${ManagerPath.CART}`}>
              <i className="bi bi-cart"></i> Quản lý giỏ hàng
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link"  to={`${ManagerPath.ORDER}`}>
              <i className="bi bi-inboxes"></i> Quản lý đơn hàng
            </Link>
          </li>
        </ul>
        <Link className="btn btn-primary" to="/login" role="button">Đăng nhập</Link>
      </div>
    </div>
  </nav>
);

export default AdminNavbar;
