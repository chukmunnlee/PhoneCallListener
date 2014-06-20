
console.log("===== Loading PhoneCallListener.js ===");

var PhoneCallListener = function() { };

PhoneCallListener.prototype.isEnabled = function(success, failure) {

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.enable: isEnabled callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.enable: isEnabled callback must be a function");
		return;
	}

	//Make the call
	cordova.exec(success, failure, "PhoneCallHandlerPlugin", "isEnabled", []);
}

PhoneCallListener.prototype.enableCallIntercept = function(val, success, failure) {

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.enableCallIntercept: success callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.enableCallIntercept: failure callback must be a function");
		return;
	}

	//Make the call
	cordova.exec(success, failure, "PhoneCallHandlerPlugin", "enableCallIntercept", [val]);
}

PhoneCallListener._isFunction = function(f) {
	return ((null != f) && (typeof f == "function"));
}

//--------------------------------------
if (!window.plugins)
	window.plugins = {};

if (!window.plugins.phoneCallListener)
	window.plugins.phoneCallListener = new PhoneCallListener();

if (module.exports)
	module.exports = PhoneCallListener;
