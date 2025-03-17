function getPokemon() {
    //I need to grab the pokemon name the user wants to search for
    let pokename = document.querySelector("#pokename").value;

    //Interacting with your backend api
    //By default, fetch will use GET as its type of request
    //You can change the request method by adding a second parameter in your fetch
        //This second parameter needs an object and it needs a method property
    //JSON = JS object
    fetch(`https://pokeapi.co/api/v2/pokemon/${pokename}`,{method:"GET"})
        /*
            Then, catch, finally is very similar to try, catch, finally in Java error handling
            They also have very similar functionalities
            Get the result from this endpoint and convert it to a JS object
        */
       .then(result => {
            if(result.status == 404){
                throw new Error("Pokemon not found")
            }else if(result.status == 500) {
                throw new Error("Please wait a moment. Server is in maintence")
            }else {
                return result.json();
            }
       })
       .then(jsObject => {
           console.log(jsObject);

           document.querySelector("#result").innerHTML = "";
           //Courtesy of Aidan

           let paragraphId = document.createElement("p");
           paragraphId.innerHTML = `Id: ${jsObject.id}\nName: ${jsObject.name}`

           let image = document.createElement("img");
           image.src = jsObject.sprites.front_default;

           document.querySelector("#result").append(paragraphId);
           document.querySelector("#result").append(image);

        })
        //Catch specifically for fetch will only catch if it grabs a 4XX or 5XX status code
        .catch((error) => {
            //Change your website dynamically to tell the user something went wrong
            document.querySelector("#result").innerHTML = "";

            let span = document.createElement('span');
            span.innerHTML = error;
            span.style = "color:red";

            document.querySelector("#result").append(span);
        })
        //Catch method is for when your fetch fails
        //This will execute no matter the result of your fetch
        .finally(() => {

        })
}

/*
    async/await is an extension of Promises

    await - it will wait until the promises resolves or gives an result

    async - this function will give a promise

    async can be used without await. However, await needs an async keyword to work
*/
async function anotherWay() {
    let response = await fetch("https://pokeapi.co/api/v2/pokemon/bulbasaur");

    //Behavior to handle problems
    if (response.status == 404) {
        console.log("I found a 404!")
    }

    let jsObject = await response.json();

    document.querySelector("#result").innerHTML = "";
           //Courtesy of Aidan

    let paragraphId = document.createElement("p");
    paragraphId.innerHTML = `Id: ${jsObject.id}\nName: ${jsObject.name}`

    let image = document.createElement("img");
    image.src = jsObject.sprites.front_default;

    document.querySelector("#result").append(paragraphId);
    document.querySelector("#result").append(image);
}