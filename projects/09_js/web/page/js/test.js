// alert('hello world')
console.log('Hello world!');

let age = 10

age = 'hello'

age = true

age = {}

age = 'jack'

for (const c of age){
    console.log(c);
}

for (let i = 0; i < age.length; i++) {
    console.log(age.charAt(i));
}

function sum(a, b){
    return a + b;
}

function execute(fn, a, b){
    // alert(fn(a , b));
}

execute(sum, 10, 20)

execute(function (x, y) {
    return x - y;
}, 10, 20)

execute((x, y) =>x - y,20,1);

function test() {
    return sum
}

alert(test()(10,20))