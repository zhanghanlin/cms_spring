$(function() {
	$.getJSON('/menu/tree',function(obj){
		if(obj.hasChild){
			var html = '';
			$.each(obj.childNode,function(i,o){
				var cls = '';
				if(o.hasChild){
					cls = 'treeview';
				}
				html += '<li class="'+cls+'">\
							<a href="###">\
								<i class="fa '+o.node.icon+'"></i>\
								<span>'+o.node.name+'</span>\
								<i class="fa fa-angle-left pull-right"></i>\
							</a>';
				html += menuSubTree(o);
			});
			$('.sidebar-menu').append(html);
		}
	});
	
	function menuSubTree(obj) {
		var html = '';
		if(obj.hasChild) {
			html += '<ul class="treeview-menu">';
			$.each(obj.childNode,function(j,o){
				html += '<li><a href="'+o.node.link+'"><i class="fa fa-circle-o"></i>'+o.node.name;
				if(o.childNode.length > 0){
					html += '<i class="fa fa-angle-left pull-right"></i>';
				}
				html += '</a>';
				html += menuSubTree(o);
				html += '</li>';
			});		
			html += '</ul>';
		}
		return html
	}
});