import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { IndexComponent } from './pages/index/index.component';
import { LogViewerComponent } from './pages/log-viewer/log-viewer.component';
import { RandomWordComponent } from './pages/random-word/random-word.component';
import { WotdComponent } from './pages/wotd/wotd.component';

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'wotd', component: WotdComponent },
  { path: 'random-word', component: RandomWordComponent },
  { path: 'logs', component: LogViewerComponent },
  { path: 'index', component: IndexComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
