import { SearchResultModule } from './pages/search-result/search-result.module';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HeaderComponent } from './pages/header/header.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { WotdComponent } from './pages/wotd/wotd.component';
import { SearchResultComponent } from './pages/search-result/search-result.component';
import { LogViewerComponent } from './pages/log-viewer/log-viewer.component';
import { RandomWordComponent } from './pages/random-word/random-word.component';
import { IndexComponent } from './pages/index/index.component';


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    HeaderComponent,
    FooterComponent,
    WotdComponent,
    LogViewerComponent,
    RandomWordComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    SearchResultModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
