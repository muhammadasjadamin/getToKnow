var mongoose = require('mongoose');

var date = new Date()
console.log(date);
//console.log(date.getDate())

console.log (date.getMonth())
console.log (date.getDate())
console.log (date.getYear())

var myDate = "D" + date.getYear() + ((date.getMonth())+1)  + date.getDate()
console.log(myDate)

var db = mongoose.connection;

db.on('error', console.error);
db.once('open', function() {
  // Create your schemas and models here.
});

mongoose.connect('mongodb://localhost/tweetsdata/karachi');

/*var movieSchema = new mongoose.Schema({
  text: String, 
  location: String,
  Hashtag: { type: Object }
});*/
var dateSchema = new mongoose.Schema({ myDate : { type: Object } });
console.log("Schema created")

var insertDate = mongoose.model(myDate,dateSchema);
console.log("Mode Created")


var testDate = new insertDate({ myDate : { "test": "succesfull test"}})
console.log("to be inseerted created")
testDate.save(function(err,testDate){
		if (err) 
  		{
  			console.log ("En error -------------- An Error")
  			return console.error(err); 
  		}		
  		console.log(testDate);
	})


//mongoose.connect('mongodb://localhost/tweetsdata/myCol/'+ myDate);

/*var mySchema = new mongoose.Schema({
  text: String, 
  location: String,
  Hashtag: { type: Object }
})
var Sindh = mongoose.model('Sindh', movieSchema);

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
	})*/

