// src/components/Dashboard.js
import React from 'react';
import AdminHeader from '../components/AdminHeader/AdminHeader';
import AdminNavbar from '../components/AdminNavbar/AdminNavbar';
import AdminFooter from '../components/AdminFooter/AdminFooter';

const AdminDashboard = () => (

    <>
    <AdminHeader />
    <AdminNavbar />
  <section className="section-content padding-y">
    <div className="container">
      <div className="card bg-light">
        <div className="card-body p-5">
          <h1 className="display-4 mb-5">Quản lý Shop Bán Sách</h1>
          <div className="row">
            <div className="col-6 col-lg-3">
              <figure className="card bg-primary text-white">
                <div className="p-3">
                  <h4 className="title">125</h4>
                  <span>người dùng</span>
                </div>
              </figure>
            </div>
            <div className="col-6 col-lg-3">
              <figure className="card">
                <div className="p-3">
                  <h4 className="title">25</h4>
                  <span>thể loại sách</span>
                </div>
              </figure>
            </div>
            <div className="col-6 col-lg-3">
              <figure className="card">
                <div className="p-3">
                  <h4 className="title">475</h4>
                  <span>sách</span>
                </div>
              </figure>
            </div>
            <div className="col-6 col-lg-3">
              <figure className="card">
                <div className="p-3">
                  <h4 className="title">115</h4>
                  <span>đơn hàng</span>
                </div>
              </figure>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
    <AdminFooter />
  </>
);

export default AdminDashboard;
