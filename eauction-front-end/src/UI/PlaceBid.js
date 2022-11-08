import React, { useState } from 'react';
import { useForm, } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import "yup-phone";
import { Box, Button, Grid, TextField, Typography } from '@mui/material';

const PlaceBid = (props) => {

    const [placeBidHiddlen, setPlaceBidHiddlen] = useState(false);
    const [message, setMessage] = useState('');
    const [success, setSuccess] = useState(false);

    const BASE_URL = 'https://kzjbpinr64.execute-api.us-east-1.amazonaws.com/prod';

    const placebidschema = Yup.object().shape({
        buyer: Yup.object().shape({
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
        productID: Yup.string()
            .required('Product ID is required'),
        bidAmount: Yup.number()
            .required('Bid Amount is required')
            .positive(),
    });

    const { register, handleSubmit, formState: { errors }, reset } = useForm({ resolver: yupResolver(placebidschema) });

    const placeBidDialog = () => {
        setPlaceBidHiddlen(false);
    }

    async function onSubmit(data) {
        const bidData = { ...data, email: data.buyer.email }
        const options = {
            method: 'POST',
            rejectUnauthorized: false,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bidData)
        };
        const PLACE_BID_URL = BASE_URL + '/e-auction/api/v1/buyer/place-bid'
        var response = await fetch(PLACE_BID_URL, options);
        var response_data = await response.json();
        if (response.ok) {

            const email_body = {
                data: JSON.stringify(data),
                emailsubject: 'Bid Placed Successfully, Product ID: ' + response_data.productID,
                email: data.buyer.email
            }

            const email_options = {
                method: 'POST',
                rejectUnauthorized: false,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(email_body)
            }
            //sending email
            const EMAIL_URL = BASE_URL + '/e-auction/api/v1/email'
            await fetch(EMAIL_URL, email_options);
            setMessage('Bid Placed Successfully, Product ID: ' + response_data.productID);
            setPlaceBidHiddlen(true);
            setSuccess(true);
        } else {
            setMessage('Failed : ' + response_data.message);
            setPlaceBidHiddlen(true);
            setSuccess(false);
        }

        reset({ buyer: null, productID: null, bidAmount: null });
    }

    return (<>
        <div hidden={placeBidHiddlen} style={{ width: '60%' }}>

            <Typography variant="h6" align="center" color={'white'} margin="dense">Place Bid</Typography>
            <Grid margin container spacing={3}  >
                <Grid spacing={1} xs={5}>

                    <TextField required id="email" name="email" label="Email" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.email')} error={errors.buyer?.email ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.buyer?.email?.message}</Typography>

                    <TextField required id="firstname" name="firstName" label="First Name" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.firstName')} error={errors.buyer?.firstName ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.buyer?.firstName?.message}</Typography>

                    <TextField required id="lastname" name="lastName" label="Last Name" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.lastName')} error={errors.buyer?.lastName ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.buyer?.lastName?.message}</Typography>


                    <TextField required id="phone" name="phone" label="Phone" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.phone')} error={errors.buyer?.phone ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.buyer?.phone?.message}</Typography>

                    <TextField id="addressLine" name="addressLine" label="Address Line" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.address.addressLine')} />

                    <TextField id="city" name="city" label="City" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.address.city')} />

                    <TextField id="state" name="state" label="State" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.address.state')} />

                    <TextField id="pin" name="pin" label="Pin" fullWidth margin="dense" variant='outlined'
                        {...register('buyer.address.pin')} />

                </Grid>
                <Grid spacing={1} xs={1} >

                </Grid>
                <Grid spacing={1} xs={2} >

                    <TextField required id="productID" name="productID" label="Product ID" fullWidth margin="dense" variant='outlined'
                        {...register('productID')} error={errors.productID ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.productID?.message}</Typography>

                    <TextField required id="bidAmount" name="bidAmount" label="Bid Amount" fullWidth margin="dense" variant='outlined'
                        {...register('bidAmount')} error={errors.bidAmount ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.bidAmount?.message ? 'Bid Amount Should be Positiive Number' : ''}</Typography>
                </Grid>

            </Grid>
            <Box mt={3} align='right' padding={1}>
                <Button variant="contained" color="primary" onClick={handleSubmit(onSubmit)}>Place Bid</Button>
            </Box>


        </div>
        <div hidden={!placeBidHiddlen} style={{ width: '50%' }}>
            <Grid margin spacing={2} color={success ? 'green' : 'red'}>
                <Typography variant="h6" align="left" margin="dense">{message}</Typography>
                <Box padding={1} align='right'>
                    <Button variant="contained" color="primary" onClick={placeBidDialog}>close</Button>
                </Box>
            </Grid>
        </div>
    </>);

}
export default PlaceBid;