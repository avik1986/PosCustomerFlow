import dayjs from 'dayjs';
import { IAddressDetails } from 'app/shared/model/address-details.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { SourceType } from 'app/shared/model/enumerations/source-type.model';

export interface IOrder {
  id?: number;
  sourceType?: keyof typeof SourceType | null;
  isActive?: boolean | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  selectedAddress?: IAddressDetails | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IOrder> = {
  isActive: false,
};
