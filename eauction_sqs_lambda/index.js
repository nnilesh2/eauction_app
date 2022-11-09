const fetch = require('node-fetch');
exports.handler = async (event) => {

    var records = event.Records;

    for (var i = 0; i < records.length; i++) {


        var record = event.Records[i];

        const BASE_URL = 'http://eauctionebs-env-2.eba-3kytec32.us-east-1.elasticbeanstalk.com';
        const BASE_URL_EMAIL = 'https://kzjbpinr64.execute-api.us-east-1.amazonaws.com/prod';

        const ADD_PRODUCT_URL = BASE_URL + '/e-auction/api/v1/seller/add-product'

        const options = {
            method: 'POST',
            rejectUnauthorized: false,
            headers: {
                'Content-Type': 'application/json'
            },
            body: record.body
        };
        var add_product_response = await fetch(ADD_PRODUCT_URL, options);
        var response_data = await add_product_response.json();


        const product_body = JSON.parse(record.body);
        console.log('Response Data :' + product_body.seller.email);

        if (add_product_response.ok) {

            const email_body = {
                data: JSON.stringify(event.body),
                emailsubject: 'Product Added Successfully, Product ID: ' + response_data.productID,
                email: product_body.seller.email
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
            const EMAIL_URL = BASE_URL_EMAIL + '/e-auction/api/v1/email'
            await fetch(EMAIL_URL, email_options);
        } else {
            const email_body = {
                data: JSON.stringify(product_body),
                emailsubject: 'Failed to add product with , Product ID: ' + product_body.productID,
                email: product_body.seller.email
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
            const EMAIL_URL = BASE_URL_EMAIL + '/e-auction/api/v1/email'
            await fetch(EMAIL_URL, email_options);
        }

    }


    const response = {
        statusCode: 200,
        headers: {
            "Access-Control-Allow-Headers": "*",
            "Access-Control-Allow-Methods": "*",
            "Access-Control-Allow-Origin": "*"
        },
        body: 'Produt added Successfully !',
        isBase64Encoded: false
    };
    return response;
};