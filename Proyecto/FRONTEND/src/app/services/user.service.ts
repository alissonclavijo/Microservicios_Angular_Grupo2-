import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { User } from '../models/user.model';

import { environment } from '../enviroments/enviroment';

@Injectable({
    providedIn: 'root',
})
export class UserService {

    constructor(private http: HttpClient) { }

    getUser(): Observable<User[]> {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }

    getUserById(id: number): Observable<User> {
        return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
    }

    createUser(user: User): Observable<User> {
        return this.http.post<User>(`${environment.apiUrl}/users`, user);
    }

    updateUser(user: User): Observable<User> {
        return this.http.put<User>(`${environment.apiUrl}/users/${user.id}`, user);
    }

    deleteUser(id: number): Observable<User> {
        return this.http.delete<User>(`${environment.apiUrl}/users/${id}`);
    }

}
