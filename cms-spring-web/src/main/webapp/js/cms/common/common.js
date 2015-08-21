$(function() {
	var common = {
		initLeftTree : function() {
			var breadcrumb = common.pageBreadcrumb();
			$
					.getJSON(
							'/menu/tree',
							function(obj) {
								if (obj.hasChild) {
									var str = [];
									$
											.each(
													obj.children,
													function(i, o) {
														var cls = '';
														if (o.hasChild) {
															cls = 'treeview';
														}
														if (breadcrumb[0] == o.node.name) {
															cls += ' active';
														}
														str.push('<li class="');
														str.push(cls);
														str
																.push('"><a href="###"><i class="fa ');
														str.push(o.node.icon);
														str
																.push('"></i><span>')
														str.push(o.node.name);
														str
																.push('</span><i class="fa fa-angle-left pull-right"></i></a>');
														str
																.push(common
																		.subLeftTree(
																				o,
																				breadcrumb));
													});
									$('.sidebar-menu').append(str.join(''));
								}
							});
		},
		subLeftTree : function(obj, breadcrumb) {
			var str = [];
			if (obj.hasChild) {
				var cls = '';
				var style = 'display : none;';
				if (breadcrumb && breadcrumb[obj.level - 1] == obj.node.name) {
					cls = 'menu-open';
					style = 'display : block;';
				}
				str.push('<ul class="treeview-menu ');
				str.push(cls);
				str.push(' " style="');
				str.push(style)
				str.push('">');
				$
						.each(
								obj.children,
								function(j, o) {
									var subCls = '';
									if (breadcrumb
											&& breadcrumb[o.level - 1] == o.node.name) {
										subCls = 'active';
									}
									str.push('<li class="');
									str.push(subCls);
									str.push('"><a href="');
									str.push(o.node.link);
									str.push('"><i class="fa ');
									str.push(o.node.icon);
									str.push('"></i>');
									str.push(o.node.name);
									if (o.children.length > 0) {
										str
												.push('<i class="fa fa-angle-left pull-right"></i>');
									}
									str.push('</a>');
									str.push(common.subLeftTree(o));
									str.push('</li>');
								});
				str.push('</ul>');
			}
			return str.join('');
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