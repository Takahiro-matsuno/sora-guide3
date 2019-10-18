/**
 * Worldに表示するARObjectの定義
 */
function Marker(poiData) {
	/**
	 * マーカーを作成するために、新しいオブジェクトAR.GeoObjectが指定された地理位置に作成されます。
	 * AR.GeoObjectは、1つ以上のAR.GeoLocationsと複数のAR.Drawableを接続します。
	 * AR.Drawablesは複数のターゲットに対して定義できます。ターゲットは、カメラ、レーダー、または方向インジケーターです。
	 * レーダーと方向インジケータの両方については、後の例で詳しく説明します。
	 */
	this.poiData = poiData;

	/*
	 *  The example Image Recognition already explained how images are loaded and displayed in the augmented reality view. This sample loads an AR.ImageResource when the World variable was defined. It will be reused for each marker that we will create afterwards.
	 */
	// 画像を取得
	var markerDrawable = new AR.ImageResource("assets/" + this.poiData.resource, {
		onError: World.onError
	});

	/* Create the AR.GeoLocation from the poi data. */
	// 位置情報を設定
	var markerLocation = new AR.GeoLocation(
		this.poiData.latitude,
		this.poiData.longitude,
		this.poiData.altitude
	);

	/* Create an AR.ImageDrawable for the marker in idle state. */
	this.markerImageDrawable = new AR.ImageDrawable(
		markerDrawable,
		8, {
			zOrder: 0,
			opacity: 1.0,
			onClick: function() {
				console.log('Marker onClick:', poiData.resource);
				// Worldに通知
				World.getMarker(poiData.id);
				// マーカーを削除
				markerDrawable.destroy();
			}
		}
	);

	/* Create the AR.GeoObject with the drawable objects. */
	this.markerObject = new AR.GeoObject(
		markerLocation, {
			drawables: {
				cam: [this.markerImageDrawable]
			}
		}
	);

	return this;
}