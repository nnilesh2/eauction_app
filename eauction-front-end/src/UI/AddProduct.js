import React, { useState } from 'react';
import { useForm, } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import "yup-phone";
import { Box, Button, Grid, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material';


const AddProduct = (props) => {

    const [addProdcutHidden, setAddProductHidden] = useState(false);
    const [message, setMessage] = useState('');
    const [success, setSuccess] = useState(false);

    const addproduct = Yup.object().shape({
        seller: Yup.object().shape({
            email: Yup.string()
                .required('Email is required')
                .email('Please Enter Valid Eamil !'),
            firstName: Yup.string()
                .required('First Name Should Not Be Null !')
                .min(5, 'First Name must be at least 5 characters')
                .max(30, 'First Name must not exceed 30 characters'),

            lastName: Yup.string()
                .required('Last Name Should Not Be Null !')
                .min(5, 'Last Name must be at least 6 characters')
                .max(30, 'Last Name must not exceed 20 characters'),

            phone: Yup.string()
                .length(10)
                .required('Phone Number Should Not be Empty !')
                .phone('Phone Number Should be 10 Digits !'),
            address: Yup.object().shape({
                addressLine: Yup.string(),
                city: Yup.string(),
                state: Yup.string(),
                pin: Yup.string(),
            })
        }),
        product: Yup.object().shape({
            productID: Yup.string()
                .required('Product ID is required'),
            productName: Yup.string()
                .required('Product Name Should Not Be Null !')
                .min(5, 'Product Name must be at least 6 characters')
                .max(30, 'Product Name must not exceed 20 characters'),
            shortDescription: Yup.string(),
            detailedDescription: Yup.string(),
            category: Yup.string()
                .required('Product Category Should Not Be Null !')
        }),
        startPrice: Yup.number().required().positive(),
        bidEndDate: Yup.date().required()
    });

    const {
        register,
        handleSubmit,
        formState: { errors },
        reset
    } = useForm({
        resolver: yupResolver(addproduct)
    });

    const addProductDialog = () => {
        setAddProductHidden(false);
    }

    async function onSubmit(data) {
        const options = {
            method: 'POST',
            rejectUnauthorized: false,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };
        const ADD_PRODUCT_URL = 'http://localhost:8080/e-auction/api/v1/seller/add-product'
        var response = await fetch(ADD_PRODUCT_URL, options);
        var response_data = await response.json();
        if (response.ok) {
            setMessage('Product Added Successfully, Product ID: ' + response_data.productID);
            setAddProductHidden(true);
            setSuccess(true);
        } else {
            setMessage('Failed : ' + response_data.message);
            setAddProductHidden(true);
            setSuccess(false);
        }

        reset({seller:null,product:null,startPrice:null,bidEndDate:null});
    }


    return (
        <>
            <div hidden={addProdcutHidden} style={{ width: '60%' }}>

                <Typography variant="h6" align="center" color={'white'} margin="dense">Add Product</Typography>
                <Grid margin container spacing={3}  >
                    <Grid spacing={1} xs={5}>

                        <TextField required id="email" name="email" label="Email" fullWidth margin="dense" variant='outlined'
                            {...register('seller.email')} error={errors.seller?.email ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.seller?.email?.message}</Typography>

                        <TextField required id="firstname" name="firstName" label="First Name" fullWidth margin="dense" variant='outlined'
                            {...register('seller.firstName')} error={errors.seller?.firstName ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.seller?.firstName?.message}</Typography>

                        <TextField required id="lastname" name="lastName" label="Last Name" fullWidth margin="dense" variant='outlined'
                            {...register('seller.lastName')} error={errors.seller?.lastName ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.seller?.lastName?.message}</Typography>


                        <TextField required id="phone" name="phone" label="Phone" fullWidth margin="dense" variant='outlined'
                            {...register('seller.phone')} error={errors.seller?.phone ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.seller?.phone?.message}</Typography>

                        <TextField id="addressLine" name="addressLine" label="Address Line" fullWidth margin="dense" variant='outlined'
                            {...register('seller.address.addressLine')} />

                        <TextField id="city" name="city" label="City" fullWidth margin="dense" variant='outlined'
                            {...register('seller.address.city')} />

                        <TextField id="state" name="state" label="State" fullWidth margin="dense" variant='outlined'
                            {...register('seller.address.state')} />

                        <TextField id="pin" name="pin" label="Pin" fullWidth margin="dense" variant='outlined'
                            {...register('seller.address.pin')} />

                    </Grid>
                    <Grid spacing={1} xs={1} >

                    </Grid>
                    <Grid spacing={1} xs={5} >


                        <TextField required id="productID" name="productID" label="Product ID" fullWidth margin="dense" variant='outlined'
                            {...register('product.productID')} error={errors.product?.productID ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.product?.productID?.message}</Typography>

                        <TextField required id="productName" name="productName" label="Product Name" fullWidth margin="dense" variant='outlined'
                            {...register('product.productName')} error={errors.product?.productName ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.product?.productName?.message}</Typography>

                        <InputLabel id="category">Product Category*</InputLabel>
                        <Select
                            labelId="category"
                            id="category_select"
                            label="Product Category"
                            defaultValue={'Painting'}
                            required
                            fullWidth
                            {...register('product.category')} error={errors.product?.category ? true : false}
                        >
                            <MenuItem value={'Painting'}>Painting</MenuItem>
                            <MenuItem value={'Sculptor'}>Sculptor</MenuItem>
                            <MenuItem value={'Ornament'}>Ornament</MenuItem>
                        </Select>
                        <Typography variant="overline" color="textSecondary">{errors.product?.category?.message}</Typography>

                        <TextField required id="shortDescription" name="shortDescription" label="Produc Short Description" fullWidth margin="dense" variant='outlined'
                            {...register('product.shortDescription')} />

                        <TextField id="detailedDescription" name="detailedDescription" label="Detailed Description" fullWidth margin="dense" variant='outlined'
                            {...register('product.detailedDescription')} />

                        <TextField required id="startPrice" name="startPrice" label="Start Price" fullWidth margin="dense" variant='outlined'
                            {...register('startPrice')} error={errors.startPrice ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.startPrice?.message ? 'Start Price Should be Positiive Number' : ''}</Typography>

                        <InputLabel id="bidenddatelabel">Bid End Date*</InputLabel>
                        <TextField labelId='bidenddatelabel' required id="bidEndDate" align='right' type='date' name="bidEndDate" fullWidth margin="dense" variant='outlined'
                            {...register('bidEndDate')} error={errors.bidEndDate ? true : false} />
                        <Typography variant="overline" color="textSecondary">{errors.bidEndDate?.message ? 'Bid Date Should Not be Null !' : ''}</Typography>

                    </Grid>

                </Grid>
                <Box mt={3} align='right' padding={1}>
                    <Button variant="contained" color="primary" onClick={handleSubmit(onSubmit)}>Add Product</Button>
                </Box>


            </div>
            <div hidden={!addProdcutHidden} style={{ width: '50%' }}>
                <Grid margin spacing={2} color={success ? 'green' : 'red'}>
                    <Typography variant="h6" align="left" margin="dense">{message}</Typography>
                    <Box padding={1} align='right'>
                        <Button variant="contained" color="primary" onClick={addProductDialog}>close</Button>
                    </Box>
                </Grid>
            </div>
        </>
    );

}
export default AddProduct