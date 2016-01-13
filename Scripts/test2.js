var Firebase = require ('firebase');

var ref = new Firebase("https://myfyp.firebaseio.com");
var usersRef = ref.child("Data");
/*var Twit = require ('twi);
	
var	T = new Twit({
		consumerKey: 'lUEfgNKBwe4Mm5YowLtRG8l33',
    	consumerSecret: 'Fmo4BaodU1zSjqAITHjWlBkAJvsjLA9CkulpbXCLBONfKyJzEd',
    	access_token_key: '3590486067-OZTgv8yC2i7Nq0zQWGbu8IC2j0NaJz2HVeL2599',
	   	access_token_secret: 'gZ26KZuLutQM7lnPqSbqBxa4MtUTmD3uivGWya7nzXl8N'
	});
var count = 0;
/*var util = require ('util');
twitter.stream ('statuses/filter',{track:'love'},function(stream){
	stream.on('data',function(data){
		console.log(util.inspect(data));
		console.log('i am ghere');
		stream.desttoy();
		process.exit(0);
	});
}
T.get('search/tweets', { q: 'banana since:2011-11-11', count: 100 }, function(err, data, response) {
  console.log(data)
})*/


var Twit = require('twit');

var T = new Twit({
    consumer_key:         'lUEfgNKBwe4Mm5YowLtRG8l33', 
    consumer_secret:      'Fmo4BaodU1zSjqAITHjWlBkAJvsjLA9CkulpbXCLBONfKyJzEd', 
    access_token:         '3590486067-OZTgv8yC2i7Nq0zQWGbu8IC2j0NaJz2HVeL2599', 
    access_token_secret:  'gZ26KZuLutQM7lnPqSbqBxa4MtUTmD3uivGWya7nzXl8N'
})

//
//  tweet 'hello world!'
//

//
//  search twitter for all tweets containing the word 'banana' since Nov. 11, 2011
//
70.7,32.1,71.7,33.1
62.9,25.2,73.3,35.2
var sanFrancisco = [ '-122.75', '36.8', '-121.75', '37.8' ]
var Pakistan = ['62.9','25.2' , '73.3', '35.2' ]
var sindh = ['62.9', '25.2','69.5', '28.1'];
var stream = T.stream('statuses/filter',{locations: sindh});
stream.on ('tweet', function(result){
	console.log('\n\n')
	console.log('-----------------------------------')
	console.log(result);
	console.log('-----------------------------------')
	console.log(result.text);
	console.log(result.user.location);
	console.log(result.entities.hashtags);
	console.log('-----------------------------------')
	checkForEmojin(result.text);
	usersRef.child('fullTweets').push({
		
			"data":{
				result;
			}
	})
	usersRef.child('Pakistan').push({
		
			"data":{
				'text': result.text,
				'location': result.user.location,
				'Hashtags': result.entities.hashtags
			} 
	})
})

var  Emojins = [ ':)', ':(' , '-.-', ';)', ';(', ':@', ':p', '<3' ];
function checkForEmojin (text){

	for (var emojin in Emojins)
		if ( text.indexOf(emojin) > -1)
		{
			usersRef.child('Emojins').push({
			"data":{
				'emojin': Emojins[emojin],
				} 
			});
		}

}


/*T.stream(
            'statuses/filter',
            {track: 'Love'},
            function (stream) {
                stream.on('data', function (data,err) {
                    if(err){
                        console.log('no tweets');
                    }
                    if(data){
                       //stream.destroy();
                       // process.exit(0);
                       console.log(data);
                    }
                    //console.log(data.user.screen_name + " : " + data.text);
                    //io.sockets.emit('newTwitt', data);
                    // throw  new Exception('end');
                });

            });*/
