/*
    The incomplete attempt of trying to implement OOP in JS
    However, it is better syntax wise when implementing OOP compared to just using functions
    It has some working implementation of OOP pillars
    Polymorphism is the pillar that is lacking with JS
    Abstraction is the pillar that is also lacking with JS

*/

let dog = {
    color: "Black",
    size: 40,

    speak: function () {
        console.log("Bark!");
    },

    howBig: function () {
        //"this" keyword becomes really important if you want to reference the properties within an object
        console.log("The dog is this big! - " + this.size);
    }
}

let size = 100;
dog.howBig();
console.log(dog.colr); //A simple spelling mistake will give undefined since colr doesn't exist

// This is class is almost similar but doesn't have access modifiers (since it doesn't exist)
class Animal {

    //Will automatically create a name field for you
    //Works the same in Java constructor
    constructor(name){
        this.name = name;
    }

    /*
    This will not work since JS will not do method overloading nor constructor overloading the one thing missing in our OOP pillars
    speak() {
        console.log("Speaking");
    }
        Default parameters is a way to accomplish polymorphism
        If the person who used the method didn't give a value to a parameter, the default value will be used
    */
    speak(talk = "Default", howMany) {
        console.log(talk);
    }
}

let ani1 = new Animal("Minnie");
// console.log(ani1.name);
ani1.speak("Bark");

//Inheritance works the same in Java, you get to inherit the class members of the parent class
class Dog extends Animal {
    //This is a public field
    color = "Black"

    //This is a private field
    //Adding a hashtag will make a field private meaning you can't access it directly
    #size = 20;

    //Getters & Setters
    //Getter method
    get Size() {
        return this.#size + 100;
    }

    //Setter method
    set Size(size){
        this.#size = size + 100;
    }

}

let dog1 = new Dog("Minnie2");
console.log(dog1);
dog1.speak("Bark");
console.log(dog1.color);
// console.log(dog1.#size); This will give an error because size is a private field
console.log(dog1.Size);

dog1.Size = 40;

console.log(dog1.Size);
/*
    What will be display on line 68?
    - Undefined - 3 so it has no value in the outside therefore undefined; 
    - error - 3 Because it is private it should throw an error saying you can't access it
    - 20 - 2 - if it is private what's the point I can't even do anything with it
*/