import axios from 'axios';
import ResourceURL from '../../constants/ResourceURL';

const PRODUCT_API_BASE_URL = ResourceURL.PRODUCT;

class ProductService {
    getProduct(){
        return axios.get(PRODUCT_API_BASE_URL);
    }
    createProduct(product){
        return axios.post(PRODUCT_API_BASE_URL, product);
    }
    getProductById(productId){
        return axios.get(PRODUCT_API_BASE_URL + '/' + productId);
    }
    updateProduct(product, productId){
        return axios.put(PRODUCT_API_BASE_URL + '/' + productId, product);
    }
    deleteProduct(productId){
        return axios.delete(PRODUCT_API_BASE_URL + '/' + productId);
    }

}
export default new ProductService()
