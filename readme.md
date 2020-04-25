# Swagger UI URL
http://{server:port}/swagger-ui.html

# Lunchinator 3000 challenge requirements
You have been asked to implement the back-end service for Lunchinator 3000, a new software application that answers the question "Where to for lunch?" Each day, Lunchinator 3000 chooses a list of suggested restaurants from your lunch group's restaurant list, collects and tallies the votes, and then decides where you are going to eat. No more standing on the sidewalk talking about where to go for lunch!

As the Software Engineer for this project, you must develop a working service that implements all of the requirements below. You may choose any current technology: Java, NodeJS, PHP, etc.

We understand that your time is valuable, so please do not feel you need to spend more than 5 - 7 hours on this project. We are looking for a sample of your code.

Focus on the following areas to help us evaluate your code:

    Functionality (Does your code work correctly?)
    Code structure and style (Is your code DRY? How complex is your code? Is your code readable?)
    Documentation / self-documenting code (Can another developer understand how your code works?)
    Error handling (What happens when an error occurs?)
    Testing (Does your code include unit tests?)

Restaurant data

We will be providing you the restaurant and review data from our interview test API. Follow this link to view our API and documentation.
Restaurant voting

The user interface will be built by the front-end team using Hot New JavaScript Framework 1.0. Users will cast their votes for restaurants, which the application will pass to your service. By default, voting should end at 11:45 AM, but this time can be modified by an API call (below). When the voting period ends, the service tallies the votes to determine where to go for lunch.
POST /api/create-ballot

To start the voting process you must first create a ballot. To create a ballot POST JSON to your API that includes the voting closing time and a list of voters that includes email addresses and names. The response will include GUID as ballotId that will be used in the voting process.
Example request for POST /api/create-ballot

POST /api/create-ballot

POST BODY
```
{
    "endTime":"03/20/2017 11:45", 
    "voters":[
        {
           "name":"Bob",
           "emailAddress": "bob@gmail.com"
        },
        {
           "name":"Jim",
           "emailAddress": "jim@gmail.com"
        }
    ]

}
```
Example response for POST /api/create-ballot
```
{
    "ballotId":"1b9be815-60cf-444b-83a3-6da322684183"
}
```
GET /api/ballot/{ballotId}

The service randomly selects a list of no more than five suggested restaurants for lunch. Each ballot request must return the same list of restaurants with the restaurants listed in a random order. A new ballot should vary from previous ballots.

If the voting deadline has not passed, the results will include a suggestion that is based on the highest rated restaurant and restaurant choices (See example below). The suggestion will include the restaurant Id, name, average review rating, top reviewer name, and review. For the top review, select the first highest rated review for the suggestion restaurant. The choices will be an array of objects that include restaurant id, name, average review rating, and description.

If the voting deadline has passed, the server will display the results of the ballot including the winner and the choices with their respective vote totals. It should display in the format below for after the voting deadline has passed.
Example JSON response for GET /api/ballot/{ballotId} BEFORE voting deadline has passed
```
{
suggestion:{
    "id": 16, 
    "name": "DP Cheesesteak", 
    "averageReview": "5", 
    "TopReviewer":"Heather", 
    "Review":"erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse ac metus vitae velit egestas lacinia. Sed congue, elit sed consequat auctor, nunc nulla vulputate dui, nec tempus mauris erat eget ipsum. Suspendisse sagittis"
    }
choices:
    [
      { 
        "id": 4,
        "name":"Chick-Fil-A", 
        "averageReview": "3", 
        "description":"Home of the original chicken sandwich with two pickles on a toasted butter bun since 1964. We also offer many healthy alternatives to typical fast food."
      },
      { "id": 16, "name": "DP Cheesesteak", "averageReview": "5", "description":"DP Cheesesteak - Utah's Cheesesteak Champ" },
      { "id": 6, "name": "Arby's", "averageReview": "2","description":"Arby's sandwich shops are known for slow roasted roast beef, turkey, and premium Angus beef sandwiches, sliced fresh every day." },
      { "id": 2, "name": "Jimmy John's", "averageReview": "4", "description":"Jimmy John’s Gourmet Sandwiches – We Deliver" },
      { "id": 10, "name": "Popeye's", "averageReview": "3","description":"Popeyes Louisiana Kitchen shows off its New Orleans heritage with authentic spicy & mild fried chicken, chicken tenders, seafood and signature sides." }
    ]
}
```
Example JSON response for GET /api/ballot/{ballotId} AFTER voting deadline has passed
```
{
winner:{
     "id": 16,
     "datetime":"April 22, 2017 12:00PM",
     "name": "DP Cheesesteak",
     "votes":3  
      }
choices:
    [
      { "id": 4, "name": "Chick-Fil-A", "votes": 1},
      { "id": 16, "name": "DP Cheesesteak", "votes": 3 },
      { "id": 6, "name": "Arby's", "votes": 0 },
      { "id": 2, "name": "Jimmy John's", "votes": 2},
      { "id": 10, "name": "Popeye's", "votes": 1}
    ]
}
```
POST /api/vote

A user can cast their vote for where to eat for lunch. The request must include the restaurant Id, ballotId, voterName, and emailAddress as query string parameters. This request does not return any data. This vote will be recorded in server memory with the ballotId and emailAddress as a composite key. You are welcome to persist the data in a database if you would like but, it is not required.

If a user votes twice, the user's first vote is discarded and replaced with the later vote. The server must return a 409 response if the voting deadline has passed.

Example request for POST /api/vote

```
POST /api/vote?id=15&ballotId=1b9be815-60cf-444b-83a3-6da322684183&voterName=Bob&emailAddress=bob@gmail.com
```

Stretch goals

If you are looking for a little bigger challenge, you can optionally implement one or both of the following features.
Email the Ballot Link and Results

Utilizing an email API (e.g. gmail API - https://developers.google.com/gmail/api/guides/), send a "ballot link" to all voters when a new ballot is created. When the ballot has expired, email the results to the voters.
Picky Eaters

Persist the votes and ballots in a database and ensure that for the same group of voters, the winning restaurants are not presented again as options within the same week. After all, variety is the spice of life!