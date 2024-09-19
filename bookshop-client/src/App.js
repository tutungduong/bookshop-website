import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AdminDashboard from './pages/AdminDashboard';
import Client from './pages/Client';
import CategoryManage from './pages/category/CategoryManage';
import CategoryCreate from './pages/category/CategoryCreate';
import ManagerPath from './constants/ManagerPath';
import CategoryUpdate from './pages/category/CategoryUpdate';
import ProductManager from './pages/product/ProductManage';
import ProductCreate from './pages/product/ProductCreate';
import ProductUpdate from './pages/product/ProductUpdate';

function App() {
  return (
    <div className="App">
   <Router>
      <Routes>
        <Route path="/" element={<Client />} />
        <Route path="/admin" element={<AdminDashboard/>} />
  
        <Route path={ManagerPath.CATEGORY} element={<CategoryManage />} />
        <Route path={`${ManagerPath.CATEGORY}/create`} element={<CategoryCreate />} />
        <Route path={`${ManagerPath.CATEGORY}/:id`} element={<CategoryUpdate />} />

        <Route path={ManagerPath.PRODUCT} element={<ProductManager/>} />
        <Route path={`${ManagerPath.PRODUCT}/create`} element={<ProductCreate />} />
        <Route path={`${ManagerPath.PRODUCT}/:id`} element={<ProductUpdate />} />

        

      </Routes>
    </Router>
    </div>
  );
}

export default App;
