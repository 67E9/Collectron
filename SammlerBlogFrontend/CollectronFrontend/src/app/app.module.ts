import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { SidebarLeftComponent } from './component/sidebar-left/sidebar-left.component';
import { BlogPostsListComponent } from './component/blog-posts-list/blog-posts-list.component';
import { BrowseCollectionListComponent } from './component/browse-collection-list/browse-collection-list.component';
import { BrowseCollectionViewComponent } from './component/browse-collection-view/browse-collection-view.component';
import { ManageCollectionViewComponent } from './component/manage-collection-view/manage-collection-view.component';
import { SidebarRightMarketplaceComponent } from './component/sidebar-right-marketplace/sidebar-right-marketplace.component';
import {HttpClientModule} from "@angular/common/http";
import { CollectibleFormComponent } from './component/collectible-form/collectible-form.component';
import { BlogPostFormComponent } from './component/blog-post-form/blog-post-form.component';
import { CollectibleBlogComponent } from './component/collectible-blog/collectible-blog.component';
import { MarketplaceComponent } from './component/marketplace/marketplace.component';
import { MarketplaceFormComponent } from './component/marketplace-form/marketplace-form.component';
import { ImprintComponent } from './component/imprint/imprint.component';
import { RandomOfferComponent } from './component/random-offer/random-offer.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TypeListComponent } from './component/type-list/type-list.component';
import { TypeFormComponent } from './component/type-form/type-form.component';
import { SidebarRightManagerComponent } from './component/sidebar-right-manager/sidebar-right-manager.component';
import { ResultsComponent } from './component/results/results.component';
import {FilterPipe} from "./services/filter.pipe";
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SidebarLeftComponent,
    BlogPostsListComponent,
    BrowseCollectionListComponent,
    BrowseCollectionViewComponent,
    ManageCollectionViewComponent,
    SidebarRightMarketplaceComponent,
    CollectibleFormComponent,
    BlogPostFormComponent,
    CollectibleBlogComponent,
    MarketplaceComponent,
    MarketplaceFormComponent,
    ImprintComponent,
    RandomOfferComponent,
    TypeListComponent,
    TypeFormComponent,
    SidebarRightManagerComponent,
    ResultsComponent,
    FilterPipe,
    PagenotfoundComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
