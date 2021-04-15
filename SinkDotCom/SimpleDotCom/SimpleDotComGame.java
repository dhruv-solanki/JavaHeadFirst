class SimpleDotComGame {

    public static void main(String[] args) {
        // make a variable to track how many guesses the user makes
        int numberOfGuesses = 0;
        
        // special class to take user input
        GameHelper helper = new GameHelper();

        // make the dot com object
        SimpleDotCom theDotCom = new SimpleDotCom();

        // make the random number for the first cell
        int randomNum = (int) (Math.random() * 5);

        // use random number to build locations array
        int[] locations = {randomNum, randomNum+1, randomNum+2};

        // give the dot com its locations
        theDotCom.setLocationCells(locations);

        // make a boolean variable to track wether the game is still alive 
        boolean isAlive = true;

        // check if game is alive
        while(isAlive) {
            // take user input by helper object's method
            String guess = helper.getUserInput("Enter a Number: ");

            // ask the dot com to check the guess and save the result
            String result = theDotCom.checkYourself(guess);
            // increment the numberOfGuesses
            numberOfGuesses++;

            // was dot com killed if so print the numberOfGuesses and stop the loop
            if(result.equals("kill")) {
                isAlive = false;

                System.out.println("You took, " + numberOfGuesses + " guesses");
            }
        }
    }
}