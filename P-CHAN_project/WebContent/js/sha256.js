function async_digestMessage(message) {
    return new Promise(function(resolve){
    var msgUint8 = new TextEncoder("utf-8").encode(message);
    crypto.subtle.digest('SHA-256', msgUint8).then(
        function(hashBuffer){
            var hashArray = Array.from(new Uint8Array(hashBuffer));
            var hashHex = hashArray.map(function(b){return b.toString(16).padStart(2, '0')}).join('');
            return resolve(hashHex);
        });
    })
}
//不一致の表示と計算は別で行う必要がある
function sha256(){
	var input1 = nowPass.value;
	console.log(input1)
	var input2 = challenge.value;
	//promissの中身取り出して変数に格納したい
	input1 = async_digestMessage(input1)
	input1.then(function(value){
		input1 = value;
	})
	input2 = async_digestMessage(input1 + input2)
	input2.then(function(value){
		input2 = value;
	})
	/*ここはsubmit時
	var password = document.getElementById("nowPass");
	password.value = input2;*/
}
/*window.addEventListener('DOMContentLoaded', () => {
	sha256();
});*/
window.addEventListener('DOMContentLoaded', function(){
	var form = document.getElementById("form");
	form.addEventListener("submit",function(){
		sha256();
	})
});