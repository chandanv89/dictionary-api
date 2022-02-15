# Dictionary Service
Rest endpoints for a dictionary app.

## Try this now!
The service is deployed on Heroku and you can test it right away!
Since it's deployed using free-tier, the application may be sleeping/stopped. Please be patient while the app is started again - it'll take a few seconds ;)

[Dictionary Service on Heroku](https://dictionary-service.herokuapp.com/)

## Endpoints Exposed
1. `GET` `/rest/v1/dictionary/words` _Gets a pagenated list of words._
1. `GET` `/rest/v1/dictionary/words/{word}` _Gets a single word and it's definition._
1. `GET` `/rest/v1/dictionary/word-of-the-day` _Gets the word of the day._
1. `GET` `/rest/v1/dictionary/random-word` _Gets a random word and it's definition._
