var canvas = document.getElementById("cvs");
var ctx = canvas.getContext("2d");
var imgCnt = 25;          // 描画する画像の数
var aryImg = [];          // 画像の情報を格納
var cvsw = canvas.clientWidth;           // canvasタグに指定したwidth
var cvsh = canvas.clientHeight;           // canvasタグに指定したheight
var imgBaseSizeW = 9;    // 画像の基本サイズ横幅
var imgBaseSizeH = 7.4;  // 画像の基本サイズ立幅
var aspectMax = 1.5;      // アスペクト比計算時の最大値
var aspectMin = 0.5;      // アスペクト比計算時の最小値
var speedMax = 2;         // 落下速度の最大値
var speedMin = 0.5;       // 落下速度の最小値
var angleAdd = 4;         // 画像角度への加算値
var task = null;

// 画像の読み込み
var img = new Image();
img.src = "./fonts/images/sakura.png";

// 画像のパラメーターを設定
function setImagas(){
  var aspect = 0;
  for(var i = 0;i < imgCnt;i++){
    // 画像サイズに掛けるアスペクト比を0.5~1.5倍でランダムで生成
    aspect = Math.random()*(aspectMax-aspectMin)+aspectMin;
    aryImg.push({
      "posx": Math.random()*cvsw,   // 初期表示位置x
      "posy": Math.random()*cvsh,   // 初期表示位置y
      "sizew": imgBaseSizeW*aspect, // 画像の横幅
      "sizeh": imgBaseSizeH*aspect, // 画像の縦幅
      "speedy": Math.random()*(speedMax-speedMin)+speedMin,　// 画像が落ちていく速度
      "angle": Math.random()*360,   // 角度
    });
  }
}

// 描画、パラメーターの更新
var idx = 0;
var cos = 0;
var sin = 0;
var rad = Math.PI / 180;

function flow(){
    ctx.clearRect(0,0,cvsw,cvsh);
    for(idx = 0;idx < imgCnt;idx++){
        aryImg[idx].posy += aryImg[idx].speedy;
        aryImg[idx].angle += Math.random()*angleAdd;
        cos = Math.cos(aryImg[idx].angle * rad);
        sin = Math.sin(aryImg[idx].angle * rad);
        ctx.setTransform(cos, sin, sin, cos, aryImg[idx].posx, aryImg[idx].posy);
        ctx.drawImage(img, 0, 0 , aryImg[idx].sizew , aryImg[idx].sizeh);
        ctx.setTransform(1, 0, 0, 1, 0, 0);
        // 範囲外に描画された画像を上に戻す
        if(aryImg[idx].posy >= cvsh){
            aryImg[idx].posy = -aryImg[idx].sizeh;
        }
    }
}

// アニメーション開始
function animationStart() {
    setImagas();
    this.task = setInterval(flow, 10);
}

// アニメーション停止
function animationStop() {
    clearInterval(task);
    ctx.clearRect(0,0,cvsw,cvsh);
}
