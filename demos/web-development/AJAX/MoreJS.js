//Spread operator
//Convert the array into its own individual elements
let arr = [1, 2, "string"];

console.log(arr);

console.log(...arr);

//Combining any number of arrays together
let arr2 = [4, 5, "hello", true];

let someString = "Hello";
//Spread operator with strings will individual separate the string's character into its own element
let arr3 = [...arr, ...someString];

//Concat does not and will add that whole string as one element
let arr4 = arr2.concat(arr, someString);
console.log(arr3);
console.log(arr4);

//Using spread operator as parameters
function add(num1, num2, num3) {
    console.log(num1+num2+num3);
}

//Whats the output of this function?
add(...arr);

//Rest parameters
//This is to have an input/parameter that have indefinite amount of parameters
//You cannot add a parameter after the rest parameter (just think about it and I'm sure it makes sense)
function addMore(num1, num2,...numbers) {
    let sum = 0;
    numbers.forEach(element => {
        sum+=element;
    })

    console.log(sum);
}

addMore(5,4,2,6,7);
addMore(1,2);
addMore();