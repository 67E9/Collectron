import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FilterService} from "../../services/filter.service";

@Component({
  selector: 'app-sidebar-left',
  templateUrl: './sidebar-left.component.html',
  styleUrls: ['./sidebar-left.component.css']
})
export class SidebarLeftComponent implements OnInit {

  @Output() newItemEvent = new EventEmitter<string>();

  constructor(public router: Router,
              private route: ActivatedRoute,
              public filterService: FilterService) { }

  ngOnInit(): void { }

  sendKeyword(value: string) {
    if (this.route.snapshot.url[0].path === 'results') {
      this.newItemEvent.emit(value);
    } else {
      this.router.navigate(['/results'], {state: {data: value}});

    }
  }
}
