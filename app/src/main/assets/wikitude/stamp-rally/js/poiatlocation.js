// implementation of AR-Experience (aka "World")
// Worldにロジックを記述する。
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// POI-Marker list
	markerList : [],

	// called to inject new POI data。
	// マーカーを設置する。
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {

	    /* Empty list of visible markers. */
        World.markerList = [];

        for (var cnt = 0; cnt < poiData.length; cnt++) {


            //
            var singlePoi = {
                "id": poiData[cnt].id,
                "latitude": poiData[cnt].latitude,
                "longitude": poiData[cnt].longitude,
                "altitude": poiData[cnt].altitude,
                "resource": poiData[cnt].resource
            };

            //
            World.markerList.push(new Maker(singlePoi));

        }

        // Updates status message as a user feedback that everything was loaded properly.
        // メッセージ
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

	// 位置が変化した時の処理。メインルーチンみたいになる。
	// location updates, fired every time you call architectView.setLocation() in native environment
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {

		/*
			The custom function World.onLocationChanged checks with the flag World.initiallyLoadedData if the function was already called. With the first call of World.onLocationChanged an object that contains geo information will be created which will be later used to create a marker using the World.loadPoisFromJsonData function.
		*/
		//　最初だけマーカーの位置を作成する。
		if (!World.initiallyLoadedData) {
			// creates a poi object with a random location near the user's location
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
			//　１回だけのフラグを立てる
			World.initiallyLoadedData = true;
		}
	},

	onError: function onErrorFn(error) {
        alert(error);
    }
};

/* 
	Set a custom function where location changes are forwarded to. There is also a possibility to set AR.context.onLocationChanged to null. In this case the function will not be called anymore and no further location updates will be received. 
*/
// 位置情報が変化した時の処理。これがメインルーチンみたいになる。
AR.context.onLocationChanged = World.locationChanged;