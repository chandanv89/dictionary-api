import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchResultComponent } from './search-result.component';
const routes: Routes = [
    { path: 'search', component: SearchResultComponent },
    { path: 'search/:word', component: SearchResultComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class SearchResultRoutingModule { }
