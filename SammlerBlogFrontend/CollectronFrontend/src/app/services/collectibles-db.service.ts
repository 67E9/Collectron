import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Collectible } from "../model/Collectible";

//TODO: switch to https, replace root path with variable

@Injectable({
  providedIn: 'root'
})
export class CollectiblesDBService {

  constructor(private httpClient: HttpClient ) { }

  getCollectibleById(id: number): Observable<Collectible> {
    return this.httpClient.get<Collectible>('http://localhost:8080/api/collectible/findById/' + id);
  }

  getCollectibleByName(name: string): Observable<Collectible[]> {
    return this.httpClient.get<Collectible[]>('http://localhost:8080/api/collectible/findByName/' + name);
  }

  getAllCollectible(): Observable<Collectible[]> {
    return this.httpClient.get<Collectible[]>('http://localhost:8080/api/collectible');
  }

  getCollectibleByType(type: string): Observable<Collectible[]> {
    return this.httpClient.get<Collectible[]>('http://localhost:8080/api/collectible/' + type);
  }

  getCollectibleByForSale(forSale: boolean): Observable<Collectible[]> {
    return this.httpClient.get<Collectible[]>('http://localhost:8080/api/collectible/forSale/' + forSale);
  }

  getCollectibleByNameAndDescription(keywords: string): Observable<Collectible[]> {
    return this.httpClient.get<Collectible[]>('http://localhost:8080/api/collectible/find_keywords/' + keywords);
  }

  getCollectibleByAllFilter(keywords: string): Observable<Collectible[]> {
    return this.httpClient.get<Collectible[]>('http://localhost:8080/api/collectible/findByAllFilter/' + keywords);
  }

  //the following methods are the manipulation services FE
  deleteCollectibleById(id: number): Observable<Collectible> {
    return this.httpClient.delete<Collectible>('http://localhost:8080/api/collectible/' + id);
  }

  updateCollectible(collectible: any) {
    return this.httpClient.put<Collectible>('http://localhost:8080/api/collectible', collectible);
  }

  newCollectible(collectible: any) {
    return this.httpClient.post<Collectible>('http://localhost:8080/api/collectible', collectible);
  }



}

//TODO: add Error handling!
