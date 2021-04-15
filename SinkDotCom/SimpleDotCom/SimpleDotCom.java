class SimpleDotCom {

    int[] locationCells;
    int numberOfHits = 0;

    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String checkYourself(String stringGuess) {
        // convert the string to an int
        int guess = Integer.parseInt(stringGuess);

        // make a variable to hold the result we will return,
        // put "miss" as the default value
        String result = "miss";

        // repeat with each cell in the locationCells array
        for(int cell : locationCells) {
            // compare the user guess to this element(cell) in the array
            if(guess == cell) {
                // we got hit!
                result = "hit";
                numberOfHits++;
            
                // get out of the loop no need to test the other cells
                break;
            }
        }

        // let's check if we hit 3 times and change the result to "kill"
        if(numberOfHits == locationCells.length) {
            result = "kill";
        }

        // display the result for the user
        System.out.println(result);

        // return the result back to the calling method
        return result;
    }
}