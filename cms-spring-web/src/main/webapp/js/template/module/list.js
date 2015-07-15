$(function() {	
	$('a.delete').click(function(){
		var _this = $(this);
		$.ajax({
			url : '/template/module/delete/' + _this.attr('tid'),
			type : 'POST',
			dataType : 'text',
			success : function(obj) {
				if (obj * 1 > 0) {
					_this.parents('tr').remove();
				}
			}
		});
	});
})