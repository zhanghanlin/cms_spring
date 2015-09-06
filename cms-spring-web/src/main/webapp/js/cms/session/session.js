$(function() {
	var session = {
		forceLogout : function() {
			$('.forceLogout').click(
					function() {
						var _this = $(this)
						$.ajax({
							url : '/sessions/' + _this.attr('sessionId')
									+ '/forceLogout',
							dataType : 'JSON',
							success : function(data) {
								if (data.code == 200) {
									_this.parent().prev().html('是');
									_this.remove();
								}
							}
						});
					});
		}
	};
	session.forceLogout();
});