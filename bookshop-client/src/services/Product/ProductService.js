import axios from 'axios';
import ResourceURL from '../../constants/ResourceURL';

const PRODUCT_API_BASE_URL = ResourceURL.PRODUCT;

class ProductService {
    getProducts() {
        return axios.get(PRODUCT_API_BASE_URL);
    }
}
export default new ProductService()
