import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'users',
        loadChildren: async () => (await import('./pages/user.routes')).routes,
    },
];
