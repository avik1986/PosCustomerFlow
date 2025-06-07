import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { AddressType } from 'app/shared/model/enumerations/address-type.model';
import { createEntity, getEntity, updateEntity } from './address-details.reducer';

export const AddressDetailsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customers = useAppSelector(state => state.customer.entities);
  const addressDetailsEntity = useAppSelector(state => state.addressDetails.entity);
  const loading = useAppSelector(state => state.addressDetails.loading);
  const updating = useAppSelector(state => state.addressDetails.updating);
  const updateSuccess = useAppSelector(state => state.addressDetails.updateSuccess);
  const addressTypeValues = Object.keys(AddressType);

  const handleClose = () => {
    navigate('/address-details');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getCustomers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.pincode !== undefined && typeof values.pincode !== 'number') {
      values.pincode = Number(values.pincode);
    }
    if (values.latitude !== undefined && typeof values.latitude !== 'number') {
      values.latitude = Number(values.latitude);
    }
    if (values.longitude !== undefined && typeof values.longitude !== 'number') {
      values.longitude = Number(values.longitude);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...addressDetailsEntity,
      ...values,
      customer: customers.find(it => it.id.toString() === values.customer?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          addressDetailType: 'Billing',
          ...addressDetailsEntity,
          createdAt: convertDateTimeFromServer(addressDetailsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(addressDetailsEntity.updatedAt),
          customer: addressDetailsEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="posCustomerFlowApp.addressDetails.home.createOrEditLabel" data-cy="AddressDetailsCreateUpdateHeading">
            <Translate contentKey="posCustomerFlowApp.addressDetails.home.createOrEditLabel">Create or edit a AddressDetails</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="address-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.addressDetailType')}
                id="address-details-addressDetailType"
                name="addressDetailType"
                data-cy="addressDetailType"
                type="select"
              >
                {addressTypeValues.map(addressType => (
                  <option value={addressType} key={addressType}>
                    {translate(`posCustomerFlowApp.AddressType.${addressType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.mobileNo')}
                id="address-details-mobileNo"
                name="mobileNo"
                data-cy="mobileNo"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.name')}
                id="address-details-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.label')}
                id="address-details-label"
                name="label"
                data-cy="label"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.addressLine1')}
                id="address-details-addressLine1"
                name="addressLine1"
                data-cy="addressLine1"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.addressLine2')}
                id="address-details-addressLine2"
                name="addressLine2"
                data-cy="addressLine2"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.addressLine3')}
                id="address-details-addressLine3"
                name="addressLine3"
                data-cy="addressLine3"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.floorNo')}
                id="address-details-floorNo"
                name="floorNo"
                data-cy="floorNo"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.pincode')}
                id="address-details-pincode"
                name="pincode"
                data-cy="pincode"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.area')}
                id="address-details-area"
                name="area"
                data-cy="area"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.city')}
                id="address-details-city"
                name="city"
                data-cy="city"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.country')}
                id="address-details-country"
                name="country"
                data-cy="country"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.landmark')}
                id="address-details-landmark"
                name="landmark"
                data-cy="landmark"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.latitude')}
                id="address-details-latitude"
                name="latitude"
                data-cy="latitude"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.longitude')}
                id="address-details-longitude"
                name="longitude"
                data-cy="longitude"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.isActive')}
                id="address-details-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.createdAt')}
                id="address-details-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.updatedAt')}
                id="address-details-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.createdBy')}
                id="address-details-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('posCustomerFlowApp.addressDetails.updatedBy')}
                id="address-details-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="address-details-customer"
                name="customer"
                data-cy="customer"
                label={translate('posCustomerFlowApp.addressDetails.customer')}
                type="select"
              >
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/address-details" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AddressDetailsUpdate;
