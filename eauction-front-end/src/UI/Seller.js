import { Grid, Tab, Tabs, Box, Typography,TextField } from "@mui/material";
import { useState } from "react";

const Seller = (props) => {

    const [tabIndex, setTabIndex] = useState(0);

    const handleTabChange = (event, newTabIndex) => {
        setTabIndex(newTabIndex);
    };

    return (
        <Grid xs={'auto'} margin>
            <Box>
                <Box>
                    <Tabs value={tabIndex} onChange={handleTabChange}>
                        <Tab label="Add Product" />
                        <Tab label="Delete Product" />
                        <Tab label="Show Bids" />
                    </Tabs>
                </Box>
                <Box sx={{ padding: 2 }}>
                    {tabIndex === 0 && (
                        <Box>                           
                            <TextField id="standard-basic" label="Email" variant="standard" required />
                        </Box>
                    )}
                    {tabIndex === 1 && (
                        <Box>
                            <Typography>The second tab</Typography>
                        </Box>
                    )}
                    {tabIndex === 2 && (
                        <Box>
                            <Typography>The third tab</Typography>
                        </Box>
                    )}
                </Box>
            </Box>

        </Grid>
    );
}

export default Seller