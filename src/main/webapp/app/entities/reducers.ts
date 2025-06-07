import customer from 'app/entities/customer/customer.reducer';
import addressDetails from 'app/entities/address-details/address-details.reducer';
import order from 'app/entities/order/order.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  customer,
  addressDetails,
  order,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
