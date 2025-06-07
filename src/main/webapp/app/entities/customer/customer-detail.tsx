import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CustomerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customerEntity = useAppSelector(state => state.customer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customerDetailsHeading">
          <Translate contentKey="posCustomerFlowApp.customer.detail.title">Customer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="fname">
              <Translate contentKey="posCustomerFlowApp.customer.fname">Fname</Translate>
            </span>
          </dt>
          <dd>{customerEntity.fname}</dd>
          <dt>
            <span id="lname">
              <Translate contentKey="posCustomerFlowApp.customer.lname">Lname</Translate>
            </span>
          </dt>
          <dd>{customerEntity.lname}</dd>
          <dt>
            <span id="mobileNo">
              <Translate contentKey="posCustomerFlowApp.customer.mobileNo">Mobile No</Translate>
            </span>
          </dt>
          <dd>{customerEntity.mobileNo}</dd>
          <dt>
            <span id="emailId">
              <Translate contentKey="posCustomerFlowApp.customer.emailId">Email Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.emailId}</dd>
          <dt>
            <span id="rposCustomerId">
              <Translate contentKey="posCustomerFlowApp.customer.rposCustomerId">Rpos Customer Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.rposCustomerId}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="posCustomerFlowApp.customer.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{customerEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="posCustomerFlowApp.customer.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{customerEntity.createdAt ? <TextFormat value={customerEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="posCustomerFlowApp.customer.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{customerEntity.updatedAt ? <TextFormat value={customerEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="posCustomerFlowApp.customer.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{customerEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="posCustomerFlowApp.customer.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{customerEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
