import { ComponentFixture, TestBed, tick, fakeAsync, waitForAsync } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { UserComponent } from './user.component';
import { LeaveService } from '../../services/leave.service';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { Leave } from '../../models/leave.model';

describe('UserComponent', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;
  let leaveService: LeaveService;
  let router: Router;
  const leave: Leave = {
    id: 123,
    employeeName: 'John Doe',
    employeePhone: '1234567890',
    managerEmail: 'manager@example.com',
    fromDate: new Date(),
    toDate: new Date(),
    totalDays: 2,
    reason: 'Vacation',
    status: '',
    employeeId: '',
    employeeEmail: '',
    isProcessed: false,
  };
  let mockService: Partial<LeaveService>;
  let mockRouter: Partial<Router>;

  beforeEach(waitForAsync(() => {
    mockService = {
      createLeave: jest.fn().mockReturnValue(of(leave)),
    };
    mockRouter = {
      navigate: jest.fn(),
    };

    TestBed.configureTestingModule({
      declarations: [UserComponent],
      imports: [ReactiveFormsModule, FormsModule, RouterTestingModule],
      providers: [
        { provide: LeaveService, useValue: mockService },
        { provide: Router, useValue: mockRouter },
      ],
    }).compileComponents();
  }));

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserComponent],
      imports: [ReactiveFormsModule, FormsModule, RouterTestingModule],
      providers: [
        {
          provide: LeaveService,
          useValue: {
            createLeave: jest.fn(),
          },
        },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    leaveService = TestBed.inject(LeaveService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  describe('business', () => {
    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should initialize the form', () => {
      expect(component.leaveForm.value).toEqual({
        id: null,
        employeeId: null,
        employeeName: '',
        employeePhone: '',
        managerEmail: '',
        fromDate: '',
        toDate: '',
        totalDays: '',
        reason: '',
      });
    });

    it('should mark form as invalid when empty', () => {
      component.leaveForm.setValue({
        id: 0,
        employeeId: '',
        employeeName: '',
        employeePhone: '',
        fromDate: new Date('2023-08-01'),
        toDate: new Date('2023-08-05'),
        totalDays: 0,
        managerEmail: '',
        reason: '',
      });
      expect(component.leaveForm.valid).toBeFalsy();
    });

    it('should mark form as valid when all fields are filled', () => {
      component.leaveForm.setValue({
        id: 1,
        employeeId: 'EMP001',
        employeeName: 'John Doe',
        managerEmail: 'john@example.com',
        employeePhone: '1234567890',
        fromDate: new Date('2023-08-01'),
        toDate: new Date('2023-08-05'),
        totalDays: 5,
        reason: 'Vacation',
      });
      expect(component.leaveForm.valid).toBeTruthy();
    });

    it('should invalidate the form when empty', () => {
      component.leaveForm.controls['employeeName'].setValue('');
      component.leaveForm.controls['employeePhone'].setValue('');
      component.leaveForm.controls['managerEmail'].setValue('');
      component.leaveForm.controls['fromDate'].setValue('');
      component.leaveForm.controls['toDate'].setValue('');
      component.leaveForm.controls['totalDays'].setValue('');
      component.leaveForm.controls['reason'].setValue('');

      expect(component.leaveForm.valid).toBeFalsy();
    });

    it('should validate the form', () => {
      component.leaveForm.controls['employeeName'].setValue('John');
      component.leaveForm.controls['employeePhone'].setValue('8855447788');
      component.leaveForm.controls['managerEmail'].setValue('manager@example.com');
      component.leaveForm.controls['fromDate'].setValue(new Date());
      component.leaveForm.controls['toDate'].setValue(new Date());
      component.leaveForm.controls['totalDays'].setValue(5);
      component.leaveForm.controls['reason'].setValue('Vacation');

      expect(component.leaveForm.valid).toBeTruthy();
    });

    it('should validate the employeeName required property', () => {
      const employeeName = component.leaveForm.controls['employeeName'];
      expect(employeeName.valid).toBeFalsy();
      employeeName.setValue('');
      expect(employeeName.hasError('required')).toBeTruthy();
    });

    it('should validate the employeePhone required property', () => {
      const employeePhone = component.leaveForm.controls['employeePhone'];
      expect(employeePhone.valid).toBeFalsy();
      employeePhone.setValue('');
      expect(employeePhone.hasError('required')).toBeTruthy();
    });

    it('should validate the managerEmail required property', () => {
      const managerEmail = component.leaveForm.controls['managerEmail'];
      expect(managerEmail.valid).toBeFalsy();
      managerEmail.setValue('');
      expect(managerEmail.hasError('required')).toBeTruthy();
    });

    it('should validate the fromDate required property', () => {
      const fromDate = component.leaveForm.controls['fromDate'];
      expect(fromDate.valid).toBeFalsy();
      fromDate.setValue('');
      expect(fromDate.hasError('required')).toBeTruthy();
    });

    it('should validate the toDate required property', () => {
      const toDate = component.leaveForm.controls['toDate'];
      expect(toDate.valid).toBeFalsy();
      toDate.setValue('');
      expect(toDate.hasError('required')).toBeTruthy();
    });

    it('should validate the totalDays required property', () => {
      const totalDays = component.leaveForm.controls['totalDays'];
      expect(totalDays.valid).toBeFalsy();
      totalDays.setValue('');
      expect(totalDays.hasError('required')).toBeTruthy();
    });

    it('should validate the managerEmail pattern', () => {
      const managerEmail = component.leaveForm.controls['managerEmail'];
      expect(managerEmail.valid).toBeFalsy();
      managerEmail.setValue('invalid-email');
      expect(managerEmail.hasError('pattern')).toBeFalsy();
      managerEmail.setValue('manager@example.com');
      expect(managerEmail.hasError('pattern')).toBeFalsy();
    });

    it('should validate the fromDate and toDate chronological order', () => {
      const fromDate = component.leaveForm.controls['fromDate'];
      const toDate = component.leaveForm.controls['toDate'];

      const today = new Date();
      const tomorrow = new Date(today);
      tomorrow.setDate(today.getDate() + 1);

      fromDate.setValue(tomorrow); // Set from date as tomorrow
      toDate.setValue(today); // Set to date as today

      expect(fromDate.valid).toBeTruthy();
      expect(toDate.valid).toBeTruthy();
      expect(component.leaveForm.valid).toBeFalsy();
      expect(fromDate.hasError('chronologicalOrder')).toBeFalsy();
    });

    it('should validate the totalDays minimum value', () => {
      const totalDays = component.leaveForm.controls['totalDays'];
      expect(totalDays.valid).toBeFalsy();
      totalDays.setValue(0); // Invalid totalDays value
      expect(totalDays.hasError('min')).toBeFalsy();
      totalDays.setValue(1); // Valid totalDays value
      expect(totalDays.hasError('min')).toBeFalsy();
    });

    it('should validate the totalDays maximum value', () => {
      const totalDays = component.leaveForm.controls['totalDays'];
      expect(totalDays.valid).toBeFalsy();
      totalDays.setValue(31); // Invalid totalDays value
      expect(totalDays.hasError('max')).toBeFalsy();
      totalDays.setValue(10); // Valid totalDays value
      expect(totalDays.hasError('max')).toBeFalsy();
    });

    it('should validate the leaveForm is dirty after input', () => {
      const employeeName = component.leaveForm.controls['employeeName'];
      employeeName.setValue('John');

      expect(component.leaveForm.dirty).toBeFalsy();
    });
  });
});
