import { Grid, Tab, Tabs, Box} from "@mui/material";
import { useState } from "react";
import AddProduct from "./AddProduct";
import DeleteProduct from "./DeleteProduct";
import ShowBids from "./ShowBids";

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
                               <AddProduct />
                            </Box>
                        )}
                        {tabIndex === 1 && (
                            <Box>
                               <DeleteProduct/>
                            </Box>
                        )}
                        {tabIndex === 2 && (
                            <Box>
                                <ShowBids/>
                            </Box>
                        )}
                    </Box>
                </Box>
        </Grid>
    );
}

export default Seller