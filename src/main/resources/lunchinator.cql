DROP KEYSPACE IF EXISTS "lunch";

// for local installs
CREATE KEYSPACE "lunch" WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TYPE lunch.voter (
name text,
emailAddress text
);

CREATE TYPE lunch.restraunt (
id int,
name text,
waitTimeMinutes int,
types List<text>,
image text,
description text,
votes int,
dateTime text,
averageReview text,
topReviewer text
);

CREATE TYPE lunch.review (
id int,
restaurant text,
reviewer text,
rating text,
review text,
reviewerImage text
);



CREATE TABLE lunch.ballotById (
ballotId UUID,
endTime text,
voters list<frozen<voter>>,
PRIMARY KEY (ballotId)
);