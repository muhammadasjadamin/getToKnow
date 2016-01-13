var mongoose = require('mongoose');
var Twit = require('twit');

var T = new Twit({
    consumer_key:         'lUEfgNKBwe4Mm5YowLtRG8l33', 
    consumer_secret:      'Fmo4BaodU1zSjqAITHjWlBkAJvsjLA9CkulpbXCLBONfKyJzEd', 
    access_token:         '3590486067-OZTgv8yC2i7Nq0zQWGbu8IC2j0NaJz2HVeL2599', 
    access_token_secret:  'gZ26KZuLutQM7lnPqSbqBxa4MtUTmD3uivGWya7nzXl8N'
})

var db = mongoose.connection;

db.on('error', console.error);
db.once('open', function() {
  // Create your schemas and models here.
});

mongoose.connect('mongodb://localhost/test');

var movieSchema = new mongoose.Schema({
  text: String, 
  location: String,
  Hashtag: { type: Object }
});

// Compile a 'Movie' model using the movieSchema as the structure.
// Mongoose also creates a MongoDB collection called 'Movies' for these documents.
//var Movie = mongoose.model('Movie', movieSchema);

var Sindh = mongoose.model('Sindh', movieSchema);

var sanFrancisco = [ '-122.75', '36.8', '-121.75', '37.8' ]
var sindh = ['62.9', '25.2','69.5', '28.1'];
var stream = T.stream('statuses/filter',{locations: sanFrancisco});
stream.on ('tweet', function(result){
	console.log('\n\n')
	console.log('-----------------------------------')
	console.log(result);
	console.log('-----------------------------------')
	console.log(result.text);
	console.log(result.user.location);
	console.log(result.entities.hashtags);
	console.log('-----------------------------------')
	
	/*usersRef.child('Pakistan').push({
		
			"data":{
				'text': result.text,
				'location': result.user.location,
				'Hashtags': result.entities.hashtags
			} 
	})*/

	var tweet = new Sindh({
		text: result.text,
		location: result.user.location,
	    Hashtag: result.entities.hashtags
	})
	tweet.save(function(err,tweet){
		if (err) 
  		{
  			console.log ("En error -------------- An Error")
  			return console.error(err); 
  		}		
  		console.log(tweet);
	})
})


/*var thor = new Movie({
  title: 'Thor'
, rating: 'PG-13'
, releaseYear: '2011'  // Notice the use of a String rather than a Number - Mongoose will automatically convert this for us.
, hasCreditCookie: true
});

thor.save(function(err, thor) {
  if (err) 
  	{
  		console.log ("En error -------------- An Error")
  		return console.error(err); }
  //console.dir(thor);
});*/

// Find a single movie by name.
/*Movie.findOne({ title: 'Thor' }, function(err, thor) {
  if (err) {
  	console.log ("En error -------------- An Error")
  	return console.error(err);
  }
  console.dir(thor);
});*/

/*Movie.find(function(err, movies) {
  if (err) return console.error(err);
  console.log(movies);
});*/



// Use the helper as a static function of the compiled Movie model.
/*Movie.findAllWithCreditCookies(function(err, movies) {
  if (err) return console.error(err);
  console.dir(movies);
});*/