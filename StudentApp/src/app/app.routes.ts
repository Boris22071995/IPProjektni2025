import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AuthGuard } from './guards/auth.guard';
import { DashboardHomeComponent } from './components/dashboard-home/dashboard-home.component';
import { CvCreateComponent } from './components/cv-create/cv-create.component';
import { InternshipsComponent } from './components/internships/internships.component';
import { AiRecommendationsComponent } from './components/ai-recommendations/ai-recommendations.component';
import { WorkLogComponent } from './components/work-log/work-log.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: DashboardHomeComponent },
      { path: 'cv', component: CvCreateComponent },
      { path: 'internships', component: InternshipsComponent },
      { path: 'ai', component: AiRecommendationsComponent },
      { path: 'work-log', component: WorkLogComponent }
    ]
  },
  { path: '**', redirectTo: 'login' }
];
