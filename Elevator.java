/**
 * 
 * @authors Willie Ball, Michael Hershberg
 * 
 * This is our elevator algorithm code. For this default example the  building has 10 floors and the elevator will start
 * on floor 3, with pickups on floors 1, 3, 4, 6, 9, 10. The program will then output the starting location of the elevator,
 * number of floors it had to move (including floors to get back to starting location after there are no more requests), total number of floors the elevator had to stop 
 * at (exluding if there is a pickup on the starting floor), and the amount of people moved.
 * 
 * For our algorithm we excluded checking for capacity, it is in our sudo code to check for capacity, but it was hard to find a good way to implement
 * and show people getting off of the elevator.
 * 
 * 
 * To change the example you must change the "location" attribute for the elevator object.
 * and/or change the number of "floors" in the hashmap.
 * 
 */

import java.util.*;

public class Elevator {

    /**
     * This is were the capacity attribues would be if they were implemented
     */
    //int MAXCAPACITY;
    //int currentCapacity;

    // which direction the elevator is going, null until it is decided by conditionals
    String direction = null;


    /***************************/
    // change the elevator starting floor here as desired
    // default is starting on floor 3
    // elevator goes back to this location after there are no more requests
    int location = 3;

    // no parameter constructor
    Elevator(){

    }
    

