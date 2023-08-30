var express = require('express'),http = require('http'),path=require('path');
var static = require('serve-static');

var app = express();
var router = express.Router();


app.set('port',process.env.PORT||8080);
app.set('host','127.0.0.1');

app.use(static(__dirname)); 
router.route('/routetest').get(function(req,res){
    res.redirect('http://www.google.com');
});
router.route('/').get(function(req,res){
    res.redirect('source/jquery.html');
});
app.use('/',router);
/*
app.use(function(req,res,next){
    console.log("첫 번째 미들웨어에서 요청을 처리함.");

    req.user = 'mike';
    next();
});
app.get('/',function(req,res,next){
    console.log('두 번째 미들웨어에서 요청을 처리함');

    res.writeHead('200',{'Content-Type':'text/html;charset=utf8'});
    res.end('<h1>Express 서버에서 '+req.user+'가 응답한 결과입니다.<h1>');
});
*/



app.use(express.urlencoded());
app.use(express.json());
http.createServer(app).listen(app.get('port'),app.get('host'), ()=>{
    console.log('Express server running at'+app.get('port')+app.get('host'));
});