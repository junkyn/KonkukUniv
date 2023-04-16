/*202211389 최준규*/
function showColor(){
    var cor;
    var a,b,c;
    var colordivs = document.getElementsByClassName('colorDiv');
    for(var i = 0; i<colordivs.length;i++){
        a=Math.floor(Math.random()*255+1);
        b=Math.floor(Math.random()*255+1);
        c=Math.floor(Math.random()*255+1);
        cor = 'rgb('+a+','+b+','+c+')';
        colordivs[i].innerHTML = cor;
        colordivs[i].style.backgroundColor = cor;
    }
}