/**
 * 共通処理 （アプリやサーバへのインタフェースを記述）
 */

// アプリへのデータ送信
var sendKotlin = function(type, data) {
	console.log('Notice Kotlin');

	var obj = new Object();
	obj.type = type;
	obj.data = data;
	AR.platform.sendJSONObject(obj);
}

function hoge() {
	console.log("ほげ")
}