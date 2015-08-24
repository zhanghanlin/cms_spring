$(function() {
	var common = {
		initLeftTree : function() {
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
																		.subLeftTree(o));
													});
									$('.sidebar-menu').append(str.join(''));
								}
							});
		},
		subLeftTree : function(obj) {
			var str = [];
			if (obj.hasChild) {
				str.push('<ul class="treeview-menu">');
				$
						.each(
								obj.children,
								function(j, o) {
									str.push('<li><a href="###" link="');
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
		}
	}
	common.initLeftTree();
});