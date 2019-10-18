/**
 * AR Worldの定義
 */
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// POI-Marker list
	markerList: [],
    acquiredList: [],

	// called to inject new POI data。
	// Markerを設置する
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {
		console.log('World Initialize STA:', World.acquiredList);

		// Marker保持配列
		World.markerList = [];

		if (poiData.length != 0) {
			// Json配列分Markerを作成する
			for (var cnt = 0; cnt < poiData.length; cnt++) {
			    console.log("World Add Marker:", poiData[cnt].resource);

                // Marker用にJsonを再定義
				var singlePoi = {
					"id": poiData[cnt].id,
					"latitude": poiData[cnt].latitude,
					"longitude": poiData[cnt].longitude,
					"altitude": poiData[cnt].altitude,
					"resource": poiData[cnt].resource
				};

				// Markerを作成し、配列に追加
				World.markerList.push(new Marker(singlePoi));
			}
		} else {
			// コンプリートの場合はクーポンアイコンを表示
			// TODO コンプリート時の処理を検討する
			World.showCouponWindow();
		}
		console.log('World Initialize END:');
	},

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
	// マーカー取得処理
	getMarker: function getMarkerFn(id) {
		console.log('Get Marker');

		/* UI更新 */
        // トースト表示
        var jsFrame = new JSFrame();
        jsFrame.showToast({ html: 'スタンプ獲得！！', align: 'bottom', duration: 2000});

        // モーダル表示
        // TODO 画像が置き換わらない https://codeday.me/jp/qa/20190604/930209.html
        //World.showModal(src, 'スタンプを見つけました!!');

		/* 取得済みリストの更新 */
		World.acquiredList[id - 1] = true;

		/* Kotlinへ通知 */
		sendKotlin("COLLECT_STAMP", JSON.stringify(World.acquiredList));

		/* コンプリート判定 */
		if (World.acquiredList.indexOf(false) == -1) {
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
	},
	// エラー表示
	onError: function onErrorFn(error) {
		alert(error);
	}
};
// 位置情報が変化にトリガーして実行される
AR.context.onLocationChanged = World.locationChanged;

// Kotlinから取得済みリストを取得する
function setAcquiredListFn(jStr) {
	var jArray = JSON.parse(jStr);
	for (var cnt = 0; cnt < jArray.length; cnt++) {
		World.acquiredList[cnt] = jArray[cnt];
	}
}