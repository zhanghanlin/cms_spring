$(function() {
	var tree = {
		menu : function() {
			$.getJSON('/menu/tree', function(obj) {
				if (obj && obj.childNode) {
					var html = '';
					$.each(obj.childNode, function(i, o) {
						var subChild = o.childNode;
						html += '<li><span>' + o.node.name + '</span>';
						html += tree.subTree(subChild);
						html += '</li>';
					});
					$('#menuManageTree').append(html);
					tree.treeView($('#menuManageTree'));
				}
			});
		},
		subTree : function(obj) {
			var html = '';
			if (obj && obj.length > 0) {
				html += '<ul>';
				$.each(obj, function(j, o) {
					html += '<li><span>'+ o.node.name + '</span>';
					html += tree.subTree(o.childNode);
					html += '</li>';
				});
				html += '</ul>';
			}
			return html
		},
		treeView : function(dom) {
			dom.treeview({
				animated : "fast",
				control: "#treecontrol",
				unique : true,
				persist : "cookie",
				toggle : function() {
					window.console && console.log("%o was toggled", this);
				}
			});
		}
	}
	tree.menu();
});
