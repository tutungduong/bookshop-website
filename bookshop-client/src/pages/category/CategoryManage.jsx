import React, { useEffect, useState } from 'react';
import AdminHeader from '../../components/AdminHeader/AdminHeader';
import AdminFooter from '../../components/AdminFooter/AdminFooter';
import AdminNavbar from '../../components/AdminNavbar/AdminNavbar';
import CategoryService from '../../services/category/CategoryService';
import ManagerPath from '../../constants/ManagerPath';

import { Link } from 'react-router-dom'


function CategoryManage() {
  // Initialize category as an empty array to store a list of categories
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    // Fetch the categories and set the state
    CategoryService.getCategories()
      .then((response) => {
        if (Array.isArray(response.data)) {
          setCategories(response.data);
          console.log(response.data);
        } else {
          console.error('Unexpected data format:', response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

      const deleteCategory = (id) => {
        CategoryService.deleteCategory(id).then((response) => {
            console.log(response)
            setCategories(categories.filter(category => category.id !== id))
        }).catch((error) => {
            console.log(error)
        })
    }

  return (
    <>
      <AdminHeader />
      <AdminNavbar />
      <section className="section-content">
        <div className="container">
          <header className="section-heading py-4 d-flex justify-content-between">
            <h3 className="section-title">Quản lý sản phẩm</h3>
            <Link className="btn btn-primary" to={`${ManagerPath.CATEGORY}/create`} role="button" style={{ height: 'fit-content' }}>
            Thêm sản phẩm
          </Link>

          </header>
          <main className="table-responsive-xl mb-5">
            <table className="table table-bordered table-striped table-hover align-middle text-center">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">ID</th>
                  <th scope="col">Tên thể loại</th>
                  <th scope="col">Hình ảnh</th>
                  <th scope="col">Thao tác</th>
                </tr>
              </thead>
              <tbody>
                {categories.map((category, index) => (
                  <tr key={category.id}>
                    <th scope="row">{index + 1}</th>
                    <td>{category.id}</td>
                    <td>{category.name}</td>
                    <td>{category.thumbnail ? <img src={category.thumbnail} alt={category.name} width="50" /> : 'No Image'}</td>
                    <td className="text-center text-nowrap">
                    <Link className="btn btn-primary me-2" to={`${ManagerPath.CATEGORY}`} role="button">Xem</Link>
                    <Link className="btn btn-success me-2" to={`${ManagerPath.CATEGORY}/${category.id}`} role="button">Sửa</Link>
                    <Link className="btn btn-danger" role="button"
                    onClick={() => deleteCategory(category.id)}
                    >Xóa</Link>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </main>
          <nav className="mt-3 mb-5">
            <ul className="pagination justify-content-center">
              <li className="page-item disabled"><a className="page-link" href="#">Trang trước</a></li>
              <li className="page-item active"><a className="page-link" href="#">1</a></li>
              <li className="page-item"><a className="page-link" href="#">2</a></li>
              <li className="page-item"><a className="page-link" href="#">3</a></li>
              <li className="page-item"><a className="page-link" href="#">Trang sau</a></li>
            </ul>
          </nav>
        </div>
      </section>
      <AdminFooter />
    </>
  );
}

export default CategoryManage;
