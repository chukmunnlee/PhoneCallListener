
console.log("===== Loading PhoneCallListener.js ===");

var PhoneCallListener = function() { };

PhoneCallListener.prototype.status = function(val, success, failure) {

	var args = (val)? [val]: [];

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.enable: success callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.enable: failure callback must be a function");
		return;

	//Make the call
	cordova.exec(success, failure, "PhoneCallListener", "status", args);
}

PhoneCallListener._isFunction(f) {
	return ((null != f) && (typeof f == "function"));
}

//--------------------------------------
if (!window.plugins)
	window.plugins = {};

if (!window.plugins.phoneCallListener)
	window.plugins.phoneCallListener = new PhoneCallListener();

if (module.exports)
	module.exports = PhoneCallListener;
