import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListCategoryComponent from './components/ListCategoryComponent';
import AddCategoryComponent from './components/AddCategoryComponent';
import ProductList from './components/product/ProductList';
import ProductCreate from './components/product/ProductCreate';

function App() {
  return (
    <div className="App">
   <Router>
      <Routes>
        <Route path="/" element={<ListCategoryComponent />} />
        <Route path="/categories" element={<ListCategoryComponent />} />
        <Route path="/add-categories" element={<AddCategoryComponent />} />
        <Route path="/update-categories/:id" element={<AddCategoryComponent />} />
        <Route path="/product" element={<ProductList/>} />
        <Route path="/add-product" element={<ProductCreate/>} />
      </Routes>
    </Router>
    </div>
  );
}

export default App;
