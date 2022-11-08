var aws = require("aws-sdk");
var ses = new aws.SES({ region: "us-east-1" });
exports.handler = async (event) => {

    const event_body = JSON.parse(event.body);

    var params = {
        Destination: {
            ToAddresses: ['nnilesh2@gmail.com', event_body.email],
        },
        Message: {
            Body: {
                Text: { Data: JSON.stringify(event_body) },
            },

            Subject: { Data: '' + event_body.emailsubject },
        },
        Source: "nnilesh2@gmail.com",
    };

    await ses.sendEmail(params).promise()

    const response = {
        statusCode: 200,
        headers: {
            "Access-Control-Allow-Headers": "*",
            "Access-Control-Allow-Methods": "*",
            "Access-Control-Allow-Origin": "*"
        },
        body: "Email sent successfully !",
        isBase64Encoded: false
    }

    return response;
};
