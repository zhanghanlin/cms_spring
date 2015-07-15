$(function() {
	var editor = $("#controlEditor");
	Base.include.css("/css/editor/font-awesome.min.css");
	Base.include.css("/css/editor/editor.css");
	Base.include.js("/js/editor/editor.js", function() {
		editor.Editor();
		initEdit();
	});
	var id = $('#id').val();
	$('#submit').click(function() {
		var url = '/template/page/add';
		if (id) {
			url = '/template/page/update/' + id;
		}
		var data = editor.Editor('getText');
		data = Base.string.enUnicode(data) || '';
		$('#data').val(data);
		$('#inputPage').ajaxSubmit({
			url : url,
			type : 'POST',
			success : function(obj) {
				Base.log.info('input submit is OK!');
			},
			error : function() {
				Base.log.error('input submit is error!');
			}
		});
		window.location.href = "/template/page/list";
	});

	$('#cancel').click(function() {
		window.location.href = document.referrer;
	});

	function initEdit() {
		if (!id)
			return false;
		$.ajax({
			url : '/template/page/get/' + id,
			type : 'GET',
			dataType : 'JSON',
			success : function(obj) {
				var data = Base.string.deUnicode(obj.data.data);
				editor.Editor('setText', data);
			}
		});
	}
})