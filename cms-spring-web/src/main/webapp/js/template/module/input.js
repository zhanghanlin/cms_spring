$(function() {
	var editor = $("#controlEditor");
	var textEditor = $('#moduelData');
	var id = $('#id').val();
	var type = $('#moduleType').val();
	Base.include.css("/css/editor/font-awesome.min.css");
	Base.include.css("/css/editor/editor.css");
	Base.include.js("/js/editor/editor.js", function() {
		changeEdit();
	});

	var id = $('#id').val();
	var type = $('#moduleType').val();

	$('#submit').click(function() {
		var url = '/template/module/add';
		if (id) {
			url = '/template/module/update/' + id;
		}
		var data = getText();
		data = Base.string.enUnicode(data) || '';
		$('#data').val(data);
		$('#inputPage').ajaxSubmit({
			url : url,
			type : 'POST',
			dataType : 'JSON',
			success : function(obj) {
				Base.log.info('input submit is OK!');
			},
			error : function() {
				Base.log.error('input submit is error!');
			}
		});
		window.location.href = "/template/module/list";
	});

	$('#cancel').click(function() {
		window.location.href = document.referrer;
	});

	$('#moduleType').change(function() {
		type = $(this).val();
		changeEdit();
	});

	function changeEdit() {
		if (type * 1 == 1) {
			if ($('.Editor-container').length == 0) {
				editor.Editor();
			}
			textEditor.hide();
		} else {
			$('.Editor-container').remove();
			textEditor.show();
		}
		initEdit();
	}

	function initEdit() {
		if (!id)
			return false;
		$.ajax({
			url : '/template/module/get/' + id,
			type : 'GET',
			dataType : 'JSON',
			success : function(obj) {
				var data = Base.string.deUnicode(obj.data.data);
				setText(data);
			}
		});
	}
	function setText(data) {
		if (type * 1 == 1) {
			editor.Editor('setText', data);
		} else {
			textEditor.val(data);
		}
	}
	function getText() {
		if (type * 1 == 1) {
			return editor.Editor('getText');
		} else {
			return textEditor.val();
		}
	}
})