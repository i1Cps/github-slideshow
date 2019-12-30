Welcome to Dugeon of Doom(DoD).

DoD is played on a rectangular grid, which serves as the game's board. You will control a Player tile which can move and pick up gold.
To win the game you must collect the required gold and then exit the dungeon. There is a bot player who acts like a brainless zombie,
the brainless zombie's moves are random, unpredictable and he always moves first. If the brainless zombie touches you (is on the same tile as you) you lose
the game. Every turn you send a command and if the command is successful, an action takes place.

To Start input the file path of your Map Folder in the main method!

Possible player actions:

"HELLO"           - This will display the total amount of gold required for your player to win.

"GOLD"            - This will display your players current gold owned.

"MOVE <direction> - This will move your player tile in a direction (E = East, S = South, W = West and N = North). Players cannot move
                    into walls and if you try to, game will reply with "UNSUCCESSFUL" else if your move is valid it will reply with
                    "SUCCESSFUL". 
                  
"PICKUP"          - This will pick up 1 gold if you are on a gold tile. If you are not on a gold tile the game will reply with UNSUCCESSFUL
                    (Once you pick up a gold on a gold tile, the gold tile becomes a normal tile)

"LOOK"            - This will display a 5x5 grid showing the map around your player, with your player at the CENTER.

"QUIT"            - Depending on whether your player has met the requirements to exit (which are to have the required gold for the map and be on a exit tile)
		    if you have met the requirements you will win the game, however if you haven't you will exit and lose the game 
                    with your players progress being deleted

If you try type in a unknown command the game will reply with "INVALID". All commands take up a players turn, regardless of whether they were SUCCESSFUL or not.



BOARD TILES:

"P" - Player

"B" - Bot

"." - Empty tile

"G" - Gold

"E" - Exit

"#" - Wall 

Implentation

The Main method starts the code and immediately finds a start location for the player and the bot (by randomely generating numbers in a certain range),
making sure they dont start on gold tiles or walls. The player IS allowed to spawn on a exit tile. It then checks for input.

when a player looks i created a seperate 5x5map attribute which gets player location in terms of index and based on player relative position 
in the normal map the code takes a index values in a 5x5 raidus of the players location and loads them into the 5x5 map whilst including 
try catch conditions so that when player is right next to a border the 5x5 maps shows extra walls '#'.

I set up my code so that depending on the direction of the move command the code would change the char variable in the next element North, East,
South or West to "P" and change the char variable in the element it just occupied back to "." which would represent a empty tile.
However i quickly ran into a problem in the event that a player went onto a gold tile but didnt pick it up then moved onto a new tile,
When this would happen the Gold tile would be replaced with a empty tile making it appear as if there was one less gold tile. 
To fix this i created attributes that would contain the location of special tiles.

I created attribute for the x location and y location of all the special attribute. I done this so i could easily create condidtions such as 
whether a move would be SUCCESSFUL or not, Or when a player would use the "PICKUP" command, i would simply use a getter for player x and y
location use another getter for gold tiles and check if the players x and y location would match any of the gold tiles x and y location
to see if the move would be SUCCESSFUL if not move would be UNSUCCESSFUL. Also it meant i could create  attributes which would be set to a 
certain number which would represent if a gold tile was successfully picked up meaning if this attribute still suggested that the gold hadn't 
been picked up after the player move it would reset that gold tile.

The bot is programmed to be a mindless zombie which aimlessly walks around the map avoiding gold and exit tiles, to make things less easy the
bot moves first so in a situation where the player is right next to the bot since the bot move first theres a chance player will be caught.

Map Creation

If you would like to craete your own map
