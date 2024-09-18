// src/components/Header.js
import React from 'react';

const AdminHeader = () => (
  <header className="section-header">
    <section className="header-main border-bottom">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-11 py-3">
            <a className="text-body" href="./home.html">
              <h3>Shop Bán Sách : Admin</h3>
            </a>
          </div>
          <div className="col-1">
            <ul className="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
              <li>
                <a href="#" className="nav-link text-body">
                  <i className="bi bi-window d-block text-center fs-3"></i>
                  Client
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  </header>
);

export default AdminHeader;
