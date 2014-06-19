var PhoneCallListener = function() { };

PhoneCallListener.prototype.enable = function(val, success, failure) {

	var args = (val)? [val]: [];

	if (null == failure)
		failure = function() { };

	if (!isFunction(success)) {
		console.log("phoneCallListener.enable: success callback must be a function");
		return;
	}

	if (!isFunction(failure)) {
		console.log("phoneCallListener.enable: failure callback must be a function");
		return;

	//Make the call
	cordova.exec(success, failure, args);
}

function isFunction(f) {
	return ((null != f) && (typeof f == "function"));
}

//--------------------------------------
if (!window.plugins)
	window.plugins = {};

if (!window.plugins.phoneCallListener)
	window.plugins.phoneCallListener = new PhoneCallListener();

if (module.exports)
	module.exports = PhoneCallListener;
