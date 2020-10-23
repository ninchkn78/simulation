simulation
====

This project implements a cellular automata simulator.

Names: Joshua Boss, Franklin Wu, Alex Chao

### Timeline 

Start Date: September 29th

Finish Date: October 20th

Hours Spent: 150 Hours total = around 50 hours each probably

### Primary Roles
Franklin - Backend
Josh - FrontEnd
Alex - Mostly Front end but also helped on Backend - test lead

### Resources Used

https://stackoverflow.com/questions/25409044/javafx-multiple-buttons-to-same-handler

### Running the Program

Main class: The main class is our Display class. Run Display to run our simulation.

Data files needed: You need everything that is in BoardConfig and Everything in Properties files. You also

need the images inside the resources folder if you want to add images to cell states.

Features implemented:

All but finishing graph view and multiple styles 

1. Play Button
2. Pause Button
3. Step Button
4. Load File button
5. Save File Button
6. Image adder
7. Multiple languages
8. Multiple windows
9. Speed up, slow down bar
10. Splash Screen with default properties files
11. User can click on states to set them
12. Six different simulations
13. Different CSV formats
14. Neighbor and edge policies
15. Read in from a properties file
16. Choose color and image of state 

Optional Feature: properties file contains images and colors for states and kind of grid to use




### Notes/Assumptions

## Properties Files

Required Keys: Title, Description, Author, GameType, CellType, States, NeighborPolicy, EdgePolicy,
CSVSource

If a key is missing or an invalid file is read in, an error message is displayed on the screen. 

For View Cells: 

Color of state is formatted with 0, 1, 2 etc 
Image for state is formatted with 0image, 1image, 2image, etc

Keys for these are not required, and the default if missing is white for color, and default.png for image  

CSVSource format is name of file,type where supported types right now are set, random, and count 

Every CSV has the first line of its dimensions, row by column. 

#ReaderTypes (set,random,count)
For set, each state is represented by its string in a grid format. If the dimensions do not match up
with the grid, an error message is displayed on the screen. 

For random, each line is a state along with its proportion of occurences 
For count, each line is a state along with the number of cells there are of each state

If a state is given in the CSV but not in the properties file, an error message is displayed on the screen.


##Assumptions or Simplifications

1. for analyasis - states versus cells - we should have used cells but were in too deep with states (strings)


2. The only files that are valid when read in are inside of properties files directory.

3. For error handling, if they load a bad file, the game is stopped and we ~yell at them

4. We can't read the graph button - It's all the way on the right...

5. csv files to be read in from provided properties files are stored in board_config, and clicking 
save makes them inside of GAME_CSVS

Interesting data files:

Known Bugs:

1. As of right now, if you click the save file button, and exit out of the window, it still saves a file with the entered
information. I need to fix this but documenting for now.


2. When you exit out of LoadFile the program crashes... basically exiting out of anything where you choose
a file gives an error.

3. We are failing a couple of our tests - on the notaproperties file test, missing proeprties file test, stateColorChangeTest
the behavior works as intended when running the project, but the tests don't. We weren't sure whether
to comment this out or not, so we left them as failing. 


4. Graph generates but hitting play causes an error for the graph. Also we did not have time to 
implement testing for the graph, so our line coverage is 0% for that class. 


### Impressions

Overall we had a good time working on this project. It was definitely difficult and we all learned a lot through
the process. We spent a lot of time planning our code before we started, and a ton of time
on zoom calls together. We all improved our design and utilized a lot of reflection and resource property files
to practice best design. We split up into roles fairly naturally, and did a good job at all pulling our own weight.
That said, this was a difficult project for sure, and we spent tons of hours on this project.
