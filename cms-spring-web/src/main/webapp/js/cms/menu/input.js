$(function() {
	var menuInput = {
		editSelect : function() {
			var pCode = $('#parentCode').val();
			if (parseInt(pCode) == 0) {
				return false;
			}
			var level = pCode.length / 3 + 1;
			for ( var i = 1; i < level; i++) {
				var _code = pCode.substr(0, (i - 1) * 3);
				if (!_code)
					_code = 0;
//				$.ajax({
//					url : '/menu/p/' + _code,
//					type : 'GET',
//					async : false,
//					dataType : 'JSON',
//					success : function(obj) {
//						$('#menuDirectory').append(
//								menuInput.ajaxSelectHtml(obj, i));
//						$('#menu' + i).select2({
//							placeholder : "请选择"
//						});
//						
//					}
//				});
				$.ajaxSettings.async = false;
				menuInput.initSelect(_code);
				$('#menu' + i).val(pCode.substr(0, i * 3)).trigger('change');
			}
		},
		ajaxSelectHtml : function(obj, level) {
			var html = '<div class="col-sm-2 menuDiv"><select class="form-control select" id="menu'
					+ level + '"><option></option>';
			$.each(obj, function(i, o) {
				html += '<option value="' + o.code + '">' + o.name
						+ '</option>';
			});
			html += '</select></div>';
			return html;
		},
		initSelect : function(code) {
			var level = code.length / 3 + 1;
			$.getJSON('/menu/p/' + code, function(obj) {
				while (level <= $('.menuDiv').length) {
					$('.menuDiv:last').remove();
				}
				$('#menuDirectory')
						.append(menuInput.ajaxSelectHtml(obj, level));
				$('#menu' + level).select2({
					placeholder : "请选择"
				});
			});
		},
		initLevel : function() {
			$.getJSON('/menu/maxLevel', function(obj) {
				var html = '';
				for ( var i = 1; i <= obj.data + 1; i++) {
					html += '<option value="' + i + '">Level  - ' + i
							+ '</option>';
				}
				$('#menuLevel').html(html);
				menuInput.selectLevel()
			});
		},
		selectLevel : function() {
			$('#menuLevel').change(function() {
				$('div.menuDiv').remove();
				var level = $(this).val();
				if (level > 1) {
					menuInput.initSelect(0);
				}
			});
		},
		selectMenu : function() {
			$('#menuDirectory').delegate('.select', 'change', function() {
				var code = $(this).val();
				$('#parentCode').val(code);
				if (!code) {
					return false;
				}
				var level = code.length / 3 + 1;
				if (level == $('#menuLevel').val()) {
					return false;
				}
				if ($('#menu' + level).length > 0) {
					$('#menu' + level).parent().remove();
				}
				menuInput.initSelect(code);
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
		}
	};
	if (parseInt($('#id').val()) > 0) {
		menuInput.editSelect();
	}
	menuInput.initLevel();
	menuInput.selectMenu();
	menuInput.iconBlur();
});