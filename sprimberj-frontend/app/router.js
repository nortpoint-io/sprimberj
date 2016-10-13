import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('customer', function() {
    this.route('show', { path: '/show/:id' });
    this.route('create');
  });
  this.route('message');
});

export default Router;
