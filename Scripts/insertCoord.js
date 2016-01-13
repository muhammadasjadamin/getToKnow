var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/tweetsdata';

var insertDocument = function(db, callback) {
   db.collection('citycoords').insertOne( {

   	 "_id" : "Washington",
   	 "Washington" : {
   	 	"long1" : -123.949,
   	 	"lat1" : 45.950,
   	 	"long2" : -117.050,
   	 	"lat2" : 49.007
   	 }
      
   }, function(err, result) {
    assert.equal(err, null);
    console.log("Inserted a document into the restaurants collection.");
    callback(result);
  });
};


MongoClient.connect(url, function(err, db) {
  assert.equal(null, err);
  console.log("Connected correctly to server.");
  insertDocument(db,function(){
  	db.close();
  })

});