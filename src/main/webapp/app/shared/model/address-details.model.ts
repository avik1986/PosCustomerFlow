import dayjs from 'dayjs';
import { AddressType } from 'app/shared/model/enumerations/address-type.model';

export interface IAddressDetails {
  id?: number;
  addressDetailType?: keyof typeof AddressType | null;
  mobileNo?: string | null;
  name?: string | null;
  label?: string | null;
  addressLine1?: string | null;
  addressLine2?: string | null;
  addressLine3?: string | null;
  floorNo?: string | null;
  pincode?: number | null;
  area?: string | null;
  city?: string | null;
  country?: string | null;
  landmark?: string | null;
  latitude?: number | null;
  longitude?: number | null;
  isActive?: boolean | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
  createdBy?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IAddressDetails> = {
  isActive: false,
};
