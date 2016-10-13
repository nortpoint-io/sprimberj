import DS from 'ember-data';
import Ember from 'ember';

export default DS.RESTSerializer.extend({
  normalizeResponse(store, primaryModelClass, hateoasPayload, id, requestType) {
    let restPayload = {};

    hateoasPayload.meta = {};
    hateoasPayload.meta.links = hateoasPayload.links;
    delete hateoasPayload.links;

    restPayload[primaryModelClass.modelName] = hateoasPayload;

    return this._super(store, primaryModelClass, restPayload, id, requestType);
  },
  
  serializeIntoHash(hash, typeClass, snapshot, options) {
    let opts = options || {};
    opts.includeId = true;
    Ember.merge(hash, this.serialize(snapshot, opts));
  }
});