import React, { useState } from 'react';

import AdminHeader from '../../components/AdminHeader/AdminHeader';
import AdminNavbar from '../../components/AdminNavbar/AdminNavbar';
import AdminFooter from '../../components/AdminFooter/AdminFooter';



const AdminSignin = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Xử lý đăng nhập ở đây
    console.log('Submitted:', formData);
  };

  return (
    <>
    <AdminHeader />
    <AdminNavbar />
    <section className="section-content" style={{ margin: '100px 0' }}>
      <div className="card mx-auto" style={{ maxWidth: '380px' }}>
        <div className="card-body">
          <h4 className="card-title mb-4">Đăng nhập Admin</h4>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <input
                name="username"
                className="form-control"
                placeholder="Tên đăng nhập"
                type="text"
                autoComplete="off"
                value={formData.username}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <input
                name="password"
                className="form-control"
                placeholder="Mật khẩu"
                type="password"
                autoComplete="off"
                value={formData.password}
                onChange={handleChange}
              />
            </div>
            <button type="submit" className="btn btn-primary w-100">
              Đăng nhập
            </button>
          </form>
        </div>
      </div>
    </section>
    <AdminFooter />
    </>
  );
};

export default AdminSignin;
