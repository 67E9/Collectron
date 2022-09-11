import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PasswordPair } from '../model/PasswordPair';

import { UserProfile } from '../model/UserProfile';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  //TODO: methods in this service are untested

  getUserDataOnLogin(): Observable<UserProfile>{
    return this.httpClient.get<UserProfile>('https://localhost:8080/api/user/login')
  }

  findAllUsers(): Observable<UserProfile[]>{
    return this.httpClient.get<UserProfile[]>('https://localhost:8080/api/user')
  }

  findUser(userName: string): Observable<UserProfile>{
    return this.httpClient.get<UserProfile>('https://localhost:8080/api/user/'+userName)
  }

  newUser(user: UserProfile): Observable<UserProfile>{
    return this.httpClient.post<UserProfile>('https://localhost:8080/api/user', user)
  }

  changePassword(passwords: PasswordPair): Observable<string>{
    return this.httpClient.put<string>('https://localhost:8080/api/user/password', passwords)
  }

  changeRole(username: string, newRole: string): Observable<string>{
    return this.httpClient.put<string>('https://localhost:8080/api/user/role' + username, newRole)
  }

  deleteUser(username: string): Observable<UserProfile>{
    return this.httpClient.delete<UserProfile>('https://localhost:8080/api/user'+ username)
  }

}

