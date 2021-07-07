/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getJSessionID(){
    let jsessionid = "";
    if(!navigator.cookieEnabled && location.pathname.includes("jsessionid")){
        let startIndex = location.pathname.match("jsessionid").index + "jsessionid".length+1;
        let endIndex = location.pathname.length;
        jsessionid = location.pathname.substring(startIndex,endIndex);
    }
    return jsessionid;
}
function generateUrl(path){
    let jsessionid = getJSessionID();
    if(jsessionid.length !== 0){
        path += ";jessionid" + jsessionid;
    }
    return path;
}

function getValue(i,j){
    var xhttp = new XMLHttpRequest();
    xhttp.responseType="json";
    xhttp.onreadystatechange=function(){
        if(this.readyState===4 && this.status===200){
            let bomb = this.response.isBomb;
            let value = this.response.value;
            console.log(bomb);
            console.log(value);
            if(bomb){
                window.alert("Hai perso, kek");
                resetGame();
            }else{
                document.getElementById(""+i+j).innerHTML=value;
                checkwin();
                
            }
            
        }
    };
    xhttp.open("POST",generateUrl("/Minesweeper/GetValue",true));
    xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhttp.send("x="+i+"&y="+j);
    
}
function resetGame(){
     var xhttp = new XMLHttpRequest();
    console.log("Resetting....")
    xhttp.onreadystatechange=function(){
        if(this.readyState===4 && this.status===200){
            console.log("Redirecting!")
            var url = generateUrl("/Minesweeper/StartGame");
            window.location.replace(url);
        }
    };
    xhttp.open("GET",generateUrl("/Minesweeper/ResetGame"),true);
    xhttp.send();
    
    
}
function checkwin(){
    var count = 0;
    var caselle = document.getElementsByClassName("cell");
    for(var i=0; i<caselle.length;i++){
        if(caselle[i].value!=" " && caselle[i].value!="B"){
            count++;
        }
    }
    if(count===71){
        window.alert("Yeee");
        resetGame();
    }
    
}
function getValueButton(){
    var x = document.getElementById("xselector").value;
    var y = document.getElementById("yselector").value;
    getValue(x,y);
}
