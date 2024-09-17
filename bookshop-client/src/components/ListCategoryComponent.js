import React,{useState,useEffect} from 'react'
import CategoryService from '../services/CategoryService'
import { Link } from 'react-router-dom'

const ListCategoryComponent = () => {

    const [category, setCategory] = useState([])

    useEffect(() => {
        CategoryService.getCategories().then((response) => {
            setCategory(response.data)
            console.log(response.data)
        }).catch((error) => {
            console.log(error)
        })
    }, [])

    const deleteCategory = (id) => {
        CategoryService.deleteCategory(id).then((response) => {
            console.log(response)
            setCategory(category.filter(category => category.id !== id))
        }).catch((error) => {
            console.log(error)
        })
    }
    

  return (
    <div className='container'>
        <h2 className='text-center'>Category List</h2>
        <Link to='/add-categories' className='btn btn-primary mb-2'>Add Category</Link>
        <table className='table table-bordered table-striped'>
            <thead>
               <th>Category Name</th>
               <th>Category Description</th>
               <th>Action</th>
            </thead>
            <tbody>
                {
                    category.map(
                        category =>
                        <tr key={category.id}>
                            <td>{category.name}</td>
                            <td>{category.description}</td>
                            <td>
                                <Link to={`/update-categories/${category.id}`} className='btn btn-info'>Update</Link>
                                <button className='btn btn-danger' onClick={() => deleteCategory(category.id)}>Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListCategoryComponent