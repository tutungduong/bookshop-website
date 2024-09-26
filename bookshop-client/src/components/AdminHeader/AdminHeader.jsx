// src/components/Header.js
import React from 'react';
import { Link } from 'react-router-dom'

const AdminHeader = () => (
  <header className="section-header">
    <section className="header-main border-bottom">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-11 py-3">
            <Link to={`/admin`} className='text-body text-decoration-none'>
                <h3>Shop Bán Sách : Admin</h3>
            </Link>
          </div>
          <div className="col-1">
            <ul className="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
              <li>
                <Link to={`/`} className='nav-link text-body text-decoration-none'>
                    <i className="bi bi-window d-block text-center fs-3"></i>
                    Client
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  </header>
);

export default AdminHeader;
