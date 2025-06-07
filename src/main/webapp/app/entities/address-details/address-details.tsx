import React, { useEffect, useState } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, reset } from './address-details.reducer';

export const AddressDetails = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );
  const [sorting, setSorting] = useState(false);

  const addressDetailsList = useAppSelector(state => state.addressDetails.entities);
  const loading = useAppSelector(state => state.addressDetails.loading);
  const links = useAppSelector(state => state.addressDetails.links);
  const updateSuccess = useAppSelector(state => state.addressDetails.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="address-details-heading" data-cy="AddressDetailsHeading">
        <Translate contentKey="posCustomerFlowApp.addressDetails.home.title">Address Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="posCustomerFlowApp.addressDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/address-details/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="posCustomerFlowApp.addressDetails.home.createLabel">Create new Address Details</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={addressDetailsList ? addressDetailsList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {addressDetailsList && addressDetailsList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.id">ID</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                  </th>
                  <th className="hand" onClick={sort('addressDetailType')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.addressDetailType">Address Detail Type</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('addressDetailType')} />
                  </th>
                  <th className="hand" onClick={sort('mobileNo')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.mobileNo">Mobile No</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('mobileNo')} />
                  </th>
                  <th className="hand" onClick={sort('name')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.name">Name</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                  </th>
                  <th className="hand" onClick={sort('label')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.label">Label</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('label')} />
                  </th>
                  <th className="hand" onClick={sort('addressLine1')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.addressLine1">Address Line 1</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('addressLine1')} />
                  </th>
                  <th className="hand" onClick={sort('addressLine2')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.addressLine2">Address Line 2</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('addressLine2')} />
                  </th>
                  <th className="hand" onClick={sort('addressLine3')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.addressLine3">Address Line 3</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('addressLine3')} />
                  </th>
                  <th className="hand" onClick={sort('floorNo')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.floorNo">Floor No</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('floorNo')} />
                  </th>
                  <th className="hand" onClick={sort('pincode')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.pincode">Pincode</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('pincode')} />
                  </th>
                  <th className="hand" onClick={sort('area')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.area">Area</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('area')} />
                  </th>
                  <th className="hand" onClick={sort('city')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.city">City</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('city')} />
                  </th>
                  <th className="hand" onClick={sort('country')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.country">Country</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('country')} />
                  </th>
                  <th className="hand" onClick={sort('landmark')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.landmark">Landmark</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('landmark')} />
                  </th>
                  <th className="hand" onClick={sort('latitude')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.latitude">Latitude</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('latitude')} />
                  </th>
                  <th className="hand" onClick={sort('longitude')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.longitude">Longitude</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('longitude')} />
                  </th>
                  <th className="hand" onClick={sort('isActive')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.isActive">Is Active</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                  </th>
                  <th className="hand" onClick={sort('createdAt')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.createdAt">Created At</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                  </th>
                  <th className="hand" onClick={sort('updatedAt')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.updatedAt">Updated At</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.createdBy">Created By</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="posCustomerFlowApp.addressDetails.updatedBy">Updated By</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {addressDetailsList.map((addressDetails, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/address-details/${addressDetails.id}`} color="link" size="sm">
                        {addressDetails.id}
                      </Button>
                    </td>
                    <td>
                      <Translate contentKey={`posCustomerFlowApp.AddressType.${addressDetails.addressDetailType}`} />
                    </td>
                    <td>{addressDetails.mobileNo}</td>
                    <td>{addressDetails.name}</td>
                    <td>{addressDetails.label}</td>
                    <td>{addressDetails.addressLine1}</td>
                    <td>{addressDetails.addressLine2}</td>
                    <td>{addressDetails.addressLine3}</td>
                    <td>{addressDetails.floorNo}</td>
                    <td>{addressDetails.pincode}</td>
                    <td>{addressDetails.area}</td>
                    <td>{addressDetails.city}</td>
                    <td>{addressDetails.country}</td>
                    <td>{addressDetails.landmark}</td>
                    <td>{addressDetails.latitude}</td>
                    <td>{addressDetails.longitude}</td>
                    <td>{addressDetails.isActive ? 'true' : 'false'}</td>
                    <td>
                      {addressDetails.createdAt ? (
                        <TextFormat type="date" value={addressDetails.createdAt} format={APP_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      {addressDetails.updatedAt ? (
                        <TextFormat type="date" value={addressDetails.updatedAt} format={APP_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>{addressDetails.createdBy}</td>
                    <td>{addressDetails.updatedBy}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button
                          tag={Link}
                          to={`/address-details/${addressDetails.id}`}
                          color="info"
                          size="sm"
                          data-cy="entityDetailsButton"
                        >
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`/address-details/${addressDetails.id}/edit`}
                          color="primary"
                          size="sm"
                          data-cy="entityEditButton"
                        >
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button
                          onClick={() => (window.location.href = `/address-details/${addressDetails.id}/delete`)}
                          color="danger"
                          size="sm"
                          data-cy="entityDeleteButton"
                        >
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="posCustomerFlowApp.addressDetails.home.notFound">No Address Details found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default AddressDetails;
