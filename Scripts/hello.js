var Twitter = require('twitter');
// /var es = require('event-stream');

var client = new Twitter({
  consumerKey: 'lUEfgNKBwe4Mm5YowLtRG8l33',
      consumerSecret: 'Fmo4BaodU1zSjqAITHjWlBkAJvsjLA9CkulpbXCLBONfKyJzEd',
      access_token_key: '3590486067-OZTgv8yC2i7Nq0zQWGbu8IC2j0NaJz2HVeL2599',
      access_token_secret: 'gZ26KZuLutQM7lnPqSbqBxa4MtUTmD3uivGWya7nzXl8N'
});

//
// client.stream('statuses/filter', {follow: 20343405}, function(stream) {
//   stream.on('data', function(tweet) {
//     console.log("inside stream:",tweet);
//   });
//
//   stream.on('error', function(error) {
//     console.log(error);
//   });
// });

//
// client.stream('statuses/sample', function(stream) {
//   stream.on('data', function(tweet) {
//     console.log("inside stream:",tweet);
//   });
//
//   stream.on('error', function(error) {
//     console.log(error);
//   });
//
// });


// // Load your image
// var data = require('fs').readFileSync('/Users/desmondmorris/Desktop/twitter.png');
//
// // Make post request on media endpoint. Pass file data as media parameter
// client.post('media/upload', {media: data}, function(error, media, response){
//
//   if (!error) {
//
//     // If successful, a media object will be returned.
//     console.log(media);
//
//     var status = {
//       status: 'Testing media uploads for the npm twitter module.',
//       media_ids: media.media_id_string
//     }
//
//     client.post('statuses/update', status, function(error, tweet, response){
//
//       if (!error) {
//
//         console.log(tweet);
//
//       }
//
//     });
//
//   }
// });

client.stream('statuses/firehose', function(stream) {
  stream.on('data', function(tweet) {
    console.log(tweet.text);
    console.log("Get Success !!!");
  });

  stream.on('error', function(error) {
    console.log("Error in stream");
    console.log(""+error);
  });
});


// var stream = client.get('statuses/sample', {base: 'stream'});
//
// console.log(stream);
//
//
// var params = {
//   q: 'from:businessinsider filter:links soundcloud OR youtube'
// };
//
// client.get('search/tweets', params, function(err, data, res){
//   console.log(data);
//   console.log(err);
// });
