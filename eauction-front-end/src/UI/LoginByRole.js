import { InputLabel, Select, MenuItem, Grid } from "@mui/material";
import { useState } from "react";
import Buyer from "./Buyer";
import Seller from "./Seller";

const LoginByRole = (props) => {

    const [role, setRole] = useState('Seller');

    var isSeller = role === 'Seller' ? true : false;

    const rolechangeHandler = (event) => {
        setRole(event.target.value);
        console.log(event.target.value);
    }

    return (
        <div>
            <Grid xs={'auto'} margin>
                <InputLabel id="role-select-label">Role</InputLabel>
                <Select
                    labelId="role-select-label"
                    id="role-select"
                    value={role}
                    label="Age"
                    onChange={rolechangeHandler}
                >
                    <MenuItem value={'Seller'}>Seller</MenuItem>
                    <MenuItem value={'Buyer'}>Buyer</MenuItem>
                </Select>
            </Grid>
            <div >
                {isSeller && <Seller />}
                {!isSeller && <Buyer />}
            </div>
        </div>
    );
}
export default LoginByRole;