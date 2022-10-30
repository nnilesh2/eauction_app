import './App.css';
import { Box, createTheme, ThemeProvider } from '@mui/material'
import LoginByRole from './UI/LoginByRole';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

function App() {
  return (
    <div className='App'>
      <ThemeProvider theme={darkTheme}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <LoginByRole />
        </Box>
      </ThemeProvider>
    </div>
  );
}

export default App;
