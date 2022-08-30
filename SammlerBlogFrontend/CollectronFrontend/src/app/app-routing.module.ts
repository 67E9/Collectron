import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {BrowseCollectionViewComponent} from "./component/browse-collection-view/browse-collection-view.component";
import {ManageCollectionViewComponent} from "./component/manage-collection-view/manage-collection-view.component";
import {CollectibleFormComponent} from "./component/collectible-form/collectible-form.component";
import {BlogPostFormComponent} from "./component/blog-post-form/blog-post-form.component";
import {CollectibleBlogComponent} from "./component/collectible-blog/collectible-blog.component";
import {MarketplaceComponent} from "./component/marketplace/marketplace.component";
import {MarketplaceFormComponent} from "./component/marketplace-form/marketplace-form.component";
import {ImprintComponent} from "./component/imprint/imprint.component";
import {TypeListComponent} from "./component/type-list/type-list.component";
import {TypeFormComponent} from "./component/type-form/type-form.component";
import {ResultsComponent} from "./component/results/results.component";
import {PagenotfoundComponent} from "./component/pagenotfound/pagenotfound.component";

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "collection", component: BrowseCollectionViewComponent},
  {path: "collection/:id", component: CollectibleBlogComponent},
  {path: "marketplace", component: MarketplaceComponent},
  {path: "marketplace/:id", component: MarketplaceFormComponent},
  {path: "manager", component: ManageCollectionViewComponent},
  {path: "manager/:id", component: ManageCollectionViewComponent},
  {path: "new_collectible", component: CollectibleFormComponent},
  {path: "edit_collectible/:id", component: CollectibleFormComponent},
  {path: "new_blog_post", component: BlogPostFormComponent},
  {path: "edit_blog_post/:id", component: BlogPostFormComponent},
  {path: "results", component: ResultsComponent},
  {path: "imprint", component: ImprintComponent},
  {path: "types", component: TypeListComponent},
  {path: "new_type", component: TypeFormComponent},
  {path: "edit_type/:id", component: TypeFormComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: '**', pathMatch: 'full', component: PagenotfoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
