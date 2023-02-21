$(".custom-select").each(function() {
	var classes = $(this).attr("class"),
		id = $(this).attr("id"),
		name = $(this).attr("name");
	var template = '<div class="' + classes + '">';
	template += '<span class="custom-select-trigger">' + $(this).attr("placeholder") + '</span>';
	template += '<div class="custom-options">';
	$(this).find("option").each(function() {
		template += '<span class="custom-option ' + $(this).attr("class") + '" data-value="' + $(this).attr("value") + '">' + $(this).html() + '</span>';
	});
	template += '</div></div>';

	$(this).wrap('<div class="custom-select-wrapper"></div>');
	$(this).hide();
	$(this).after(template);
});
$(".custom-option:first-of-type").hover(function() {
	$(this).parents(".custom-options").addClass("option-hover");
}, function() {
	$(this).parents(".custom-options").removeClass("option-hover");
});
$(".custom-select-trigger").on("click", function() {
	$('html').one('click', function() {
		$(".custom-select").removeClass("opened");
	});
	$(this).parents(".custom-select").toggleClass("opened");
	event.stopPropagation();
});
$(".custom-option").on("click", function() {
	$(this).parents(".custom-select-wrapper").find("select").val($(this).data("value"));
	$(this).parents(".custom-options").find(".custom-option").removeClass("selection");
	$(this).addClass("selection");
	$(this).parents(".custom-select").removeClass("opened");
	$(this).parents(".custom-select").find(".custom-select-trigger").text($(this).text());
});

window.onload = function() {
	const tdProducts = document.getElementsByClassName('tdProduct');
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

 $('.custom-option').click(function () {
	    //選択したoptionのvalueを取得
	    var val = this.getAttribute("data-value");
	    //先頭に#を付けてvalueの値をidに変換
	    var optionId = '#' + val;
	    //一度すべてのブロックを非表示にする
	    $('#initial').hide();
	    $('#name').hide();
	    $('#maker').hide();
	    $('#price').hide();
	    //選択したブロックのみを表示
	    $(optionId).show();
	});


