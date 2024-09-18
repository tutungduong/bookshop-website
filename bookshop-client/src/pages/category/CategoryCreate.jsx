import React from 'react'
import AdminHeader from '../../components/AdminHeader/AdminHeader'
import AdminFooter from '../../components/AdminFooter/AdminFooter'

function CategoryCreate() {
  return (
    <>
        <AdminHeader />
        <section>
         <div className='container'>
             <header className="section-heading py-4 d-flex justify-content-between">
                 <h3 className="section-title">Thêm sản phẩm</h3>
             </header>

             <main className="add-book-form mb-5">
                 <form className='w-50'>
                 <div className='mb-3'>
                     <label for="add-book-title" class="form-label">Tên thể loại</label>
                     <input type="text" class="form-control" id="add-book-title"></input>
                 </div>
                 <div className='mb-3'>
                     <label for="add-book-description" class="form-label">Mô tả</label>
                     <input class="form-control" id="add-book-description"></input>
                 </div>
                 <div className='mb-3'>
                     <label for="add-book-imageName" class="form-label">Hình</label>
                     <input type="file" class="form-control" id="add-book-imageName" name='image'></input>
                 </div>
                 <button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
                 <button type="reset" class="btn btn-warning ms-2">Tẩy trống</button>
                 <button type="button" class="btn btn-light ms-2">Hủy</button>
             </form>
             </main>
         </div>
     </section>
        <AdminFooter />
    </>
  )
}

export default CategoryCreate