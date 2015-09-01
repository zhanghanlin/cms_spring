(function($) {
	$.button = function(options) {
		var settings = {
			url : '',
			name : '',
			clazz : 'btn btn-default btn-xs',
			modal : {
				enable : true,
				toggle : 'modal',
				target : '#modal'
			},
			param : []
		}
		var opts = $.extend(settings, options);
		var str = [];
		str.push('<a role="button" class="');
		str.push(opts.clazz);
		str.push('" ');
		if (opts.url) {
			str.push('href="');
			str.push(opts.url);
			str.push('" ');
		}
		if (opts.modal.enable) {
			str.push('data-toggle="')
			str.push(opts.modal.toggle);
			str.push('" data-target="');
			str.push(opts.modal.target);
			str.push('"');
		}
		if (opts.param) {
			$.each(opts.param, function(i, o) {
				str.push(o);
			});
		}
		str.push('>');
		str.push(opts.name);
		str.push('</a>');
		return str.join('');
	}
})(jQuery);