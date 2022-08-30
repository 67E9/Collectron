import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Type} from "../model/Type";

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private httpClient: HttpClient ) { }

  getTypeById(id: number): Observable<Type> {
    return this.httpClient.get<Type>('http://localhost:8080/api/type/findById/' + id);
  }

  getTypeByName(name: string): Observable<Type[]> {
    return this.httpClient.get<Type[]>('http://localhost:8080/api/type/findByName/' + name);
  }

  getAllType(): Observable<Type[]> {
    return this.httpClient.get<Type[]>('http://localhost:8080/api/type');
  }

  getTypeByNameAndDescription(keywords: string): Observable<Type[]> {
    return this.httpClient.get<Type[]>('http://localhost:8080/api/type/findByKey/' + keywords);
  }

  //the following methods are the manipulation services FE
  deleteTypeById(id: number): Observable<Type> {
    return this.httpClient.delete<Type>('http://localhost:8080/api/type/' + id);
  }

  updateType(type: any) {
    return this.httpClient.put<Type>('http://localhost:8080/api/type', type);
  }

  newType(type: any) {
    return this.httpClient.post<Type>('http://localhost:8080/api/type', type);
  }

}

//TODO: add Error handling!
