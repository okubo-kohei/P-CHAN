window.onload = function() {
	let send = document.getElementById('send');
	send.addEventListener("click", function() {
		let attention = document.getElementById('attention');
		attention.innerText = "※送信しました"
	});
};