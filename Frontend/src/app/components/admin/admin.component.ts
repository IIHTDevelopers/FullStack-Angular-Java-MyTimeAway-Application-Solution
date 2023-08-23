import { Component, OnInit } from '@angular/core';
import { LeaveService } from '../../services/leave.service';
import { Leave } from '../../models/leave.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  leaveApplications: Leave[] = [];

  constructor(private leaveService: LeaveService) { }

  ngOnInit() {
    this.fetchLeaveApplications();
  }

  fetchLeaveApplications() {
    this.leaveService.getAllLeaves().subscribe(
      (leaves) => {
        this.leaveApplications = leaves;
      },
      (error) => {
        console.error('Error fetching leave applications:', error);
      }
    );
  }

  cancelLeave(application: Leave) {
    this.leaveService.cancelLeaveRequest(application.id).subscribe(
      () => {
        this.fetchLeaveApplications();
      },
      (error) => {
        console.error('Error canceling leave application:', error);
      }
    );
  }

  approveLeave(application: Leave) {
    this.leaveService.approveLeaveRequest(application.id).subscribe(
      () => {
        this.fetchLeaveApplications();
      },
      (error) => {
        console.error('Error canceling leave application:', error);
      }
    );
  }
}
