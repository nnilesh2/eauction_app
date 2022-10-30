import React, { useState } from 'react';
import { useForm, } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import "yup-phone";
import { Box, Button, Grid, TextField, Typography } from '@mui/material';

const UpdateBid = (props) => {

    const [updateBidHiddlen, setUpdateBidHiddlen] = useState(false);
    const [message, setMessage] = useState('');
    const [success, setSuccess] = useState(false);

    const updatebidschema = Yup.object().shape({
        email: Yup.string()
            .required('Email is required')
            .email('Please Enter Valid Eamil !'),
        productID: Yup.string()
            .required('Product ID is required'),
        bidAmount: Yup.number()
            .required('Bid Amount is required')
            .positive()
    });

    const { register, handleSubmit, formState: { errors }, reset } = useForm({ resolver: yupResolver(updatebidschema) });

    const updateBidDialog = () => {
        setUpdateBidHiddlen(false);
    }

    async function onSubmit(data) {
        const options = {
            method: 'PUT',
            rejectUnauthorized: false,
            headers: {
                'Content-Type': 'application/json'
            },
        };
        const productID = data.productID;
        const email = data.email;
        const bidAmount = data.bidAmount;

        const UPDATE_BID_URL = 'http://localhost:8080/e-auction/api/v1/buyer/update-bid/'+productID+'/'+email+'/'+ bidAmount;
        var response = await fetch(UPDATE_BID_URL, options);
        var response_data = await response.json();
        if (response.ok) {
            setMessage('Bid Updated Successfully, Product ID: ' + response_data.productID);
            setUpdateBidHiddlen(true);
            setSuccess(true);
        } else {
            setMessage('Failed : ' + response_data.message);
            setUpdateBidHiddlen(true);
            setSuccess(false);
        }

        reset({ productID: null, email: null, bidAmount: null });
    }

    return (<>
        <div hidden={updateBidHiddlen} style={{ width: '60%' }}>

            <Typography variant="h6" align="center" color={'white'} margin="dense">Update Bid</Typography>
            <Grid margin container spacing={1}  >
                <Grid spacing={1} xs={5}>

                    <TextField required id="email" name="email" label="Email" fullWidth margin="dense" variant='outlined'
                        {...register('email')} error={errors?.email ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.email?.message}</Typography>

                    <TextField required id="productID" name="productID" label="Product ID" fullWidth margin="dense" variant='outlined'
                        {...register('productID')} error={errors.productID ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.productID?.message}</Typography>

                    <TextField required id="bidAmount" name="bidAmount" label="Bid Amount" fullWidth margin="dense" variant='outlined'
                        {...register('bidAmount')} error={errors.bidAmount ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.bidAmount?.message ? 'Bid Amount Should be Positiive Number' : ''}</Typography>

                </Grid>
            </Grid>
            <Box mt={3} align='right' padding={1}>
                <Button variant="contained" color="primary" onClick={handleSubmit(onSubmit)}>Update Bid</Button>
            </Box>


        </div>
        <div hidden={!updateBidHiddlen} style={{ width: '50%' }}>
            <Grid margin spacing={2} color={success ? 'green' : 'red'}>
                <Typography variant="h6" align="left" margin="dense">{message}</Typography>
                <Box padding={1} align='right'>
                    <Button variant="contained" color="primary" onClick={updateBidDialog}>close</Button>
                </Box>
            </Grid>
        </div>
    </>);

}

export default UpdateBid;