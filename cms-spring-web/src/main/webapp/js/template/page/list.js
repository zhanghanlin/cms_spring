$(function() {	
	$('a.delete').click(function(){
		var _this = $(this);
		$.ajax({
			url : '/template/page/api/delete/' + _this.attr('tid'),
			type : 'POST',
			dataType : 'JSON',
			success : function(obj) {
				if (obj.code * 1 == 200) {
					_this.parents('tr').remove();
				}
			}
		});
	});
})