// import React,{useState,useEffect} from 'react'
// import Nav from '../adminNav/AdminNav';
// import Footer from '../adminFooter/AdminFooter';
// import Header from '../adminHeader/AdminHeader';
// import { Routes, Route, Link } from 'react-router-dom';
// import ProductService from '../../services/Product/ProductService';
// import CategoryService from '../../services/CategoryService';

// const ProductList = () => {

//   const [product, setProduct] = useState([])
//   const [category, setCategory] = useState([])

//   useEffect(() => {
//     ProductService.getProducts().then((response) => {
//         setProduct(response.data)
      
//         console.log(response.data)
//     }).catch((error) => {
//         console.log(error)
//     })
// }, [])

//   return (
//     <>
//     <Header/>
//       <Nav/>
//       <section className='section-content'>
//         <div className='container'>
//           <header className="section-heading py-4 d-flex justify-content-between">
//             <h3 className="section-title">Quản lý sản phẩm</h3>
//             <Link className="btn btn-primary" to="#" role="button">
//               Thêm sản phẩm
//             </Link>
//           </header>
//           <main className='table-responsive-xl mb-5'>
//             <table className='table table-bordered table-striped table-hover align-middle'>
//               <thead>
//                 <tr>
//                   <th scope="col">#</th>
//                   <th scope="col">ID</th>
//                   <th scope="col">Tên sách</th>
//                   <th scope="col">Tác giả</th>
//                   <th scope="col">Số trang</th>
//                   <th scope="col">Nhà xuất bản</th>
//                   <th scope="col">Thể loại</th>
//                   <th scope="col">Năm phát hành</th>
//                   <th scope="col">Số lượt mua</th>
//                   <th scope="col">Thao tác</th>
//                 </tr>
//               </thead>
//               <tbody>
//                 {/* <tr>
//                   <th scope="row">1</th>
//                   <td>1</td>
//                   <td>Example</td>
//                   <td>Example</td>
//                   <td>Example</td>
//                   <td>Example</td>
//                   <td>Example</td>
//                   <td>Example</td>
//                   <td>Example</td>
//                   <td className="text-center text-nowrap">
//                     <Link className="btn btn-primary me-2" to="#" role="button">Xem</Link>
//                     <Link className="btn btn-success me-2" to="#" role="button">Sửa</Link>
//                     <Link className="btn btn-danger" to="#" role="button">Xóa</Link>
//                   </td>
//                 </tr> */}
//                 {
//                   product.map(
//                     product =>
//                     <tr key={product.id}>
//                       <th scope="row">{product.id}</th>
//                       <td>{product.id}</td>
//                       <td>{product.name}</td>
//                       <td>{product.author}</td>
//                       <td>{product.pages}</td>
//                       <td>{product.publisher}</td>
//                       <td>{product.categoryId}</td>
//                       <td>{product.publishedYear}</td>
//                       {/* <td>{product.buy}</td> */}
//                       <td>1000</td>
//                       <td className="text-center text-nowrap">
//                         <Link className="btn btn-primary me-2" to="#" role="button">Xem</Link>
//                         <Link className="btn btn-success me-2" to="#" role="button">Sửa</Link>
//                         <Link className="btn btn-danger" to="#" role="button">Xóa</Link>
//                       </td>
//                       </tr>
//                   )
//                 }
//               </tbody>
//             </table>
//           </main>
//           <nav className='mt-3 mb-5'>
//             <ul className="pagination justify-content-center">
//               <li className="page-item disabled">
//                 <Link className="page-link" to="#">Trang trước</Link>
//               </li>
//               <li className="page-item active">
//                 <Link className="page-link" to="#">1</Link>
//               </li>
//               <li className="page-item">
//                 <Link className="page-link" to="#">2</Link>
//               </li>
//               <li className="page-item">
//                 <Link className="page-link" to="#">3</Link>
//               </li>
//               <li className="page-item">
//                 <Link className="page-link" to="#">Trang sau</Link>
//               </li>
//             </ul>
//           </nav>
//         </div>
//       </section>
//       <Footer/>
//     </>
    
//   );
// };

// export default ProductList;
