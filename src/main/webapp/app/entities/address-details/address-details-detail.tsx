import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './address-details.reducer';

export const AddressDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const addressDetailsEntity = useAppSelector(state => state.addressDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="addressDetailsDetailsHeading">
          <Translate contentKey="posCustomerFlowApp.addressDetails.detail.title">AddressDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.id}</dd>
          <dt>
            <span id="addressDetailType">
              <Translate contentKey="posCustomerFlowApp.addressDetails.addressDetailType">Address Detail Type</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.addressDetailType}</dd>
          <dt>
            <span id="mobileNo">
              <Translate contentKey="posCustomerFlowApp.addressDetails.mobileNo">Mobile No</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.mobileNo}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="posCustomerFlowApp.addressDetails.name">Name</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.name}</dd>
          <dt>
            <span id="label">
              <Translate contentKey="posCustomerFlowApp.addressDetails.label">Label</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.label}</dd>
          <dt>
            <span id="addressLine1">
              <Translate contentKey="posCustomerFlowApp.addressDetails.addressLine1">Address Line 1</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.addressLine1}</dd>
          <dt>
            <span id="addressLine2">
              <Translate contentKey="posCustomerFlowApp.addressDetails.addressLine2">Address Line 2</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.addressLine2}</dd>
          <dt>
            <span id="addressLine3">
              <Translate contentKey="posCustomerFlowApp.addressDetails.addressLine3">Address Line 3</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.addressLine3}</dd>
          <dt>
            <span id="floorNo">
              <Translate contentKey="posCustomerFlowApp.addressDetails.floorNo">Floor No</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.floorNo}</dd>
          <dt>
            <span id="pincode">
              <Translate contentKey="posCustomerFlowApp.addressDetails.pincode">Pincode</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.pincode}</dd>
          <dt>
            <span id="area">
              <Translate contentKey="posCustomerFlowApp.addressDetails.area">Area</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.area}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="posCustomerFlowApp.addressDetails.city">City</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.city}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="posCustomerFlowApp.addressDetails.country">Country</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.country}</dd>
          <dt>
            <span id="landmark">
              <Translate contentKey="posCustomerFlowApp.addressDetails.landmark">Landmark</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.landmark}</dd>
          <dt>
            <span id="latitude">
              <Translate contentKey="posCustomerFlowApp.addressDetails.latitude">Latitude</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.latitude}</dd>
          <dt>
            <span id="longitude">
              <Translate contentKey="posCustomerFlowApp.addressDetails.longitude">Longitude</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.longitude}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="posCustomerFlowApp.addressDetails.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="posCustomerFlowApp.addressDetails.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {addressDetailsEntity.createdAt ? (
              <TextFormat value={addressDetailsEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="posCustomerFlowApp.addressDetails.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {addressDetailsEntity.updatedAt ? (
              <TextFormat value={addressDetailsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="posCustomerFlowApp.addressDetails.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="posCustomerFlowApp.addressDetails.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{addressDetailsEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/address-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/address-details/${addressDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AddressDetailsDetail;
