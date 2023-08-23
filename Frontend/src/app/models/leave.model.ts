export interface Leave {
    id: number;
    employeeId: string;
    employeeName: string;
    employeePhone: string;
    employeeEmail: string;
    managerEmail: string;
    fromDate: Date;
    toDate: Date;
    totalDays: number;
    reason: string;
    status: string;
    isProcessed: boolean;
}
