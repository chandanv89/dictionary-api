import { MessagingService } from './../../core/services/messaging.service';
import { DictionaryService } from './../../core/services/dictionary.service';
import { CommonModule } from '@angular/common';
import { NgModule } from "@angular/core";
import { SearchResultRoutingModule } from './search-result-routing.module';
import { SearchResultComponent } from "./search-result.component";


@NgModule({
    declarations: [
        SearchResultComponent
    ],
    imports: [
        CommonModule,
        SearchResultRoutingModule
    ],
    providers: [DictionaryService, MessagingService]
})
export class SearchResultModule {

}