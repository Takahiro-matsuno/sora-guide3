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
            /*
                  The example Image Recognition already explained how images are loaded and displayed in the augmented reality view. This sample loads an AR.ImageResource when the World variable was defined. It will be reused for each marker that we will create afterwards.
            */
            // 画像
            var markerDrawable = new AR.ImageResource("assets/" + poiData[cnt].resource, {
                onError: World.onError
            });

            /*
                For creating the marker a new object AR.GeoObject will be created at the specified geolocation. An AR.GeoObject connects one or more AR.GeoLocations with multiple AR.Drawables. The AR.Drawables can be defined for multiple targets. A target can be the camera, the radar or a direction indicator. Both the radar and direction indicators will be covered in more detail in later examples.
            */
            // 位置情報
            var markerLocation = new AR.GeoLocation(poiData[cnt].latitude, poiData[cnt].longitude, poiData[cnt].altitude);

            // 描画情報.８は８倍のサイズ
            var markerImageDrawable = new AR.ImageDrawable(markerDrawable, 8, {
                zOrder: 0,  // 表示順
                opacity: 1.0,   // 不透明
                // クリック時処理
                onClick : function() {
                    // Kotlinに送信
                    // sendKotlin("collectStamp", this.poiData[cnt].id);
                    // アニメーション開始
                    // todo アニメーションを変更する
                    elevatorAnimation.start();
                }
            });

            // クリック時のアニメーション
            var elevatorAnimation = new AR.PropertyAnimation(
                markerImageDrawable, //the object geoLocation1 holds the animated property
                "rotation", // the property altitude will be animated
                0, // the start value of the animation
                360, // the resulting value of the animation
                1000, // the duration of the elevator climb is 10 seconds (10000 miliseconds)
                {type: AR.CONST.EASING_CURVE_TYPE.EASE_IN_OUT_QUAD}
            );

            World.markerList.push(markerImageDrawable)

            // create GeoObject
            // AR上に配置
            var markerObject = new AR.GeoObject(
                markerLocation,
                {
                    drawables: { cam: markerImageDrawable }
                }
            );
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

// Kotlinへのインタフェース関数
var sendKotlin = function (type, data) {
    var obj = new Object();
    obj.type = type;
    obj.data = data;
    AR.platform.sendJSONObject(obj);
}

/* 
	Set a custom function where location changes are forwarded to. There is also a possibility to set AR.context.onLocationChanged to null. In this case the function will not be called anymore and no further location updates will be received. 
*/
// 位置情報が変化した時の処理。これがメインルーチンみたいになる。
AR.context.onLocationChanged = World.locationChanged;