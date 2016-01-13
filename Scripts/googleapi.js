var latitude = 24.825;
var longitude = 67.056;

var geocoder = require ('geocoder');

geocoder.reverseGeocode(latitude,longitude, function(err,data){
	console.log("I Am here")
console.log(data);
})