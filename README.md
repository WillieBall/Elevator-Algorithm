This is our elevator algorithm code. For this default example the building has 10 floors and the elevator will start
on floor 3, with pickups on floors 1, 3, 4, 6, 9, 10. The program will then output the starting location of the elevator,
number of floors it had to move (including floors to get back to starting location after there are no more requests), total number of floors the elevator had to stop 
at (exluding if there is a pickup on the starting floor), and the amount of people moved.

For our algorithm we excluded checking for capacity, it is in our sudo code to check for capacity, but it was hard to find a good way to implement
and show people getting off of the elevator.
   
  
To change the example you must change the "location" attribute for the elevator object.
and/or change the number of "floors" in the hashmap.


This Algorithm is custom made for this scenario using different methods that elevators use that I researched. Different building use different algorithms for their elevators based on their purpose, or building design.
