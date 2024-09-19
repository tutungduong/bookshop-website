import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import AdminHeader from '../../components/AdminHeader/AdminHeader';
import AdminNavbar from '../../components/AdminNavbar/AdminNavbar';
import AdminFooter from '../../components/AdminFooter/AdminFooter';

import ManagerPath from '../../constants/ManagerPath';

import ProductService from '../../services/product/ProductService';

const ProductManager = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    ProductService.getProduct()
      .then((response) => {
        if (Array.isArray(response.data)) {
          setProducts(response.data);
          console.log(response.data);
        } else {
          console.error('Unexpected data format:', response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const deleteProduct = (id) => {
    ProductService.deleteProduct(id)
      .then((response) => {
        console.log(response);
        setProducts(products.filter(product => product.id !== id));
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <AdminHeader />
      <AdminNavbar />
      <section className="section-content">
        <div className="container">
          <header className="section-heading py-4 d-flex justify-content-between">
            <h3 className="section-title">Quản lý sản phẩm</h3>
            <Link className="btn btn-primary" to={`${ManagerPath.PRODUCT}/create`} role="button" style={{ height: 'fit-content' }}>
              Thêm sản phẩm
            </Link>
          </header>
          <main className="table-responsive-xl mb-5">
            <table className="table table-bordered table-striped table-hover align-middle">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">ID</th>
                  <th scope="col">Tên sách</th>
                  <th scope="col">Tác giả</th>
                  <th scope="col">Số trang</th>
                  <th scope="col">Nhà xuất bản</th>
                  <th scope="col">Thể loại</th>
                  <th scope="col">Năm phát hành</th>
                  <th scope="col">Số lượt mua</th>
                  <th scope="col">Thao tác</th>
                </tr>
              </thead>
              <tbody>
                {products.map((product, index) => (
                  <tr key={product.id}>
                    <th scope="row">{index + 1}</th>
                    <td>{product.id}</td>
                    <td>{product.name}</td>
                    <td>{product.author}</td>
                    <td>{product.pages}</td>
                    <td>{product.publisher}</td>
                    <td>{product.category?.name}</td> {/* Thể loại sản phẩm */}
                    <td>{product.publishedYear}</td>
                    <td>{product.variants?.length > 0 ? product.variants[0].price : 'N/A'}</td> {/* Giá sản phẩm */}
                    <td className="text-center text-nowrap">
                      <Link className="btn btn-primary me-2" to={`${ManagerPath.PRODUCT}/${product.id}`} role="button">
                        Xem
                      </Link>
                      <Link className="btn btn-success me-2" to={`${ManagerPath.PRODUCT}/${product.id}`} role="button">
                        Sửa
                      </Link>
                      <button className="btn btn-danger" onClick={() => deleteProduct(product.id)}>
                        Xóa
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </main>
          <nav className="mt-3 mb-5">
            <ul className="pagination justify-content-center">
              <li className="page-item disabled">
                <Link className="page-link" to="#">Trang trước</Link>
              </li>
              <li className="page-item active">
                <Link className="page-link" to="#">1</Link>
              </li>
              <li className="page-item">
                <Link className="page-link" to="#">2</Link>
              </li>
              <li className="page-item">
                <Link className="page-link" to="#">3</Link>
              </li>
              <li className="page-item">
                <Link className="page-link" to="#">Trang sau</Link>
              </li>
            </ul>
          </nav>
        </div>
      </section>
      <AdminFooter />
    </>
  );
};

export default ProductManager;
