import dayjs from 'dayjs';

export interface ICustomer {
  id?: number;
  fname?: string | null;
  lname?: string | null;
  mobileNo?: string;
  emailId?: string | null;
  rposCustomerId?: string | null;
  isActive?: boolean | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  createdBy?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<ICustomer> = {
  isActive: false,
};
