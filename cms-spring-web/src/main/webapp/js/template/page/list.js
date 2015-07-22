$(function() {
	$('table').delegate('a.delete','click',function(){
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
	function list(){
		var html = '';
		$.ajax({
			url : '/template/page/api/list',
			type : 'GET',
			dataType : 'JSON',
			success : function(obj){
				if (obj.code * 1 == 200) {
					data = obj.data;
					$.each(data,function(i,o) {
						html += '<tr>\
							<th scope="row">'+o.id+'</th>\
							<td>'+o.name+'</td>\
							<td>'+o.fileName+'</td>\
							<td>'+o.path+'</td>\
							<td>'+o.createdAt+'</td>\
							<td>\
								<a class="btn btn-default btn-xs" href="edit/'+o.id+'" role="button">\
									<i class="glyphicon glyphicon-cog"></i>\
								</a>\
								<a class="btn btn-default btn-xs delete" href="###" tid="'+o.id+'" role="button">\
									<i class="glyphicon glyphicon-trash"></i>\
								</a>\
							</td>\
						</tr>'
					});
					$('table tbody').html(html);
				}
			}
		})
	}
	list();
})