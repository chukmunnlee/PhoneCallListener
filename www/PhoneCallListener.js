var PhoneCallListener = function() { };

PhoneCallListener.prototype.addPhoneNumber = function(val, success, failure) {

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.addPhoneNumber: isEnabled callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.addPhoneNumber: isEnabled callback must be a function");
		return;
	}

	//Make the call
	cordova.exec(success, failure, "PhoneCallHandlerPlugin", "addPhoneNumber", [val]);
}

PhoneCallListener.prototype.removePhoneNumber = function(val, success, failure) {

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.removePhoneNumber: success callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.removePhoneNumber: failure callback must be a function");
		return;
	}

	//Make the call
	cordova.exec(success, failure, "PhoneCallHandlerPlugin", "removePhoneNumber", [val]);
}

PhoneCallListener.prototype.getAllPhoneNumbers = function(success, failure) {

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.getAllPhoneNumbers: success callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.getAllPhoneNumbers: failure callback must be a function");
		return;
	}

	//Make the call
	cordova.exec(success, failure, "PhoneCallHandlerPlugin", "removePhoneNumber", []);
}

PhoneCallListener.prototype.isRegistered = function(success, failure) {

	if (null == failure)
		failure = function() { };

	if (!PhoneCallListener._isFunction(success)) {
		console.log("phoneCallListener.isRegistered: success callback must be a function");
		return;
	}

	if (!PhoneCallListener._isFunction(failure)) {
		console.log("phoneCallListener.isRegistered: failure callback must be a function");
		return;
	}

	//Make the call
	cordova.exec(success, failure, "PhoneCallHandlerPlugin", "removePhoneNumber", []);
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
