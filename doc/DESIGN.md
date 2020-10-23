# Simulation Design Final
### Names Franklin Wu, Alex Chao, Joshua Boss

## Team Roles and Responsibilities

 * Franklin - model/backend

 * Alex/Josh - view


## Design goals

#### What Features are Easy to Add

Buttons

New Simulation (rules?)

Different file format (CSV)

New edge/neighborhood policy 

Adding different cell types for view 

## High-level Design

We wanted to make the model completely independent of the view and have the controller mediate the 
relationship between view and model so that view does not directly rely on model. 

Display runs the program and contains the controller, and the controller makes instances of the 
simulations and interacts with the backend depending on user input. 



#### Core Classes

Model: the simulation abstract class is the superclass for the different rules of different simulations.
The GameBoard class is the mediator between the simulation and the cells, it's a 2D array of cells that
works for all simulations. Similar to the simulation, there is a cell abstract class that is the parent
of all cells types for each simulation. The neighborhood class stores the neighbors of every class 
and is stored inside of each cell object. The reader classes read in from a CSV and provides the 
cell states to the gameboard. 

View: The display class is the main hub for view that sets up and launches the animations. It launches 
the SplashScreen object first that chooses a simulation, then launches a SimulationBoard. The SimulationBoard
creates and updates a grid of cells in the frontend depending on what is present in the GameBoard class.
SimulationBoard uses different type of CellViews such as rectangles or images to display different 
information to the user. The ButtonSetup class set up buttons based on what's in the properties file. 

## Assumptions that Affect the Design

We assumed that propertiesFiles would have limitations on the keys they had and where they were located
which affected how we handled error handling. 

## Significant differences from Original Plan


## New Features HowTo

Buttons: add in the name of the button in the properties file, and write the method in ButtonSetups
for what should happen when that button is clicked. 

New Simulation Type 
Make a subclass of Cell for states and Simulation for the rules. Make a properties file containing 
the information for this new type of simulation. 

Different file format (CSV)
Make another reader class that generates a 2D array of states based on the information in the CSV 

New edge/neighborhood policy 
Write the policy in the neighborhood class. 

Adding different cell types for view 
Make a CellView class that extends Group and implements CellView with the new type of Cell as a Node
inside the group 

#### Easy to Add Features


#### Other Features not yet Done

The Graph View is not done and has not been fully implemented within our design. 




* Alternatives

We are not sure how to implement MVC - like do we let the model and view directly communicate? What is the role of the model? 