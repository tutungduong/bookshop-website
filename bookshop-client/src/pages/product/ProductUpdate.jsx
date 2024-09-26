import React, { useEffect, useState } from 'react';
import AdminHeader from '../../components/AdminHeader/AdminHeader';
import AdminFooter from '../../components/AdminFooter/AdminFooter';
import ProductService from '../../services/product/ProductService';

import { useNavigate, useParams } from 'react-router-dom';
import ManagerPath from '../../constants/ManagerPath';

function ProductUpdate() {
 const initialProductState = {
    title: '',
    author: '',
    pages: 0,
    publisher: '',
    yearPublishing: 0,
    category: '',
    price: 0,
    discount: 0,
    quantity: 0,
    description: '',
    imageName: null,
  };

  const [product, setProduct] = useState(initialProductState);
  const [initialProduct, setInitialProduct] = useState(initialProductState); // Lưu trạng thái ban đầu của product


  const navigate = useNavigate();
  const { id } = useParams();

  const handleSubmit = (e) => {
    e.preventDefault();
    ProductService.updateProduct(product, id).then((response) => {
      console.log(response);
      navigate(ManagerPath.PRODUCT);
    }).catch((error) => {
      console.log(error);
    });
  };

  useEffect(() => {
    ProductService.getProductById(id).then((response) => {
      setProduct(response.data);
      setInitialProduct(response.data); // Lưu giá trị category ban đầu
    }).catch((error) => {
      console.log(error);
    });
  }, [id]);

  const handleReset = () => {
    setProduct(initialProduct); // Đặt lại giá trị form về giá trị ban đầu
  };

  return (
    <section className="section-content">
      <div className="container">
        <header className="section-heading py-4 d-flex justify-content-between">
          <h3 className="section-title">Thêm sản phẩm</h3>
        </header>
        <main className="add-book-form mb-5">
          <form className="w-50" onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="add-book-name" className="form-label">Tên sách</label>
              <input
                type="text"
                className="form-control"
                id="add-book-name"
                name="name"
                value={product.name}
                onChange={(e) => setProduct({ ...product, name: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-author" className="form-label">Tác giả</label>
              <input
                type="text"
                className="form-control"
                id="add-book-author"
                name="author"
                value={product.author}
                onChange={(e) => setProduct({ ...product, author: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-pages" className="form-label">Số trang</label>
              <input
                type="number"
                className="form-control"
                id="add-book-pages"
                name="pages"
                value={product.pages}
                onChange={(e) => setProduct({ ...product, pages: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-publisher" className="form-label">Nhà xuất bản</label>
              <input
                type="text"
                className="form-control"
                id="add-book-publisher"
                name="publisher"
                value={product.publisher}
                onChange={(e) => setProduct({ ...product, publisher: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-yearPublishing" className="form-label">Năm phát hành</label>
              <input
                type="number"
                className="form-control"
                id="add-book-yearPublishing"
                name="yearPublishing"
                value={product.yearPublishing}
                onChange={(e) => setProduct({ ...product, yearPublishing: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-category" className="form-label">Thể loại</label>
              <input
                type="text"
                className="form-control"
                id="add-book-category"
                name="category"
                value={product.categoryId?.name || ''}
                onChange={(e) => setProduct({ ...product, category: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-price" className="form-label">Giá</label>
              <input
                type="number"
                className="form-control"
                id="add-book-price"
                name="price"
                value={product.price}
                onChange={(e) => setProduct({ ...product, price: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-discount" className="form-label">Khuyến mãi</label>
              <input
                type="number"
                className="form-control"
                id="add-book-discount"
                name="discount"
                value={product.discount}
                onChange={(e) => setProduct({ ...product, discount: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-quantity" className="form-label">Số lượng trong kho</label>
              <input
                type="number"
                className="form-control"
                id="add-book-quantity"
                name="quantity"
                value={product.quantity}
                onChange={(e) => setProduct({ ...product, quantity: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-description" className="form-label">Mô tả</label>
              <textarea
                className="form-control"
                id="add-book-description"
                name="description"
                rows="5"
                value={product.description}
                onChange={(e) => setProduct({ ...product, description: e.target.value })}
              ></textarea>
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-imageName" className="form-label">Hình</label>
              <input
                type="file"
                className="form-control"
                id="add-book-imageName"
                name="imageName"
                onChange={(e) => setProduct({ ...product, imageName: e.target.files[0] })}
              />
            </div>
            <button type="submit" className="btn btn-primary">Thêm sản phẩm</button>
            <button type="reset" className="btn btn-warning ms-2" onClick={handleReset}>Tẩy trống</button>
        
          </form>
        </main>
      </div>
    </section>
  );
};

export default ProductUpdate