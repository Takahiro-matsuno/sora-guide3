/**
 * Worldに表示するARObjectの定義
 */
function Marker(poiData) {
    /*
     *   For creating the marker a new object AR.GeoObject will be created at the specified geolocation. An
     *   AR.GeoObject connects one or more AR.GeoLocations with multiple AR.Drawables. The AR.Drawables can be
     *   defined for multiple targets. A target can be the camera, the radar or a direction indicator. Both the
     *   radar and direction indicators will be covered in more detail in later examples.
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
        8,
        {
            zOrder: 0,
            opacity: 1.0,
            onClick: function() {
                console.log("marker id:" + poiData.id + "取得");
                window.alert("スタンプ獲得！！");
                // マーカーを取得済みにする
                World.getMarker(poiData.id);
                // マーカーを削除
                markerDrawable.destroy();
            }
        }
    );

    /* Create the AR.GeoObject with the drawable objects. */
    this.markerObject = new AR.GeoObject(
        markerLocation,
        {
            drawables: { cam: [this.markerImageDrawable] }
        }
    );

    return this;
}

Marker.prototype.getOnClickTrigger = function(marker) {

    return function() {

        if (marker.isSelected) {

            Marker.prototype.setDeselected(marker);

        } else {
            Marker.prototype.setSelected(marker);
            try {
                World.onMarkerSelected(marker);
            } catch (err) {
                alert(err);
            }
        }
        return true;
    };
};