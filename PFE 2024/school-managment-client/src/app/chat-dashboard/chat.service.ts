import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";

const BASIC_URL =  environment["http://localhost:8080/api/chat/"]

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient) { }


  getAllMessages(): Observable<[]> {
    return this.http.get<[]>(`${BASIC_URL}/messages`);
  }

  sendMessage(message: any): Observable<any> {
    return this.http.post<[]>(`${BASIC_URL}/send`, message);
  }
}
