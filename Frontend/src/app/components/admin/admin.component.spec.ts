import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminComponent } from './admin.component';
import { LeaveService } from '../../services/leave.service';
import { of, Observable, throwError } from 'rxjs';
import { Leave } from '../../models/leave.model';

describe('AdminComponent', () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;

  const mockLeaveApplications: Leave[] = [
    {
      id: 1,
      employeeId: 'string',
      employeeName: 'string',
      employeePhone: 'string',
      employeeEmail: 'string',
      managerEmail: 'string',
      fromDate: new Date(),
      toDate: new Date(),
      totalDays: 5,
      reason: 'string',
      status: 'APPROVED',
      isProcessed: false,
    },
    {
      id: 2,
      employeeId: 'string',
      employeeName: 'string',
      employeePhone: 'string',
      employeeEmail: 'string',
      managerEmail: 'string',
      fromDate: new Date(),
      toDate: new Date(),
      totalDays: 5,
      reason: 'string',
      status: 'REJECTED',
      isProcessed: false,
    },
  ];

  const mockLeaveService = {
    getAllLeaves: jest.fn(() => of(mockLeaveApplications)),
    cancelLeaveRequest: jest.fn(() => of({})),
    approveLeaveRequest: jest.fn(() => of({})),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminComponent],
      providers: [{ provide: LeaveService, useValue: mockLeaveService }],
    });

    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
  });

  beforeEach(() => {
    jest.spyOn(console, 'error').mockImplementation(() => { });
    jest.clearAllMocks();
  });

  describe('business', () => {
    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should fetch leave applications on ngOnInit', () => {
      component.ngOnInit();
      expect(mockLeaveService.getAllLeaves).toHaveBeenCalled();
      expect(component.leaveApplications).toEqual(mockLeaveApplications);
    });

    it('should cancel leave application', () => {
      const applicationToCancel = mockLeaveApplications[0];
      component.cancelLeave(applicationToCancel);
      expect(mockLeaveService.cancelLeaveRequest).toHaveBeenCalledWith(applicationToCancel.id);
      expect(mockLeaveService.getAllLeaves).toHaveBeenCalled();
    });

    it('should approve leave application', () => {
      const applicationToApprove = mockLeaveApplications[0];
      component.approveLeave(applicationToApprove);
      expect(mockLeaveService.approveLeaveRequest).toHaveBeenCalledWith(applicationToApprove.id);
      expect(mockLeaveService.getAllLeaves).toHaveBeenCalled();
    });

    it('should handle error when fetching leave applications on ngOnInit', () => {
      const errorResponse = 'Error fetching leave applications';
      mockLeaveService.getAllLeaves.mockReturnValue(throwError(errorResponse));
      component.ngOnInit();
      expect(component.leaveApplications).toEqual([]);
      expect(console.error).toHaveBeenCalledWith('Error fetching leave applications:', errorResponse);
    });

    it('should handle error when canceling leave application', () => {
      const applicationToCancel = mockLeaveApplications[0];
      const errorResponse = 'Error canceling leave application';
      mockLeaveService.cancelLeaveRequest.mockReturnValue(throwError(errorResponse));
      component.cancelLeave(applicationToCancel);
      expect(mockLeaveService.cancelLeaveRequest).toHaveBeenCalledWith(applicationToCancel.id);
      expect(mockLeaveService.getAllLeaves).not.toHaveBeenCalled();
      expect(console.error).toHaveBeenCalledWith('Error canceling leave application:', errorResponse);
    });

    it('should cancel leave application and handle error', () => {
      const applicationToCancel = mockLeaveApplications[0];
      const errorResponse = 'Error canceling leave application';
      mockLeaveService.cancelLeaveRequest.mockReturnValue(throwError(errorResponse));
      component.cancelLeave(applicationToCancel);
      expect(mockLeaveService.cancelLeaveRequest).toHaveBeenCalledWith(applicationToCancel.id);
      expect(mockLeaveService.getAllLeaves).not.toHaveBeenCalled();
      expect(console.error).toHaveBeenCalledWith('Error canceling leave application:', errorResponse);
    });

    it('should approve leave application and handle error', () => {
      const applicationToApprove = mockLeaveApplications[0];
      const errorResponse = 'Error approving leave application';
      mockLeaveService.approveLeaveRequest.mockReturnValue(throwError(errorResponse));
      component.approveLeave(applicationToApprove);
      expect(mockLeaveService.approveLeaveRequest).toHaveBeenCalledWith(applicationToApprove.id);
      expect(mockLeaveService.getAllLeaves).not.toHaveBeenCalled();
    });
  });
});
