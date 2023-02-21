window.onload = function() {
	const tdProducts = document.getElementsByClassName('products');
	for (let i = 0; i < tdProducts.length; i++) {
		tdProducts[i].addEventListener("click", function() {
			var productId = this.id;
			var input = document.createElement("input");
			input.type = "hidden"
			input.name = "productId";
			input.value = productId;
			var form = document.createElement("form");
			form.method = "get";
			form.action = "FromIndexServlet";
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();
		});
	}
};