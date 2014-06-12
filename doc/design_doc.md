# Design Doc Twisted Hangman #

## Database design ##

![user-db](/doc/user-table.png)
![word-db](/doc/words-table.png)
![statistics-db](/doc/Statistics-table.png)

Word\_length is opgenomen in de user database, omdat we een optionele multiplayer feature op het oog hebben en die zouden we nodig hebben om de spelers aan elkaar te matchen. Je moet immers hetzelfde woord raden en dat kan alleen maar als ze dezelfde lengte hebben.

***TODO***
User table difficulty moet integer zijn.


## Highscore
Highscore is determined by: <br> # of tries / # number of letters


## Classes ##

### User-class ###

#### functions ####

- getOptions(int user\_id)
- changeOptions(int id, string difficulty, string language, int word\_length, string name)

### Statistics-class ###

#### functions ####

- getStatistics(int user\_id)<br>
  return array(won, lost, played, difficulty)
- updateStatistics(int user\_id, string difficulty, string latest\_status)

### Game-class ###

#### functions ####

- initGame(user\_id)
- getWord(word\_id)<br>
  return string word
- drawScene()
- checkLetter(char letter, string word)<br>
  return boolean
- changeLetter(char letter, string status)
- updateHangman(int difficulty, int step)
- checkWord(string word, string guess)
- showCoffin()

### Good game class ###

- Consists of functions related to the normal hangman principle

### Evil game class ###

- Consists of functions related to the evil hangman principle



###Database classes

####Statistics CLass

- primitive

- setID
- getID
- setPLayed
- getPLayed
- setWon
- getWon
- setLost
- getLost
- setUserId
- getUserId

####User Class

- primitive

- setID
- getID
- setName
- getName
- setDifficulty
- getDifficulty
- setLanguage
- getLanguage
- setWordLength
- getWordLength

####handler class

- primitive

- onCreate
- onUpgrade
- fillWords
- getAllWords
- getAllUsers
- createUser
- getUserByName

###Activities

- Main Activity
  * Is the main screen and will contain the option and how to play view

- Singleplayer Activity
  * Is the game screen for singleplayer and will contain functions for drawing and changing the scene when needed.

- Statistics Activity
  * Is the screen in which the statistics are shown.

- ChooseUserActivity
  * is the screen where a user can be selected.

- HowToPlayActivity
  * Is the screen where all the necessairy information is shown.

- NewUserActivity
  * Is where a new User can be created.

- PreferenceActivity
  * Is where the options en preferences are shown.

## App design ##

![first-time-enter](/doc/image_design_doc/first_time_enter.png)
![home](/doc/image_design_doc/home.png)
![choose_user](/doc/image_design_doc/choose_user.png)
![singleplayer](/doc/image_design_doc/singleplayer.png)
![statistics](/doc/image_design_doc/statistics.png)
![options](/doc/image_design_doc/options.png)
![how_to_play_1](/doc/image_design_doc/how_to_play_1.png)
![how_to_play_2](/doc/image_design_doc/how_to_play_2.png)
![how_to_play_3](/doc/image_design_doc/how_to_play_3.png)
