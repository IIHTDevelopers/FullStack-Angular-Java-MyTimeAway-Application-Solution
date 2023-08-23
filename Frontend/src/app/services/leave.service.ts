import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Leave } from '../models/leave.model';

@Injectable({
    providedIn: 'root'
})
export class LeaveService {
    private baseUrl = 'http://127.0.0.1:8081/mytimeaway/api/leaves';

    constructor(private http: HttpClient) { }

    getAllLeaves(): Observable<Leave[]> {
        return this.http.get<Leave[]>(`${this.baseUrl}`);
    }

    getLeaveById(id: number): Observable<Leave> {
        return this.http.get<Leave>(`${this.baseUrl}/${id}`);
    }

    createLeave(leave: Leave): Observable<Leave> {
        return this.http.post<Leave>(`${this.baseUrl}`, leave);
    }

    updateLeaveById(id: number, leave: Leave): Observable<Leave> {
        return this.http.put<Leave>(`${this.baseUrl}/${id}`, leave);
    }

    deleteLeaveById(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }

    searchLeaves(employeeId?: string, employeeName?: string, totalDays: number = 0): Observable<Leave[]> {
        let params = new HttpParams();
        if (employeeId) {
            params = params.append('employeeId', employeeId);
        }
        if (employeeName) {
            params = params.append('employeeName', employeeName);
        }
        if (totalDays > 0) {
            params = params.append('totalDays', totalDays.toString());
        }
        return this.http.get<Leave[]>(`${this.baseUrl}/search`, { params });
    }

    cancelLeaveRequest(id: number): Observable<Leave> {
        return this.http.put<Leave>(`${this.baseUrl}/${id}/cancel`, {});
    }

    approveLeaveRequest(id: number): Observable<Leave> {
        return this.http.put<Leave>(`${this.baseUrl}/${id}/approve`, {});
    }

    rejectLeaveRequest(id: number): Observable<Leave> {
        return this.http.put<Leave>(`${this.baseUrl}/${id}/reject`, {});
    }
}
