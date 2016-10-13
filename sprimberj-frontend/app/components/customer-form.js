import Ember from 'ember';

export default Ember.Component.extend({
  
  isValid() {
    return this.get('isFullNameValid');
  },
  
  isFullNameValid: Ember.computed('customer.fullName', function() {
    return this.get('customer.fullName') && this.get('customer.fullName').length;
  }),
  
  isPhoneValid: Ember.computed('customer.phone', function() {
    return this.get('customer.phone') && this.get('customer.phone').length;
  }),
  
  actions: {
    save() {
      if (this.isValid()) {
        this.get('onSave')();
    }
  }
  }  
      
});