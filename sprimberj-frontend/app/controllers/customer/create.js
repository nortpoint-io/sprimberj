import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    save() {
      let customer = this.model.customer;
      let self = this;
      customer.save().then(() => {
        this.transitionToRoute('message', {queryParams: {message: 'created'}});
      }).catch((e) => {
        self.set('model.errors', e.errors);
      });
    }
  }
});