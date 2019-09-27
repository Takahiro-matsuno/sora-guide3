// マーカーオブジェクトの定義
function Marker(poiData) {
    /*
     *   For creating the marker a new object AR.GeoObject will be created at the specified geolocation. An
     *   AR.GeoObject connects one or more AR.GeoLocations with multiple AR.Drawables. The AR.Drawables can be
     *   defined for multiple targets. A target can be the camera, the radar or a direction indicator. Both the
     *   radar and direction indicators will be covered in more detail in later examples.
     */
    this.poiData = poiData

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
            onClick: Marker.prototype.getOnClickTrigger(this)
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

    /*
        The setSelected and setDeselected functions are prototype Marker functions.
        Both functions perform the same steps but inverted, hence only one function (setSelected) is covered in
        detail. Three steps are necessary to select the marker. First the state will be set appropriately. Second
        the background drawable will be enabled and the standard background disabled. This is done by setting the
        opacity property to 1.0 for the visible state and to 0.0 for an invisible state. Third the onClick function
        is set only for the background drawable of the selected marker.
    */

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

Marker.prototype.setSelected = function(marker) {

    marker.isSelected = true;

    marker.markerDrawableIdle.opacity = 0.0;
    marker.markerDrawableSelected.opacity = 1.0;
    marker.markerDrawableIdle.onClick = null;
    marker.markerDrawableSelected.onClick = Marker.prototype.getOnClickTrigger(marker);
};

Marker.prototype.setDeselected = function(marker) {

    marker.isSelected = false;

    marker.markerDrawableIdle.opacity = 1.0;
    marker.markerDrawableSelected.opacity = 0.0;

    marker.markerDrawableIdle.onClick = Marker.prototype.getOnClickTrigger(marker);
    marker.markerDrawableSelected.onClick = null;
};


// Kotlinへのインタフェース関数
var sendKotlin = function (type, data) {
    var obj = new Object();
    obj.type = type;
    obj.data = data;
    AR.platform.sendJSONObject(obj);
}