    public static void main(String[] args) {
        // total number of floors moved
        int floorsTraversed = 0;
        // number of floors that had to be stopped at
        int numberOfStops = 0;
        // amount of people moved (kind of useless since not including capacity but included anyways)
        int peopleMoved = 0; 

        // Map of floors and the people on each floor
        // in this example there will be 10 floors
        // the map will be set up with the floors as the key
        // and an arraylist of string with the order; 1. # of people waiting on each floor, 2. if the floor is a requested floor ('true' / 'false'), 3. if the floor is a destination floor ('true'/ 'false')
        Map<Integer, ArrayList<String>> floors = new LinkedHashMap<>();

        // this is the hash map representation of a floor
        // if you want to add more floors then add another representation of a floor
        // if you want less floors than take away representations of floors

        // floor 1
        floors.put(1, new ArrayList<String>());
        floors.get(1).add("10");
        floors.get(1).add("true");
        

        // floor 2
        floors.put(2, new ArrayList<String>());
        floors.get(2).add("0");
        floors.get(2).add("false");
        

        // floor 3
        floors.put(3, new ArrayList<String>());
        floors.get(3).add("10");
        floors.get(3).add("true");
        
        
        // floor 4
        floors.put(4, new ArrayList<String>());
        floors.get(4).add("10");
        floors.get(4).add("true");
        

        //floor 5
        floors.put(5, new ArrayList<String>());
        floors.get(5).add("0");
        floors.get(5).add("false");
        

        //floor 6
        floors.put(6, new ArrayList<String>());
        floors.get(6).add("10");
        floors.get(6).add("true");
        

        //floor 7
        floors.put(7, new ArrayList<String>());
        floors.get(7).add("0");
        floors.get(7).add("false");
       

        //floor 8
        floors.put(8, new ArrayList<String>());
        floors.get(8).add("0");
        floors.get(8).add("false");

        //floor 9
        floors.put(9, new ArrayList<String>());
        floors.get(9).add("10");
        floors.get(9).add("true");

        //floor 10
        floors.put(10, new ArrayList<String>());
        floors.get(10).add("10");
        floors.get(10).add("true");


        // create the elevator object to be used
        Elevator elevator = new Elevator();

        // the starting location of the elevator, dictated by what is set for the object by the user (manually set)
        int startingLocation = elevator.location;

        // total number of floors in the building
        int MAXFLOORS = floors.size();

        
        // find which direction to go based on what floor is closest to current floor
        // if they are tied then just go up first
        int closestUp = 0;
        int closestDown = 0;

        //check closest up
        for (int x = elevator.location + 1; x <= MAXFLOORS; x++) {
            if (floors.get(x).get(1) == "true") {
                closestUp = x;
                break;
            }
        }
        //check closest down
        for (int x = elevator.location - 1; x >= 1; x--) {
            if (floors.get(x).get(1) == "true") {
                closestDown = x;
                break;
            }
        }

        
        // check if either value is 0 which means that there are only requests on half of the floors and not the other half

        // check if there is a floor to go to going up
        if (closestUp == 0 && closestDown != 0) {
            elevator.direction = "down";
        }
        // check if there is a floor to go to going down
        else if  (closestDown == 0 && closestUp != 0) {
            elevator.direction = "up";
        }
        // if both closestDown and closestUp are not 0 see which one is closer
        else {
            int comparison = Integer.compare(Math.abs(elevator.location - closestUp), Math.abs(elevator.location - closestDown));
            if ((comparison == -1) || (comparison == 0)) {
                elevator.direction = "up";
            }
            else if (comparison == 1) {
                elevator.direction = "down";
            }
        }

        // check to see if starting floor is a also a floor that is needed to go to
        // for output puproses I am not going to count the starting floor as a stop
        // since the elevator started there
        if (floors.get(elevator.location).get(1) == "true") {
            // if there was people at the starting floor just add those people to peopleMoved
            // and change the status of the floor to "false" in the hashmap
            peopleMoved += Integer.parseInt(floors.get(elevator.location).get(0));

            floors.put(elevator.location, new ArrayList<String>());
            floors.get(elevator.location).add("0");
            floors.get(elevator.location).add("false");
        }
        
        boolean keepRunning = true;
        boolean firstTime = true;
        while(keepRunning) {

            // if it not the first time running through the loop then check to see if there are more floors to go to 
            if (!firstTime) {
                boolean found = false;
                for (int floor : floors.keySet()) {
                    // if a floor was found that still needs visitied continure looping
                    if (floors.get(floor).get(1) == "true") {
                        found = true;
                        break;
                    }
                }
                // if no more floors were found stop looping
                if (!found) {
                    keepRunning = false;
                }
            }

            if(elevator.direction == "up" && keepRunning) {
                int highestFloor = 0;
                // find the highest floor that needs to be reached
                for (int x = elevator.location + 1; x <= MAXFLOORS; x++) {
                    if (floors.get(x).get(1) == "true") {
                        highestFloor = x;
                    }
                }
                // while the elevator had not reached the highest floor and there is a floor to go up to
                while ((elevator.location < highestFloor) && highestFloor != 0) {
                    // move the elevator up one floor and add one to floors traversed
                    elevator.location++;
                    floorsTraversed++;

                    // if the current floor moved to is a floor that needs to be stopped at then
                    // add 1 to numberOfStop and change the floor value to false
                    if (floors.get(elevator.location).get(1) == "true") {
                        numberOfStops++;
                        peopleMoved += Integer.parseInt(floors.get(elevator.location).get(0));

                        floors.put(elevator.location, new ArrayList<String>());
                        floors.get(elevator.location).add("0");
                        floors.get(elevator.location).add("false");
                    }
                }
                elevator.direction = "down";
            }
            if(elevator.direction == "down" && keepRunning) {
                int lowestFloor = 0;
                // find the lowest floor that needs to be reached
                for (int x = elevator.location - 1; x >= 1; x--) {
                    if (floors.get(x).get(1) == "true") {
                        lowestFloor = x;
                    }
                }
                // while the elevator had not reached the lowest floor and there is a floor to go down to
                while ((elevator.location > lowestFloor) && (lowestFloor != 0)) {
                    // move the elevator up one floor and add one to floors traversed
                    elevator.location--;
                    floorsTraversed++;

                    // if the current floor moved to is a floor that needs to be stopped at then
                    // add 1 to numberOfStop and change the floor value to false
                    if (floors.get(elevator.location).get(1) == "true") {
                        numberOfStops++;
                        peopleMoved += Integer.parseInt(floors.get(elevator.location).get(0));

                        floors.put(elevator.location, new ArrayList<String>());
                        floors.get(elevator.location).add("0");
                        floors.get(elevator.location).add("false");
                    }
                }
                elevator.direction = "up";
            }
            firstTime = false;
        }


        // after elevator has not more requests move it back to its starting location
        if (startingLocation != elevator.location) {
            floorsTraversed += Math.abs(startingLocation - elevator.location);
            elevator.location = startingLocation;
        }


        // output information here
        System.out.println("Starting location of elevator: " + startingLocation);
        System.out.println("Total number of floors moved: " + floorsTraversed);
        System.out.println("Total number of stops: " + numberOfStops);
        System.out.println("Total number of people moved: " + peopleMoved);
    }
}
