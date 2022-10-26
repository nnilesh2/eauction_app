import './App.css';
import { Box} from '@mui/material'
import LoginByRole from './UI/LoginByRole';
import { bgcolor } from '@mui/system';

function App() {
  return (
    <div className='App'>
    <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <LoginByRole/>
    </Box>
    </div>
  );
}

export default App;
