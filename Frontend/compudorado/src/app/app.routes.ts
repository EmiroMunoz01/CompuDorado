import { Routes } from '@angular/router';
import { HomeComponent } from 'pages/home/home.component';


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'productos', loadComponent: () => import('./pages/productos/productos.component').then(c => c.ProductosComponent) },
  { path: '**', redirectTo: '' }
];
