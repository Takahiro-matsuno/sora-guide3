# SoraGuide

## Wikitude
### 画像認識

### 位置情報

### Kotlin-JavaScriptインターフェース
Kotlin-JavaScriptでのデータ受け渡し又は、コールバック処理。

- kotlin-javascript
    - kotlin
        ```kotlin
        var architectView = findViewById<ArchitectView>(R.id.architectView)
        // 関数名、引数を文字列で指定（String型は「']で囲む
        architectView.callJavascript("func('hoge', true)")
        ``` 
    - javascript     
        ```javascript
        var func = function(name, flg) {
            console.log("name:" + name); // name: hoge
            console.log("flg:" + flg);  // flg: true
        }
        ```  
- javascript-kotlin
    - javascript
        ```javascript
        // 送信データ作成
        var jsonObject = new Object();
        jsonObject.name = "hoge"
        jsonObject.flg = true;
        
        // interfaceメソッド呼び出し
        AR.platform.sendJSONObject(jsonObject);
        ```
    - kotlin
        ```kotlin
       architectView.addArchitectJavaScriptInterfaceListener { jsonObject ->
            // 受信処理
            Log.d(jsonObject) // {"name": "hoge", "flg": true} 
       }
        ```