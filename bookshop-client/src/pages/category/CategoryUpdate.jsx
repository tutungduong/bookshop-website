import React, { useEffect, useState } from 'react';
import AdminHeader from '../../components/AdminHeader/AdminHeader';
import AdminFooter from '../../components/AdminFooter/AdminFooter';
import CategoryService from '../../services/category/CategoryService';
import { useNavigate, useParams } from 'react-router-dom';
import ManagerPath from '../../constants/ManagerPath';

function CategoryUpdate() {
  const initialCategoryState = {
    name: '',
    description: '',
    thumbnail: '',
    status: 1
  };

  const [category, setCategory] = useState(initialCategoryState);
  const [initialCategory, setInitialCategory] = useState(initialCategoryState); // Lưu trạng thái ban đầu của category

  const navigate = useNavigate();
  const { id } = useParams();

  const handleSubmit = (e) => {
    e.preventDefault();
    CategoryService.updateCategory(category, id).then((response) => {
      console.log(response);
      navigate(ManagerPath.CATEGORY);
    }).catch((error) => {
      console.log(error);
    });
  };

  useEffect(() => {
    CategoryService.getCategoryById(id).then((response) => {
      setCategory(response.data);
      setInitialCategory(response.data); // Lưu giá trị category ban đầu
    }).catch((error) => {
      console.log(error);
    });
  }, [id]);

  const handleReset = () => {
    setCategory(initialCategory); // Đặt lại giá trị form về giá trị ban đầu
  };

  return (
    <>
      <AdminHeader />
      <section>
        <div className='container'>
          <header className="section-heading py-4 d-flex justify-content-between">
            <h3 className="section-title">Chỉnh sửa sản phẩm</h3>
          </header>

          <main className="add-book-form mb-5">
            <form className='w-50'>
              <div className='mb-3'>
                <label className="form-label">Tên thể loại</label>
                <input
                  type="text"
                  className="form-control"
                  name='name'
                  value={category.name}
                  onChange={(e) => setCategory({ ...category, name: e.target.value })}
                />
              </div>
              <div className='mb-3'>
                <label className="form-label">Mô tả</label>
                <input
                  className="form-control"
                  name='description'
                  value={category.description}
                  onChange={(e) => setCategory({ ...category, description: e.target.value })}
                />
              </div>
              {/* <div className='mb-3'>
                <label className="form-label">Hình</label>
                <input
                  type="file"
                  className="form-control"
                  name='image'
                  value={category.thumbnail}
                  onChange={(e) => setCategory({ ...category, thumbnail: e.target.files[0] })}
                />
              </div> */}
              <button type="submit" className="btn btn-primary" onClick={(e) => handleSubmit(e)}>Chỉnh sửa sản phẩm</button>
              <button type="button" className="btn btn-warning ms-2" onClick={handleReset}>Tẩy trống</button>
              <button type="button" className="btn btn-light ms-2" onClick={() => navigate(ManagerPath.CATEGORY)}>Hủy</button>
            </form>
          </main>
        </div>
      </section>
      <AdminFooter />
    </>
  );
}

export default CategoryUpdate;
