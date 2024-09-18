import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListCategoryComponent from './components/ListCategoryComponent';
import AddCategoryComponent from './components/AddCategoryComponent';
import ProductList from './components/product/ProductList';
import ProductCreate from './components/product/ProductCreate';
import AdminDashboard from './pages/AdminDashboard';
import Client from './pages/Client';

function App() {
  return (
    <div className="App">
   <Router>
      <Routes>
        <Route path="/" element={<Client />} />
        <Route path="/categories" element={<ListCategoryComponent />} />
        <Route path="/add-categories" element={<AddCategoryComponent />} />
        <Route path="/update-categories/:id" element={<AddCategoryComponent />} />
        <Route path="/product" element={<ProductList/>} />
        <Route path="/add-product" element={<ProductCreate/>} />
        <Route path="/admin" element={<AdminDashboard/>} />
      </Routes>
    </Router>
    </div>
  );
}

export default App;
