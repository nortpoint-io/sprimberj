import Ember from 'ember';

export default Ember.Controller.extend({
  queryParams: ['message', 'status'],
  message: null,
  status: null
});