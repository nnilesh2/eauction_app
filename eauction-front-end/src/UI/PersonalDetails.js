import { Box, TextField } from "@mui/material";
import { useState } from "react";
import { useForm } from "react-hook-form";
const PersonalDetails = (props) => {

    const [formValues, setFormValues] = useState({
        email: {
            value: '',
            error: false,
            errorMessage: 'Please Enter Valid Eamil !'
        },
        firstName: {
            value: '',
            error: false,
            errorMessage: 'First Name Should Not Be Null !'
        },
        lastName: {
            l: '',
            error: false,
            errorMessage: 'Last Name Should Not Be Null !'
        },
        phone: {
            value: 'full-stack',
            error: false,
            errorMessages: ['Phone Number Should Not be Empty !', 'Phone Number Should be 10 Digits !']
        },
        addressLine: {
            value: 'full-stack'

        },
        city: {
            value: 'full-stack'
        },
        state: {
            value: 'full-stack'
        },
        pin: {
            value: 'full-stack'
        }

    })

    const handleChange = (event)=>{

    }

    return (
        <div>
           <Box width='50%'>
           <TextField 
            placeholder="abc@abc.com"
            label="Email"
            name="name"
            variant="standard"
            fullWidth
            required
            value={formValues.email.value}
            onChange={handleChange}
            error={formValues.email.error}
            helperText={formValues.email.error && formValues.email.errorMessage}
           
          />
        </Box>
        
        </div>
    );

}
export default PersonalDetails