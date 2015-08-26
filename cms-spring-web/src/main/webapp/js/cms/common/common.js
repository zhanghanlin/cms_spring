$(function() {
	var common = {
		leftTree : function() {
			$.getJSON('/menu/tree', function(data) {
				$('.sidebar-menu').append(common.treeHtml(data[0], data));
			});
		},
		treeHtml : function(arr, data) {
			if (!arr)
				return false;
			var str = [];
			$.each(arr, function(i, o) {
				if (data[o.id]) {
					str.push('<li class="treeview">')
				} else {
					str.push('<li>');
				}
				str.push('<a href="');
				str.push(o.link);
				str.push('"><i class="fa ');
				str.push(o.icon);
				str.push('"></i><span>')
				str.push(o.name);
				str.push('</span>');
				str.push('<i class="fa fa-angle-left pull-right">');
				str.push('</i></a>');
				if (data[o.id]) {
					str.push(common.subTreeHtml(data[o.id], data,
							'treeview-menu'));
				}
				str.push('</li>');
			});
			return str.join('');
		},
		subTreeHtml : function(arr, data) {
			if (!arr)
				return false;
			var str = [];
			str.push('<ul class="treeview-menu">');
			$.each(arr, function(i, o) {
				str.push('<li><a href="###" link="');
				str.push(o.link);
				str.push('"><i class="fa ');
				str.push(o.icon);
				str.push('"></i>');
				str.push(o.name);
				if (data[o.id]) {
					str.push('<i class="fa fa-angle-left pull-right">');
				}
				str.push('</a>');
				if (data[o.id]) {
					str.push(common.subTreeHtml(data[o.id], data,
							'treeview-menu'));
				}
				str.push('</li>');
			});
			str.push('</ul>');
			return str.join('');
		}
	}
	common.leftTree();
});