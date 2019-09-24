var sakuraAtsume = null;
var sakuraTag = document.getElementById("sakuraTag");
var hanaBtn1 = document.getElementById("hana1");
var hanaBtn2 = document.getElementById("hana2");
var hanaBtn3 = document.getElementById("hana3");

var SakuraAtsume = {
    sakuras: { hana1: false, hana2: false, hana3: false },
    sakuraAnimeFlg: false,
    // 初期化処理
    // 桜コレクションを初期化する
    init: function(h1, h2, h3) {
        if (h1 !== undefined) {
            this.sakuras.hana1 = true;
            hanaBtn1.style.visibility = "hidden";
        }
        if (h2 !== undefined) {
            this.sakuras.hana2 = true;
            hanaBtn1.style.visibility = "hidden";
        }
        if (h3 !== undefined) {
            this.sakuras.hana3 = true;
            hanaBtn1.style.visibility = "hidden";
        }

        this.sakuraAnimeFlg = false;

        // 桜タグ表示の切り替え
        this.sakuraTagChange();
        return this;
    },

    // 桜発見時に追加
    add: function(hana) {
        console.log(hana + "追加")
        switch (hana) {
            case "hana1" :
                this.sakuras.hana1 = true;
                hanaBtn1.style.visibility = "hidden";
                break;
            case "hana2" :
                this.sakuras.hana2 = true;
                hanaBtn2.style.visibility = "hidden";
                break;
            case "hana3" :
                this.sakuras.hana3 = true;
                hanaBtn3.style.visibility = "hidden";
                break;
        }

        // 桜タグ表示の切り替え
        this.sakuraTagChange();
    },

    // 桜タグの表示・非表示
    // sakurasコンプリート時に表示される
    sakuraTagChange: function() {
        if (this.sakuras.hana1 && this.sakuras.hana2 && this.sakuras.hana3) {
            sakuraTag.style.visibility ="visible";
        } else {
            sakuraTag.style.visibility ="hidden";
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