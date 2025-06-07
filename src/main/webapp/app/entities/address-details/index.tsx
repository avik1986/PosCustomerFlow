import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AddressDetails from './address-details';
import AddressDetailsDetail from './address-details-detail';
import AddressDetailsUpdate from './address-details-update';
import AddressDetailsDeleteDialog from './address-details-delete-dialog';

const AddressDetailsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AddressDetails />} />
    <Route path="new" element={<AddressDetailsUpdate />} />
    <Route path=":id">
      <Route index element={<AddressDetailsDetail />} />
      <Route path="edit" element={<AddressDetailsUpdate />} />
      <Route path="delete" element={<AddressDetailsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AddressDetailsRoutes;
