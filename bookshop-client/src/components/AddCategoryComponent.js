import React,{useState} from 'react'
import {useNavigate, useParams, Link} from 'react-router-dom';
import CategoryService from '../services/CategoryService'
import { useEffect } from 'react';

const AddCategoryComponent = () => {

    const [categoryName, setCategory] = useState('')
    const [categoryDescription, setDescription] = useState('')
    const navigate = useNavigate ()

    const {id} = useParams();

    const saveOfUpdate = (e) => {
        e.preventDefault()
        let category = {name: categoryName, description: categoryDescription, status: 0, thumbnail: 'thumbnail'}
        if(id){
            CategoryService.updateCategory(category, id).then(response => {
                console.log(response)
                navigate('/categories')
                alert('Category updated successfully')
            }).catch(error => {
                console.log(error)
            })
            return
        }
        else{
            CategoryService.createCategory(category).then(response => {
                console.log(response)
    
                navigate('/categories')
                alert('Category added successfully')
            }).catch(error => {
                console.log(error)
            })   
        }
       
    }

    useEffect(() => {
        CategoryService.getCategoryById(id).then((response) => {
            setCategory(response.data.name)
            setDescription(response.data.description)
        }).catch((error) => {
            console.log(error)
        })
    }, [])

    const title = () =>{
        if(id){
            return <h2 className='text-center'>Update Category</h2>
        }else{
            return <h2 className='text-center'>Add Category</h2>
        }
    }

  return (
    <div>
         <br /><br />
        <div className='container'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {
                        title()
                    }
                        <div className='card-body'>
                            <form>
                                <div className='form -group mb-2'>
                                    <label className='form-label'>Category Name: </label>
                                    <input
                                        type='text'
                                        className='form-control'
                                        name='categoryName'
                                        value={categoryName}
                                        onChange={(e) => setCategory(e.target.value)}
                                    ></input>
                                </div>
                                  <div className='form -group mb-2'>
                                    <label className='form-label'>Category Description: </label>
                                    <input
                                        type='text'
                                        className='form-control'
                                        name='categoryDescription'
                                        value={categoryDescription}
                                        onChange={(e) => setDescription(e.target.value)}
                                    ></input>
                                </div>
                                <button className='btn btn--success' onClick={(e) => saveOfUpdate(e)}>Save</button>
                            </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
    )
}

export default AddCategoryComponent