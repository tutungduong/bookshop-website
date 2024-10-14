import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import 'dayjs/locale/vi';
import '@smastrom/react-rating/style.css';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';
import { ColorScheme, ColorSchemeProvider, MantineProvider } from '@mantine/core';
import { NotificationsProvider } from '@mantine/notifications';
import { ModalsProvider } from '@mantine/modals';
import AdminDashboard from 'pages/AdminDashboard';
import Admin from 'pages/Admin';
import AdminError from 'components/AdminError/AdminError';

const queryClient = new QueryClient();

function App() {
  const [colorScheme, setColorScheme] = useState<ColorScheme>('light');
  const toggleColorScheme = (value?: ColorScheme) =>
    setColorScheme(value || (colorScheme === 'dark' ? 'light' : 'dark'));

  return (
    <QueryClientProvider client={queryClient}>
      <ColorSchemeProvider colorScheme={colorScheme} toggleColorScheme={toggleColorScheme}>
        <MantineProvider theme={{ colorScheme }} withGlobalStyles withNormalizeCSS>
          <NotificationsProvider>
            <ModalsProvider>
              <Routes>
                <Route path='/admin' element={<Admin/>}>
                  <Route path="/admin/*" element={<AdminError/>}/>
                  <Route index element={<AdminDashboard/>}/>
                </Route>
              </Routes>
            </ModalsProvider>
          </NotificationsProvider>
        </MantineProvider>
      </ColorSchemeProvider>
      <ReactQueryDevtools initialIsOpen={false}/>
    </QueryClientProvider>
  );
}


export default App;
