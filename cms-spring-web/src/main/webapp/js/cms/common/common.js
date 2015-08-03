$(function() {
	var common = {
		initLeftTree : function() {
			var breadcrumb = common.pageBreadcrumb();
			$
					.getJSON(
							'/menu/tree',
							function(obj) {
								if (obj.hasChild) {
									var html = '';
									$
											.each(
													obj.childNode,
													function(i, o) {
														var cls = '';
														if (o.hasChild) {
															cls = 'treeview';
														}
														if (breadcrumb[0] == o.node.name) {
															cls += ' active';
														}
														html += '<li class="'
																+ cls
																+ '">\
										<a href="###">\
											<i class="fa '
																+ o.node.icon
																+ '"></i>\
											<span>'
																+ o.node.name
																+ '</span>\
											<i class="fa fa-angle-left pull-right"></i>\
										</a>';
														html += common
																.subLeftTree(o,
																		breadcrumb);
													});
									$('.sidebar-menu').append(html);
								}
							});
		},
		subLeftTree : function(obj, breadcrumb) {
			var html = '';
			if (obj.hasChild) {
				var cls = '';
				if (breadcrumb[obj.level - 1] == obj.node.name) {
					cls = ' active';
				}
				html += '<ul class="treeview-menu ' + cls + '">';
				$.each(obj.childNode, function(j, o) {
					var subCls = '';
					if (breadcrumb[o.level - 1] == o.node.name) {
						subCls = ' active';
					}
					html += '<li class="' + subCls + '"><a href="'
							+ o.node.link + '"><i class="fa ' + o.node.icon
							+ '"></i>' + o.node.name;
					if (o.childNode.length > 0) {
						html += '<i class="fa fa-angle-left pull-right"></i>';
					}
					html += '</a>';
					html += common.subLeftTree(o);
					html += '</li>';
				});
				html += '</ul>';
			}
			return html;
		},
		pageBreadcrumb : function() {
			return $('.content-header ol li').map(function() {
				if ($(this).index() > 0) {
					return $(this).text();
				}
			}).get();
		}
	}
	common.initLeftTree();
});