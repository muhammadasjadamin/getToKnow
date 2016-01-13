var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/tweetsdata';


/*var insertDocument = function(db, callback) {
   db.collection('countries').insertOne({
   		"_id" : "America_ID",
   		"America": {

   			"Montana":["Helena"],
   			"Oregon": ["Salem"],
   			"California":["Sacramento"],
   			"Nevada": ["Carson City"],
   			"Washington":["Olympia"]
    
		}
	}, 
function(err, result) {
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

});*/

var cursor;
var findRestaurants = function(db, callback) {
   cursor =db.collection('countries').find( );
   //console.log(cursor.cursorState.cmd.query)
   cursor.each(function(err, doc) {
      assert.equal(err, null);
          
      if (doc != null) 
      {
         for (var key in doc){
           console.log (key)
         }
         console.log(doc);
      } 
      else {
         callback();
      }
   });


};

for (var key in cursor)
  console.log (cursor.key)

MongoClient.connect(url, function(err, db) {
  assert.equal(null, err);
  findRestaurants(db, function() {
      db.close();
  });
});

  
