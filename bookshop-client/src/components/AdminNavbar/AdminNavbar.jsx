// src/components/Navbar.js
import React from 'react';

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
            <a className="nav-link" href="#"><i className="bi bi-people"></i> Quản lý người dùng</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#"><i className="bi bi-tags"></i> Quản lý thể loại</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#"><i className="bi bi-book"></i> Quản lý sản phẩm</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#"><i className="bi bi-cart"></i> Quản lý giỏ hàng</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#"><i className="bi bi-inboxes"></i> Quản lý đơn hàng</a>
          </li>
        </ul>
        <a className="btn btn-primary" href="#" role="button">Đăng nhập</a>
      </div>
    </div>
  </nav>
);

export default AdminNavbar;
