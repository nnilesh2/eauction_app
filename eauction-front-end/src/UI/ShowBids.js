import React, { useState } from 'react';
import { useForm, } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import "yup-phone";
import { Box, Button, Grid, TextField, Typography } from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import { useEffect } from 'react';

const ShowBids = (props) => {

    const BASE_URL = 'http://localhost:8080';

    const [hideBids, setHideBids] = useState(true);
    const [message, setMesssage] = useState('');
    const [bidData, setBidData] = useState([]);

    useEffect(() => {

    }, [bidData])

    const showbidschema = Yup.object().shape({
        productID: Yup.string().required('Product ID is required')
    });

    const { register, handleSubmit, formState: { errors }, reset } = useForm({ resolver: yupResolver(showbidschema) });

    const columns = [
        { field: 'id', headerName: 'ID', width: 90 },
        { field: 'email', headerName: 'Buyer Email', width: 300 },
        { field: 'name', headerName: 'Buyer Name', width: 300 },
        { field: 'phone', headerName: 'Buyer Phone', width: 200 },
        { field: 'amount', headerName: 'Bid Amount', type: 'number', width: 110 }
    ];

    var rows = [];

    async function onSubmit(data) {
        rows = []
        const productID = data.productID;
        const SHOW_BIDS_URL = BASE_URL + '/e-auction/api/v1/seller/show-bids/' + productID;
        var response = await fetch(SHOW_BIDS_URL);
        var response_data = await response.json();
        for (var i = 0; i < response_data.length; i++) {
            rows.push({
                id: i + 1,
                email: response_data[i].buyer.email,
                name: response_data[i].buyer.firstName + ' ' + response_data[i].buyer.lastName,
                phone: response_data[i].buyer.phone,
                amount: response_data[i].bidAmount
            });
        }
        setBidData(rows);
        setHideBids(false);
        setMesssage("Bids for Product ID : " + productID);

        reset({ productID: null });
    }

    return (<>
        <div style={{ width: '60%' }}>

            <Typography variant="h6" align="center" color={'white'} margin="dense">Show Bids</Typography>
            <Grid margin container spacing={1}  >
                <Grid spacing={1} xs={5}>

                    <TextField required id="productID" name="productID" label="Product ID" fullWidth margin="dense" variant='outlined'
                        {...register('productID')} error={errors.productID ? true : false} />
                    <Typography variant="overline" color="textSecondary">{errors.productID?.message}</Typography>

                </Grid>
            </Grid>
            <Box mt={3} align='right' padding={1}>
                <Button variant="contained" color="primary" onClick={handleSubmit(onSubmit)}>Show Bids</Button>
            </Box>


        </div>
        <div hidden={hideBids} >
            <Grid>
                <Typography variant="h6" align="left" color={'white'} margin="dense">{message}</Typography>
                <Box padding={1} align='right'>
                    <DataGrid
                        rows={bidData}
                        columns={columns}
                        pageSize={5}
                        autoHeight
                        rowsPerPageOptions={[5]}
                        disableSelectionOnClick
                    />
                </Box>
            </Grid>
        </div>
    </>);
}
export default ShowBids;