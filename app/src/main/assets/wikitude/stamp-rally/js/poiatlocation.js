/**
  * AR Worldの定義
  */
var acquiredList = [];
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// POI-Marker list
	markerList : [],
	//markerGetList: [],

	// called to inject new POI data。
	// Markerを設置する
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {
	    console.log("初期化開始", acquiredList);
	    // Marker保持配列
        World.markerList = [];

        if (poiData.length != 0) {
            // Json配列分Markerを作成する
            for (var cnt = 0; cnt < poiData.length; cnt++) {
                console.log("loadPoisFromJsonDataFn_id:", poiData[cnt].id)
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
        } else {
            // コンプリートの場合はクーポンアイコンを表示
            World.completeMarker();
        }

        console.log("初期化終了", acquiredList);
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

	// マーカー取得
    getMarker: function getMarkerFn(id, src) {
        console.log("come here")

        /* 取得済みリストの更新 */
        acquiredList[id - 1] = true;

        /* Kotlinへ通知 */
        sendKotlin("COLLECT_STAMP", JSON.stringify(acquiredList));

        /* UI更新
        // トースト表示
        var jsFrame = new JSFrame();
        jsFrame.showToast({ html: 'スタンプ獲得！！', align: 'bottom', duration: 2000});
        */
        // モーダル表示
        World.showModal(src, 'スタンプを見つけました!!');
        /* コンプリート判定 */
        if (acquiredList.indexOf(false) == -1) {
            World.completeMarker();
        }
    },

    // コンプリート処理
    completeMarker: function completeMarkerFn() {
        // モーダル表示
        World.showModal('assets/coupon.jpg', 'クーポンを獲得しました');
        // WorldStatusメッセージ
        World.updateStatusMessage('Marker Completed');
        // アイコン表示
        World.showCompleteIcon();
    },

    // モーダル表示
    showModal: function showModalFn(src, message) {
        $('#modal-img').attr('src', 'assets/' + src);
        $('#modal-message').text(message);
    },

    // アイコン表示
    showCompleteIcon: function showCompleteIconFn() {
        // TODO アイコン表示
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
			// TODO コンプリートの場合、completeMarkerを呼び出す
            for (var cnt = 0; cnt < resources.length; cnt++) {
                 //すでに取得済みのスタンプの場合
                if(acquiredList[cnt]) {
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
			World.initiallyLoadedData = true;
		}
	},
    // エラー表示
	onError: function onErrorFn(error) {
        alert(error);
    }
};
// 位置情報が変化にトリガーして実行される
AR.context.onLocationChanged = World.locationChanged;

//Kotlinから取得済みリストを取得する
function setAcquiredListFn(jStr) {
    var jArray = JSON.parse(jStr);
    for (var cnt = 0; cnt < jArray.length; cnt++) {
        acquiredList[cnt] = jArray[cnt];
    }
}

$(function() {
   $('#open').on('click', function() {
     $('#overlay, #modalWindow').fadeIn();
   });

   $('#close').on('click', function() {
     $('#overlay, #modalWindow').fadeOut();
   });

   locateCenter();
   $(window).resize(locateCenter);

   function locateCenter() {
     let w = $(window).width();
     let h = $(window).height();

     let cw = $('#modalWindow').outerWidth();
     let ch = $('#modalWindow').outerHeight();

     $('#modalWindow').css({
       'left': ((w - cw) / 2) + 'px',
       'top': ((h - ch) / 2) + 'px'
     });
   }
});

