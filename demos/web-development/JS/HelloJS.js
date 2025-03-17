//=========================Feel Free to comment out sections of this demo to better follow in the console==========================
//print to console
console.log("Hello World");

//global scope
//loosely typed
var a = 5;
let global = "global";

a = "hello";

a = {b: "I am an object", c:true};

console.log(a);


//Block Scope

//varaibles still can only de declared once in their scope
for (let index = 0; index <3; index++) {
   let b = "Something";
   var c = 2 //dont do this, var does not work in block scope
   console.log(global);
}

// console.log(b) //gives and error since its out of scope
console.log(c)


//Check for undefined using typeof
let und;
console.log(typeof und); //undefined


//Arrays
console.log("Arrays-------");
let myArr = [1, "string", {hello: "world!"}];

console.log(myArr[0]);
console.log(myArr[1]);
console.log(myArr[2]);
myArr[3] = "Added to Array"
console.log(myArr[3]);

//Weird quirk in array
// myArr[1.2] = "What";
console.log("Weird array");
// console.log(myArr[1.2]);
console.log(typeof myArr);

console.log("Functions ------");

function myFunc(a){
    console.log("You passed in " + a);

    return 3+2;
}

// console.log(typeof myFunc);

let h = myFunc("a string")

console.log(h);

var bigInt = 1234567890123456789012345678901234567890n; //n makes this bigInt

console.log(typeof bigInt);
console.log(typeof Infinity);


//type coercion 
console.log("type coercion-----");
console.log('1' + 1 + 1);
console.log(Number("2"));
console.log(String(123));
console.log(1 == "1");
console.log("This is a string concat to a number " + 123);

function coercionTest(input1, input2){
    console.log("Coersion Test---");
    console.log("input 1 is " + input1 + " with type of "+(typeof input1));
    console.log("input 2 is " + input2 + " with type of "+(typeof input2));
    console.log("Coercion shows them as " + ((input1 == input2)?"its the same":"its different"));
}

coercionTest(3,3) // same
coercionTest(1,3) // different

coercionTest("3", 3) //same
coercionTest("false", false) // different
coercionTest(0, false) // same, true



//Truth/Falsy Test
console.log("Truthy vs Falsy -----------");
function truthyTest(input){
    if(input){
        console.log("evaluated as True");
    } else {
        console.log("evaluated as false");
    }
}

truthyTest(false) // false
truthyTest(true) // true
truthyTest("") //false
truthyTest(" ") //true
truthyTest("false") //true
truthyTest(NaN) //false
truthyTest(null) //false
truthyTest(undefined) //false
truthyTest(0) //false
truthyTest(1) //true
truthyTest({}) //true
truthyTest(coercionTest) //true
truthyTest([]) //true
truthyTest([] + []) // false because it returns ""
console.log([] + {}); //[object Object]
console.log(true + true + false); // 2
console.log(Math.max()); //-Infinity
console.log(Math.min()); //Infinity 



//Functions 
console.log("Functions --------");

function myFunction(){
    console.log("a function");
}

let anonFunc = function(){
    console.log("This is an anon function");
}
myFunction()
anonFunc();

let arrowFunction = ()=>{console.log("This is an arrow function")}
arrowFunction()

//Callback function
let outterFunction = function(innerFunct){
    let str = "I'm a callback function"
    innerFunct(str)
}

outterFunction((s)=>{console.log("This is the inner function. I recieved :" + s)})

//Closures
console.log("Closures");
let createUser = function createUser(user){
    let name = user.name;
    let role = user.role;

    return function () {
        return {userName: name, userRole: role}
    }
}

let loggedInUser = {
    name:"Pablo",
    role:"Trainer"
}

let currentUser = createUser(loggedInUser);

console.log(currentUser());

//Another user

let anotherUser = currentUser()
anotherUser.userName = "another user"

console.log("------ Arrays --------");

//Array Literals
let myArrLiteral = ["one", "two", "three"]
console.log(myArrLiteral);

//Array using new keyword
let primes = new Array(2,3,5,7)
console.log(primes);

//Array Methods
console.log(primes.length);
let morePrimes = primes.concat(11)
console.log(morePrimes);

//forEach
primes.forEach((value) => {
    console.log(value);
})

//using for loop to iterate
for (let index = 0; index < primes.length; index++) { //length is a property not a method
   console.log(primes[index]);
}

//reassinging a value by its index
primes[1] = 13;

console.log(primes);

//push
primes.push(17)
console.log(primes);
//pop to remove
primes.pop()
console.log(primes);

console.log(primes.splice(2,1)); // removes from starts, to delete amount
console.log(primes);

//map
let primesTimes2 = primes.map((value)=> {
    return value * 2
})
console.log(primesTimes2);

//chain calls
let primesTimes2Plus2 = primes.map((value) => {
    return value * 2
}).map((value) => {
    return value + 2
})

console.log(primesTimes2Plus2);

//filter
//sort
//reduce 





