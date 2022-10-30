import { Box, Grid, Tab, Tabs} from "@mui/material";
import { useState } from "react";
import PlaceBid from "./PlaceBid";
import UpdateBid from "./UpdateBid";

const Buyer = (props) => {

    const [tabIndex, setTabIndex] = useState(0);

    const handleTabChange = (event, newTabIndex) => {
        setTabIndex(newTabIndex);
    };

    return (
        <Grid xs={'auto'} margin>
            <Box>
                <Box>
                    <Tabs value={tabIndex} onChange={handleTabChange}>
                        <Tab label="Place Bid" />
                        <Tab label="Update Bid" />
                    </Tabs>
                </Box>
                <Box sx={{ padding: 2 }}>
                    {tabIndex === 0 && (
                        <Box>
                            <PlaceBid />
                        </Box>
                    )}
                    {tabIndex === 1 && (
                        <Box>
                            <UpdateBid/>
                        </Box>
                    )}
                </Box>
            </Box>
        </Grid>
    );
}
export default Buyer;