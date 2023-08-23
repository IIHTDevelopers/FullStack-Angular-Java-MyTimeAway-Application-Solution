import { Component } from '@angular/core';
import { LeaveService } from '../../services/leave.service';
import { Leave } from '../../models/leave.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  leaveForm!: FormGroup;
  leave: Leave = {
    id: 0,
    employeeName: '',
    employeePhone: '',
    managerEmail: '',
    fromDate: new Date(),
    toDate: new Date(),
    totalDays: 0,
    reason: '',
    status: '',
    employeeId: '',
    employeeEmail: '',
    isProcessed: false
  };

  constructor(private fb: FormBuilder, private leaveService: LeaveService, private router: Router) {
    this.createForm();
  }

  createForm() {
    this.leaveForm = this.fb.group({
      id: [],
      employeeId: [],
      employeeName: ['', Validators.required],
      employeePhone: ['', Validators.required],
      managerEmail: ['', Validators.required],
      fromDate: ['', Validators.required],
      toDate: ['', Validators.required],
      totalDays: ['', Validators.required],
      reason: [this.leave.reason, Validators.required]
    });
  }

  submitForm() {
    this.leaveService.createLeave(this.leave).subscribe(
      (createdLeave) => {
        this.router.navigate(['/admin']);
      },
      (error) => {
        console.error('Error creating leave request:', error);
      }
    );
  }
}
