$(function() {
	var tree = {
		tableHead : function() {
			return '<tr>\
			<td>#</td>\
			<td>Name</td>\
			<td>Note</td>\
			<td>Code</td>\
			<td>Link</td>\
			<td>Icon</td>\
			<td>Status</td>\
		</tr>';
		},
		parseObj : function(o) {
			var code = o.code;
			var dataId = '';
			while(code.length / 3 >= 1) {
				dataId += (code.substr(0,3) *　1) + '-';
				code = code.substr(3);
			}
			dataId = dataId.substr(0,dataId.length - 1);
			var parentId = dataId.substr(0,dataId.lastIndexOf('-'))
			return '<tr data-tt-id="'+dataId+'" data-tt-parent-id="'+parentId+'">\
						<td></td>\
						<td>' + o.name
					+ '</td>\
						<td>' + o.note + '</td>\
						<td>'
					+ o.code + '</td>\
						<td><a href="' + o.link + '">'
					+ o.link + '</a></td>\
						<td><i class="fa ' + o.icon
					+ '"></i></td>\
						<td>' + o.status
					+ '</td>\
					</tr>';
		},
		list : function() {
			$.getJSON('/menu/tree/_all', function(obj) {
				if (obj.hasChild) {
					var html = '';
					$.each(obj.childNode, function(i, o) {
						html += tree.parseObj(o.node);
						html += tree.child(o);
					});
					$('#menuTable').html(tree.tableHead() + html);
					$("#menuTable").treetable({expandable: true});
				}
			});
		},
		child : function(obj) {
			var html = '';
			if (obj.hasChild) {
				$.each(obj.childNode, function(j, o) {
					html += tree.parseObj(o.node);
					html += tree.child(o);
				});
			}
			return html;
		}
	};
	tree.list();
	

	
});