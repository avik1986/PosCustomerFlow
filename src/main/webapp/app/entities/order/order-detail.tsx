import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order.reducer';

export const OrderDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderEntity = useAppSelector(state => state.order.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderDetailsHeading">
          <Translate contentKey="posCustomerFlowApp.order.detail.title">Order</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orderEntity.id}</dd>
          <dt>
            <span id="sourceType">
              <Translate contentKey="posCustomerFlowApp.order.sourceType">Source Type</Translate>
            </span>
          </dt>
          <dd>{orderEntity.sourceType}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="posCustomerFlowApp.order.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{orderEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="posCustomerFlowApp.order.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{orderEntity.createdAt ? <TextFormat value={orderEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="posCustomerFlowApp.order.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{orderEntity.updatedAt ? <TextFormat value={orderEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="posCustomerFlowApp.order.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{orderEntity.createdBy}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="posCustomerFlowApp.order.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{orderEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="posCustomerFlowApp.order.selectedAddress">Selected Address</Translate>
          </dt>
          <dd>{orderEntity.selectedAddress ? orderEntity.selectedAddress.id : ''}</dd>
          <dt>
            <Translate contentKey="posCustomerFlowApp.order.customer">Customer</Translate>
          </dt>
          <dd>{orderEntity.customer ? orderEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/order" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order/${orderEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderDetail;
