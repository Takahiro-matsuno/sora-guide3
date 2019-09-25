var sakuraAtsume = null;
var sakuraIcon = document.getElementById("sakuraIcon");
var hanaBtn3 = document.getElementById("hana3");
var hanaBtn4 = document.getElementById("hana4");
var hanaBtn5 = document.getElementById("hana5");

var SakuraAtsume = {
    sakuras: { hana3: false, hana4: false, hana5: false },
    sakuraAnimeFlg: false,
    // 初期化処理
    // 桜コレクションを初期化する
    init: function() {
        console.log("桜集め初期化")
        this.sakuraIconChange();
        return this;
    },

    set: function(hana3, hana4, hana5) {
        console.log("配列初期化")
        if (hana3) {
            this.sakuras.hana3 = true;
            hanaBtn3.style.visibility = "hidden";
        }
        if (hana4) {
            this.sakuras.hana4 = true;
            hanaBtn4.style.visibility = "hidden";
        }
        if (hana5) {
            this.sakuras.hana5 = true;
            hanaBtn5.style.visibility = "hidden";
        }
        this.sakuraIconChange();
    },

    // 桜発見時に追加
    add: function(hana) {
        console.log(hana + "追加")
        switch (hana) {
            case "hana3" :
                this.sakuras.hana3 = true;
                hanaBtn3.style.visibility = "hidden";

                // データバインディング
                var data = new Object();
                data.hanaName = "hana3";
                data.hanaFlg = true;

                var obj = new Object();
                obj.type = "hanaCollection";
                obj.data = data;

                AR.platform.sendJSONObject(obj);
                break;
            case "hana4" :
                this.sakuras.hana4 = true;
                hanaBtn4.style.visibility = "hidden";

                // データバインディング
                var data = new Object();
                data.hanaName = "hana4";
                data.hanaFlg = true;

                var obj = new Object();
                obj.type = "hanaCollection";
                obj.data = data;

                AR.platform.sendJSONObject(obj);
                break;
            case "hana5" :
                this.sakuras.hana5 = true;
                hanaBtn5.style.visibility = "hidden";

                // データバインディング
                var data = new Object();
                data.hanaName = "hana5";
                data.hanaFlg = true;

                var obj = new Object();
                obj.type = "hanaCollection";
                obj.data = data;

                AR.platform.sendJSONObject(obj);
                break;
        }

        // 桜タグ表示の切り替え
        this.sakuraIconChange();
    },

    // 桜タグの表示・非表示
    // sakurasコンプリート時に表示される
    sakuraIconChange: function() {
        if (this.sakuras.hana3 && this.sakuras.hana4 && this.sakuras.hana5) {
            sakuraIcon.style.visibility ="visible";
        } else {
            sakuraIcon.style.visibility ="hidden";
        }
    },

    // 桜吹雪アニメーションの表示・非表示
    // 桜タグタップ時に切り替える
    sakuraAnimationChange: function() {
        if (this.sakuraAnimeFlg) {
            // アニメーション停止
            this.sakuraAnimeFlg = false;
            console.log("アニメーション停止")
            animationStop()

        } else {
            // アニメーション開始
            this.sakuraAnimeFlg = true;
            console.log("アニメーション開始")
            animationStart()
        }
    }
};

$(document).ready(function(){
    console.log("ロード");
    sakuraAtsume = SakuraAtsume.init(); // 初期化
})