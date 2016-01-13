/* var twitter = require ('twitter');

var client = new twitter({
  consumer_key: 'lUEfgNKBwe4Mm5YowLtRG8l33',
  consumer_secret: 'Fmo4BaodU1zSjqAITHjWlBkAJvsjLA9CkulpbXCLBONfKyJzEd',
  access_token_key: '3590486067-OZTgv8yC2i7Nq0zQWGbu8IC2j0NaJz2HVeL2599',
  access_token_secret: 'gZ26KZuLutQM7lnPqSbqBxa4MtUTmD3uivGWya7nzXl8N'
});

var params = {screen_name:'M_Asjad_Amin'};
client.get('statuses',params,function (error, tweets, response){ 
if (!error){
	console.log(error);
}
console.log(tweets);
});
*/

var twitterAPI = require('node-twitter-api');
var twitter = new twitterAPI({
    consumerKey: 'lUEfgNKBwe4Mm5YowLtRG8l33',
    consumerSecret: 'Fmo4BaodU1zSjqAITHjWlBkAJvsjLA9CkulpbXCLBONfKyJzEd',
});

twitter.getRequestToken(function(error, requestToken, requestTokenSecret, results){
    if (error) {
        console.log("Error getting OAuth request token : " + error);
    } else {
        console.log (results)
    }
});