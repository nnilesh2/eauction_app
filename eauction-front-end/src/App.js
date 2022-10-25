import './App.css';
import { Box} from '@mui/material'
import LoginByRole from './UI/LoginByRole';

function App() {
  return (
    <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <LoginByRole/>
    </Box>
  );
}

export default App;
