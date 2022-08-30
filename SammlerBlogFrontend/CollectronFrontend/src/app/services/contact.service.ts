import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private api = 'https://mailthis.to/collectibleRequest'

  constructor(private http: HttpClient) {
  }

  result: string = '';

  requestMessage(input: any) {
    return this.http.post(this.api, input, {responseType: 'text'}).pipe(
      map((response: string) => {
        if (response) {
          this.result = response;
        }},
        (error: any) => {
          this.result = error;
        }))
  }
}
