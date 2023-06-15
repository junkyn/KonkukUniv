// express 기본 모듈 불러오기
var express = require('express'), http = require('http');
// express 미들웨어 불러오기
var static = require('serve-static');

// express 객체 생성
var app = express();
var router = express.Router();

// 기본 속성 설정
app.set('port', process.env.PORT || 8080);
app.set('hostname','127.0.0.1');

// 웹 폴더에 저장된 모든 웹 페이지에대한 정적 참조 허용
app.use(static(__dirname));


// 루트 패스로 접근하는 request에 대해서는 final.html로 redirect
router.route('/').get(function (req, res){
	res.redirect('./final.html');
});

app.use('/',router);

http.createServer(app).listen(app.get('port'), app.get('host'), () => {
	console.log('Express server running at ' + app.get('port') + app.get('hostname'));
});