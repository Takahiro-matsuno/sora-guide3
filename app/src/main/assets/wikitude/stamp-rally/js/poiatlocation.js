/**
  * AR Worldの定義
  */
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// POI-Marker list
	markerList : [],
	markerGetList: [],

	// called to inject new POI data。
	// Markerを設置する
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {

	    // Marker保持配列
        World.markerList = [];
        World.markerGetList = [];

        // Json配列分Markerを作成する
        for (var cnt = 0; cnt < poiData.length; cnt++) {
            World.markerGetList[cnt] = false;

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

        // Marker作成完了後のWorldStatusメッセージ
        World.updateStatusMessage(World.markerList.length + ' place loaded');
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
                poiData.push({
                    "id": id + cnt,
                    "longitude": (lon + (Math.random() / 5 - 0.1)),
                    "latitude": (lat + (Math.random() / 5 - 0.1)),
                    "altitude": 100.0, //　標高
                    "resource": resources[cnt]
                });
            }
            World.loadPoisFromJsonData(poiData);
			World.initiallyLoadedData = true;
		}
	},
	// マーカー取得
	getMarker: function getMarkerFn(id) {
	    // 取得済みリストの更新
	    World.markerGetList[id - 1] = true;
	    // Kotlinへ通知
	    sendKotlin("collectStamp", World.markerGetList)

	    // コンプリート判定
	    if (World.markerGetList.indexOf(false) == -1) {
	        completeMarker();
	    }
	},

	// コンプリート処理
	completeMarker: function completeMarker() {
	    console.log("complete");
	},

	onError: function onErrorFn(error) {
        alert(error);
    }
};
// 位置情報が変化にトリガーして実行される
AR.context.onLocationChanged = World.locationChanged;