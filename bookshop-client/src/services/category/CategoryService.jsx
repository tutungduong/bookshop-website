import axios from 'axios';
import ResourceURL from '../../constants/ResourceURL';

const CATEGORY_API_BASE_URL = ResourceURL.CATEGORY;

class CategoryService{
    getCategories(){
        return axios.get(CATEGORY_API_BASE_URL);
    }
    createCategory(category){
        return axios.post(CATEGORY_API_BASE_URL, category);
    }
    getCategoryById(categoryId){
        return axios.get(CATEGORY_API_BASE_URL + '/' + categoryId);
    }
    updateCategory(category, categoryId){
        return axios.put(CATEGORY_API_BASE_URL + '/' + categoryId,category);
    }
    deleteCategory(categoryId){
        return axios.delete(CATEGORY_API_BASE_URL + '/' + categoryId);
    }
}
export default new CategoryService()