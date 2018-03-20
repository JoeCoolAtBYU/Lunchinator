DROP KEYSPACE IF exists "lunch";

// for local installs
CREATE KEYSPACE "lunch"
WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

CREATE TYPE lunch.voter (
name text,
emailAddress text
);

CREATE TYPE lunch.ballotChoices (
suggestion text,
choices text
);

CREATE TABLE lunch.restaurantReview (
id int,
name text,
averageReview text,
topReviewer text,
review text,
Primary key (name)
);

CREATE TABLE lunch.ballotById (
ballotId UUID,
endTime text,
voters list < frozen< voter > >,
ballotChoices text,
PRIMARY KEY (ballotId)
);

CREATE TABLE lunch.restaurant (
id int,
name text,
waitTimeMinutes int,
types List < text >,
image text,
description text,
PRIMARY KEY (id, name)
);