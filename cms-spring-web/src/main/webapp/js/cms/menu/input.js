$(function() {
	var menuInput = {
		initSelect : function() {
			$.getJSON('/menu/simple/tree', function(data) {
				var setting = {
					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						onClick : function(e, treeId, treeNode) {
							var zTree = $.fn.zTree.getZTreeObj("menuTree");
							var nodes = zTree.getSelectedNodes();
							$('#selectMenu').val(nodes[0].name);
							$('#parentId').val(nodes[0].id);
							menuInput.hideMenu();
							return false;
						}
					}
				};
				$.fn.zTree.init($("#menuTree"), setting, data);
				var zTree = $.fn.zTree.getZTreeObj("menuTree");
				var defaultNode = zTree.getNodeByParam('id', $('#parentId')
						.val());
				if (defaultNode) {
					$('#selectMenu').val(defaultNode.name);
					zTree.selectNode(defaultNode);
				}
			});
			$('#selectMenu').click(function() {
				$("#selectMenuCont").slideDown("fast");
				$("body").bind("mousedown", menuInput.onBodyDown);
			});
		},
		iconBlur : function() {
			if ($('#icon').val()) {
				$('#icon').parent().next().find('i').addClass('fa');
				$('#icon').parent().next().find('i').addClass($('#icon').val());
			}
			$('#icon').blur(function() {
				$(this).parent().next().find('i').removeClass();
				$('#icon').parent().next().find('i').addClass('fa');
				$(this).parent().next().find('i').addClass($(this).val());
			})
		},
		ajaxForm : function() {
			var options = {
				type : "POST",
				dataType : "json",
				success : function(json) {// 表单提交成功回调函数
					$('#modal').modal('hide');
				},
				error : function(err) {
					alert("表单提交异常！" + err.msg);
				}
			};
			$(".form-horizontal").ajaxForm(options);
		},
		hideMenu : function() {
			$("#selectMenuCont").fadeOut("fast");
			$("body").unbind("mousedown", menuInput.onBodyDown);
		},
		onBodyDown : function(event) {
			if (!(event.target.id == "selectMenu"
					|| event.target.id == "selectMenuCont" || $(event.target)
					.parents("#selectMenuCont").length > 0)) {
				menuInput.hideMenu();
			}
		}
	};
	menuInput.ajaxForm();
	menuInput.iconBlur();
	menuInput.initSelect();
});