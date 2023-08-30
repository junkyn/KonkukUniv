/*202211389 최준규*/
window.onload=changeColor;
function changeColor(){
    var now = new Date();
    var t = now.getHours();
    var title = document.getElementById("title");
    if(t<6){
        title.style.color = 'gray';
        
    }else if(t<12){
        title.style.color = "yellow";
    }else if(t<18){
        title.style.color= "red";

    }else if(t<24){
        title.style.color="blue";
    }
}