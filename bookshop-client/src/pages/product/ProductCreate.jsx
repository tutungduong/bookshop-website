import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom'

function ProductCreate() {
  const [formData, setFormData] = useState({
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
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'imageName') {
      setFormData({
        ...formData,
        [name]: files[0],
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    // Add logic to handle form submission, e.g., API call to add a product
  };

  const handleReset = () => {
    setFormData({
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
    });
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
              <label htmlFor="add-book-title" className="form-label">Tên sách</label>
              <input
                type="text"
                className="form-control"
                id="add-book-title"
                name="title"
                value={formData.title}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-author" className="form-label">Tác giả</label>
              <input
                type="text"
                className="form-control"
                id="add-book-author"
                name="author"
                value={formData.author}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-pages" className="form-label">Số trang</label>
              <input
                type="number"
                className="form-control"
                id="add-book-pages"
                name="pages"
                value={formData.pages}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-publisher" className="form-label">Nhà xuất bản</label>
              <input
                type="text"
                className="form-control"
                id="add-book-publisher"
                name="publisher"
                value={formData.publisher}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-yearPublishing" className="form-label">Năm phát hành</label>
              <input
                type="number"
                className="form-control"
                id="add-book-yearPublishing"
                name="yearPublishing"
                value={formData.yearPublishing}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-category" className="form-label">Thể loại</label>
              <input
                type="text"
                className="form-control"
                id="add-book-category"
                name="category"
                value={formData.category}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-price" className="form-label">Giá</label>
              <input
                type="number"
                className="form-control"
                id="add-book-price"
                name="price"
                value={formData.price}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-discount" className="form-label">Khuyến mãi</label>
              <input
                type="number"
                className="form-control"
                id="add-book-discount"
                name="discount"
                value={formData.discount}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-quantity" className="form-label">Số lượng trong kho</label>
              <input
                type="number"
                className="form-control"
                id="add-book-quantity"
                name="quantity"
                value={formData.quantity}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-description" className="form-label">Mô tả</label>
              <textarea
                className="form-control"
                id="add-book-description"
                name="description"
                rows="5"
                value={formData.description}
                onChange={handleChange}
              ></textarea>
            </div>
            <div className="mb-3">
              <label htmlFor="add-book-imageName" className="form-label">Hình</label>
              <input
                type="file"
                className="form-control"
                id="add-book-imageName"
                name="imageName"
                onChange={handleChange}
              />
            </div>
            <button type="submit" className="btn btn-primary">Thêm sản phẩm</button>
            <button type="reset" className="btn btn-warning ms-2" onClick={handleReset}>Tẩy trống</button>
            <Link to="/admin/products" className="btn btn-light ms-2">Hủy</Link> {/* Link to the product manager page */}
          </form>
        </main>
      </div>
    </section>
  );
};


export default ProductCreate