function checkPassword(passwordCheck) {
	// 入力値取得
	var input1 = password.value;
	var input2 = passwordCheck.value;
	// パスワード比較
	if (input1 != input2) {
		passwordCheck.setCustomValidity("入力値が一致しません。");
	} else {
		passwordCheck.setCustomValidity('');
	}
};
window.addEventListener('DOMContentLoaded', () => {
	checkPassword(this);
});

