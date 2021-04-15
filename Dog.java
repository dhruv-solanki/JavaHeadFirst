class Dog {
    String name;

    public void bark() {
        System.out.println(name + " says Ruff!");
    }

    public static void main(String[] args) {
        // make dog object and access it.
        Dog dog1 = new Dog();
        dog1.bark();
        dog1.name = "Bart";

        // now make a dog array.
        Dog[] myDogs = new Dog[3];
        // and puts some dogs in it.
        myDogs[0] = new Dog();
        myDogs[1] = new Dog();
        myDogs[2] = dog1;

        // now access the dogs using array references.
        myDogs[0].name = "Fred";
        myDogs[1].name = "Marge";

        // Hmmm... what is myDogs[2] name?
        System.out.print("Last Dog's name, " + myDogs[2].name);

        // now loop through the array and tell all dogs to bark.
        int x = 0;
        while (x < myDogs.length) {
            myDogs[x].bark();
            x = x + 1;
        }
    }
}