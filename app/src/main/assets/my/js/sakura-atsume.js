var sakuraAtsume = null;
var sakuraIcon = document.getElementById("sakuraIcon");
var hanaBtn1 = document.getElementById("hana3");
var hanaBtn2 = document.getElementById("hana4");
var hanaBtn3 = document.getElementById("hana5");

var SakuraAtsume = {
    sakuras: { hana3: false, hana4: false, hana5: false },
    sakuraAnimeFlg: false,
    // 初期化処理
    // 桜コレクションを初期化する
    init: function(hana3, hana4, hana5) {
        if (hana3 !== undefined) {
            this.sakuras.hana3 = true;
            hanaBtn1.style.visibility = "hidden";
        }
        if (hana4 !== undefined) {
            this.sakuras.hana4 = true;
            hanaBtn1.style.visibility = "hidden";
        }
        if (hana5 !== undefined) {
            this.sakuras.hana5 = true;
            hanaBtn1.style.visibility = "hidden";
        }

        this.sakuraAnimeFlg = false;

        // 桜タグ表示の切り替え
        this.sakuraIconChange();
        return this;
    },

    // 桜発見時に追加
    add: function(hana) {
        console.log(hana + "追加")
        switch (hana) {
            case "hana3" :
                this.sakuras.hana3 = true;
                hanaBtn1.style.visibility = "hidden";
                break;
            case "hana4" :
                this.sakuras.hana4 = true;
                hanaBtn2.style.visibility = "hidden";
                break;
            case "hana5" :
                this.sakuras.hana5 = true;
                hanaBtn3.style.visibility = "hidden";
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