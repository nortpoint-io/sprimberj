import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {
    return this.get('store').findRecord('customer', params.id);
  },
  actions: {
    error(error) {
      if (error) {
        return this.transitionTo('message', {queryParams: {message: error, status: error.errors[0].status}});
      }
  }
}
});
