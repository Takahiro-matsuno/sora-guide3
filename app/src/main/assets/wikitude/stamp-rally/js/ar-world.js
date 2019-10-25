/**
 * AR Worldの定義
 */
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// POI-Marker list
	markerList: [],
    arId: -1,
    resObj: [],

	// called to inject new POI data。
	// Markerを設置する
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(lat, lon, alt, acc) {
		console.log('World Initialize STA:', World.resObj);

		// Marker保持配列
		World.markerList = [];
		var resNum = World.resObj.length;
		for (var cnt = 0; cnt < resNum; cnt++) {
		    var res = World.resObj[cnt];
		    if (res.isAcquired) {
		        console.log('取得済み:', res.markerId);

		        // 取得済みの場合はフッターに取得済みアイコンを表示する
		        $('#acquired-item').append('<img src="assets/' + res.markerUri + '" id="markerIcon' + res.markerId + '" />');
		    } else {
		        console.log('未取得:', res.markerId);

		        // 未取得の場合はARObjectを追加
		        var singlePoi = {
		            "id": res.markerId,
		            "latitude": (lat + (Math.random() / 5 - 0.1)),
                    "longitude": (lon + (Math.random() / 5 - 0.1)),
                    "altitude": 100,
                    "resource": 'assets/' + res.markerUri
		        }
		        World.markerList.push(new Marker(singlePoi));
		        // フッターに未取得アイコンを表示する
		        $('#acquired-item').append('<img src="assets/stamp_gray.png" id="markerIcon' + res.markerId + '" />');
		    }
		}

		if (World.markerList.length == 0) {
		    // 全部取得済みの場合はクーポンダイアログを表示
		    World.showCouponWindow();
		}

		console.log('World Initialize END:');
	},
/*
デフォルトインフォアイコン
	// updates status message shon in small "i"-button aligned bottom center
	// index.htmlのメッセージやアイコンに状態を表示する
	updateStatusMessage: function updateStatusMessageFn(message, isWarning) {
		var themeToUse = isWarning ? "e" : "c";
		var iconToUse = isWarning ? "alert" : "info";
		$("#status-message").html(message);
		$("#popupInfoButton").buttonMarkup({
			theme: themeToUse
		});
		$("#popupInfoButton").buttonMarkup({
			icon: iconToUse
		});
	},
*/
	// マーカー取得処理
	getMarker: function getMarkerFn(id, res) {
		console.log('Get Marker');

        /* 取得済みリストの更新 */
		const marker = World.resObj.find((res) => {
            return (res.markerId === id);
        });
        marker.isAcquired = true;

		/* UI更新 */
        // トースト表示
        var jsFrame = new JSFrame();
        jsFrame.showToast({ html: 'スタンプ獲得！！', align: 'center', duration: 2000});

        /* footerの更新 */
        var timestamp = new Date().getTime();
        // jquery-1.9.1ではattrで画像が書き換えられないのでjsで書く
        //$('#markerIcon' + id).attr({"src": res});
        var target = document.getElementById('markerIcon' + id);
        target.src = res;

		/* Kotlinへ通知 */
		var sendObj = new Object();
		sendObj.type = "data";
		var data = new Object();
		data.key = World.arId;
		data.value = "ほげほげ";
		sendObj.data = data;
		AR.platform.sendJSONObject(sendObj);

		/* コンプリート判定 */
		const list = World.resObj.filter((res) => {
		    return (res.isAcquired === false);
		});
		if (list.length === 0) {
		    // 遅延実行
            window.setTimeout( function() { World.showCouponWindow(); }, 2000 );
		}
	},
	// クーポンダイアログ表示
	showCouponWindow: function showCouponWindowFn() {
		console.log('Show Coupon Window');

		$('#stamp-rally-overlay').fadeIn();
		$('#coupon-window').fadeIn();

		$('#coupon-window-close').on('click', function() {
		    console.log('Close Coupon Window');

		    $('#coupon-window').fadeOut();
		    $('#stamp-rally-overlay').fadeOut();
		});
	},
	// ネイティブ環境でarchitectView.setLocationを呼び出すたびに起動される場所の更新
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {

		/**
		 * カスタム関数World.onLocationChangedは、関数が既に呼び出されているかどうかをフラグWorld.initiallyLoadedDataでチェックします。
		 * World.onLocationChangedの最初の呼び出しで、地理情報を含むオブジェクトが作成され、後でWorld.loadPoisFromJsonData関数を使用してマーカーを作成するために使用されます。
		 */

		if (!World.initiallyLoadedData) {
		    // 未初期化の場合は初期化する
		    World.loadPoisFromJsonData(lat, lon, alt, acc);
            World.initiallyLoadedData = true; // 初期化済みにする
		}

		/*
		if (!World.initiallyLoadedData) {
			// Worldを初期化し、Marker用のJSON
			var resources = ["stamp_red.png", "stamp_blue.png", "stamp_green.png"];
			var poiData = [];
			var id = 1;
			for (var cnt = 0; cnt < resources.length; cnt++) {
				//すでに取得済みのスタンプの場合
				if (World.acquiredList[cnt]) {
					continue;
				}
				poiData.push({
					"id": id + cnt,
					"longitude": (lon + (Math.random() / 5 - 0.1)),
					"latitude": (lat + (Math.random() / 5 - 0.1)),
					"altitude": 100.0, //　標高
					"resource": resources[cnt]
				});
			}
			World.loadPoisFromJsonData(poiData);
            World.initiallyLoadedData = true; // 初期化済みにする
		}
		*/
	},
	// エラー表示
	onError: function onErrorFn(error) {
		alert(error);
	}
};
// 位置情報が変化にトリガーして実行される
AR.context.onLocationChanged = World.locationChanged;

// Kotlinから取得済みリストを取得する
function setResourceObjectFn(arResource) {
    console.log('setResourceObjectFn', arResource);

    var obj = JSON.parse(arResource);
    World.arId = obj.stampRallyId;
    World.resObj = obj.markers;
}