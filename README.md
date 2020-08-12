# Legacy-of-Brynjolf - Abhilash Gupta

A. Startup / Test -> 
	1. Import project on IDE.
	2.  a. IDE ->   
			1. Right click on LegacyOfBrynjolfApplication.java
			2. Run as a SpringBoot Project. 
			3. Add input moves in Run Configurations.
		b. Command Line (Run as JAR) -> 
			1. Build Project using maven.
			2. Run jar file -> java -jar legacy-of-brynjolf-1.0.0.jar INPUT
		
B. Changing Room -> 
	1. Update the room.txt file present in src/main/resources/input/room.txt ,
	2. (Not Required) Or you can also use RoomConstantDaoImpl.java which can allow you to pass List<String> to represent a room,
		instead of room.txt file. Add @Primary on top of RoomConstantDaoImpl.java and remove from RoomTextFileDaoImpl.java
	
C. Default Configurations ->
	1. Room -> See room.txt file on classpath. Can be changed in NameMappingConstants.java
		a. 		(  .  ) represents Empty Space
		b. 		(  g  ) represents Guards
		c. 		(  b  ) represents Brynjolf
		d. 		(  o  ) represents Exit
		e. 		(  x  ) represents Wall
	2. Room Size -> Max Size is 10. Can be changed in ApplicationConstants.java
	3. Lookahead Length -> Current is 30. Used for generating all permutations of defined length for computing solution.
		Can be changed in MovesGeneratorServiceImpl.java

D. Program Flow ->
	1. LegacyOfBrynjolfRunner.java -> called by main method on application startup. Command line input is passed.
	2. InitializeRoomServiceImpl.java -> initializes room, and validates matrix.
	3. RoomTextFileDaoImpl.java -> reads text file from classpath.
	4. MovesExecutionServiceImpl.java -> executes user input and if there is no conclusion, calls another service to find a solution.
	5. ModifyRoomServiceImpl.java -> modifies the matrix and moves the actors in a single direction.
	6. MovesGeneratorServiceImpl.java -> generates permutations for possible paths.	
	
E. Working -> 
	When user enters a string of moves, each character is read and actors are moved accordingly. First Brynjolf is moved
	one cell at a time in a given direction over empty spaces until one of these conditions occur -> 
		1. He encounters a guard. This means user has lost the game.
		2. He encounters an exit. This means user has won the game.
		3. He encounters a wall. He is stopped on the previous cell.
		4. He encounters edge of room. He is stopped on the previous cell.
	After this all the guards (one by one) are moved one cell at a time in a given direction until ->
		1. Guard encounters Brynjolf. Game is lost.
		2. Guard encounters a wall, an exit, or edge of room. Guard is stopped on the previous cell.
		3. Another guard is encountered. In this case, the next guard is moved, and then the first guard is moved.
	Count is maintained after every move in a direction, whether there is change in the matrix or not. If there is no change for 
	4 moves, game is UNDECIDED.
	
	After all user moves are executed and the game has not concluded(no win, lose or undecided), permutations are generated. 
	First all permutations of length 1 are generated (u,l,d,r). These are executed one by one in a parallel way. If any of these results in a win, 
	we return that the game has been won. If there are no wins, next length of permutations are generated on the moves which have not resulted in anything.
	This thing continues until there is a win. If there is no path to victory, we return that user is STUCK.