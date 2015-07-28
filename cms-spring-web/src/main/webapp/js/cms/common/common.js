$(function() {
	$.getJSON('/menu/tree',function(obj){
		if(obj && obj.childNode){
			var html = '';
			$.each(obj.childNode,function(i,o){
				var subChild = o.childNode;
				var cls = '';
				if(subChild && subChild.length > 0){
					cls = 'treeview';
				}
				html += '<li class="'+cls+'">\
							<a href="###">\
								<i class="fa '+o.node.icon+'"></i>\
								<span>'+o.node.name+'</span>\
								<i class="fa fa-angle-left pull-right"></i>\
							</a>';
				html += menuSubTree(subChild);
			});
			$('.sidebar-menu').append(html);
		}
	});
	
	function menuSubTree(obj) {
		var html = '';
		if(obj && obj.length > 0) {
			html += '<ul class="treeview-menu">';
			$.each(obj,function(j,o){
				html += '<li><a href="'+o.node.link+'"><i class="fa fa-circle-o"></i>'+o.node.name;
				if(o.childNode.length > 0){
					html += '<i class="fa fa-angle-left pull-right"></i>';
				}
				html += '</a>';
				html += menuSubTree(o.childNode);
				html += '</li>';
			});		
			html += '</ul>';
		}
		return html
	}
});