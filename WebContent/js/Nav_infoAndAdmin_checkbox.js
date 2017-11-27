$(function() {
	//admin.html中checkbox默认样式更改
	$(".label1").click(function() { /*获取性别男选择框*/
		if ($(".label2").has("checked")) { /*如果女被选中*/
			$(".label2").removeClass("checked"); /*移除女焦点样式（焦点样式就是选中图片）。并且添加未选中样式（就是未选中图片）*/
		}
		if ($(".label1").has("checked")) {
			$(".label1").removeClass("checked");
		}
		$(".label1").addClass("checked");
	});
	$(".label2").click(function() { /*获取性别男选择框*/
		if ($(".label1").has("checked")) { /*如果女被选中*/
			$(".label1").removeClass("checked"); /*移除女焦点样式（焦点样式就是选中图片）。并且添加未选中样式（就是未选中图片）*/
		}
		$(".label2").addClass("checked");
	});

	//index.html中个人中心点击图片显示信息
	function stopPropagation(e) {
		if (e.stopPropagation)
			e.stopPropagation();
		else
			e.cancelBubble = true;
	}

	$(document).bind('click', function() {
		$('.info-user').css('display', 'none');
	});

	$('.info-img').bind('click', function(e) {
		$('.info-user').css('display', 'block');
		stopPropagation(e);
	});
});