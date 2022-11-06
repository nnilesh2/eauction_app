import React, { useState } from 'react';
import { useForm, } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import "yup-phone";
import { Box, Button, Grid, TextField, Typography } from '@mui/material';
const DeleteProduct = (props) => {

    const [deleteProductHiddlen, setDeleteProductBidHiddlen] = useState(false);
    const [message, setMessage] = useState('');
    const [success, setSuccess] = useState(false);

    const BASE_URL = 'http://localhost:8080';

    const deleteProductSchema = Yup.object().shape({
        productID: Yup.string().required('Product ID is required')
    });

    const { register, handleSubmit, formState: { errors }, reset } = useForm({ resolver: yupResolver(deleteProductSchema) });

    const deleteProductDialog = () => {
        setDeleteProductBidHiddlen(false);
    }

    async function onSubmit(data) {
        const options = {
            method: 'DELETE',
            rejectUnauthorized: false,
            headers : { 
                'Content-Type': 'application/json',
                'Accept': 'application/json'
               }
        };
        const productID = data.productID;
        const DELETE_BID_URL = BASE_URL+'/e-auction/api/v1/seller/delete/'+productID;
        var response = await fetch(DELETE_BID_URL, options);
        var response_data = await response.json();
        if (response.ok) {
            setMessage('Product Deleted Successfully, Product ID: ' + response_data.productID);
            setDeleteProductBidHiddlen(true);
            setSuccess(true);
        } else {
            setMessage('Failed : ' + response_data.message);
            setDeleteProductBidHiddlen(true);
            setSuccess(false);
        }

        reset({ productID: null });
    }

    return (<>
        <div hidden={deleteProductHiddlen} style={{ width: '60%' }}>

            <Typography variant="h6" align="center" color={'white'} margin="dense">Delete Product</Typography>
            <Grid margin container spacing={1}  >
                <Grid spacing={1} xs={5}>

                    <TextField required id="productID" name="productID" label="Product ID" fullWidth margin="dense" variant='outlined'
                        {...register('productID')} error={errors.productID ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.productID?.message}</Typography>

                </Grid>
            </Grid>
            <Box mt={3} align='right' padding={1}>
                <Button variant="contained" color="primary" onClick={handleSubmit(onSubmit)}>Delete Product</Button>
            </Box>


        </div>
        <div hidden={!deleteProductHiddlen} style={{ width: '50%' }}>
            <Grid margin spacing={2} color={success ? 'green' : 'red'}>
                <Typography variant="h6" align="left" margin="dense">{message}</Typography>
                <Box padding={1} align='right'>
                    <Button variant="contained" color="primary" onClick={deleteProductDialog}>close</Button>
                </Box>
            </Grid>
        </div>
    </>);

}
export default DeleteProduct;