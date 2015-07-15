var Base = Base || {
	domain : "http://127.0.0.1",
	js_domain : "http://127.0.0.1"
};
Base.include = {
	css : function(file, func) {
		var h = document.getElementsByTagName('head')[0];
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.type = 'text/css';
		link.href = file;
		h.appendChild(link);
		if (navigator.userAgent.indexOf('MSIE') < 0) {
			// 非ie 浏览器css加在完成后document.styleSheets.length数量会增加1
			var css_count = document.styleSheets.length;
			var ld = setInterval(function() {
				if (document.styleSheets.length > css_count) {
					typeof (func) == 'function' && func();
					clearInterval(ld);
				}
			})
		} else {
			link.onreadystatechange = function() {
				if ('loaded' == link.readyState
						|| 'complete' == link.readyState) {
					typeof (func) == 'function' && func();
				}
			}
		}
	},
	js : function(file, func) {
		var h = document.getElementsByTagName('head')[0];
		var link = document.createElement('script');
		link.language = 'javascript';
		link.type = 'text/javascript';
		if (document.all) {
			link.onreadystatechange = function() {
				if ('complete' == link.readyState
						|| 'loaded' == link.readyState) {
					typeof (func) == 'function' && func();
				}
			}
		} else {
			link.onload = function() {
				typeof (func) == 'function' && func();
			}
		}
		link.src = file;
		h.appendChild(link);
	}
};
Base.timer = {
	timerHandle : null,
	run : function(func, time) {
		if ('undefined' == time) {
			time = 1000;
		}
		this.timerHandle = setInterval(function() {
			typeof (func) == 'function' && func();
		}, time);
	}
};
Base.js = {
	compare : function(fobj, sobj) {
		if (fobj == sobj) {
			return true;
		}
		var flength = 0;
		var slength = 0;
		for ( var ele in fobj) {
			flength++;
		}
		for ( var ele in sobj) {
			slength++;
		}
		if (flength != slength) {
			return false;
		}
		if (fobj.constructor == sobj.constructor) {
			for ( var ele in fobj) {
				if ('object' == typeof fobj[ele]) {
					if (!this.compare(fobj[ele], sobj[ele])) {
						return false;
					}
				} else if ('function' == typeof fobj[ele]) {
					if (fobj[ele].toString() != sobj[ele].toString()) {
						return false;
					}
				} else if (fobj[ele] != sobj[ele]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	},// 比较两个js对象是否相等
	randArray : function(array) {
		return array[Math.floor(Math.random() * array.length)];
	}// 随机获取数组值
};

/**
 * Cookie
 */
Base.cookie = {
	get : function(name, type) {
		var cookies = document.cookie.split('; ');
		var gets = [];
		var temp;
		if ('' == type || 'undefined' == typeof type) {
			for ( var i = 0; i < cookies.length; i++) {
				temp = cookies[i].split('=');
				gets[temp[0]] = unescape(temp[1]);
			}
			if (name) {
				return gets[name];
			} else {
				return '';
			}
		} else {
			var tempcookie = '';
			for (i = 0; i < cookies.length; i++) {
				if (cookies[i].indexOf(type + '=') > -1) {
					tempcookie = cookies[i].replace(type + '=', '').split('&');
					for ( var x = 0; x < tempcookie.length; x++) {
						temp = tempcookie[x].split('=');
						gets[temp[0]] = unescape(temp[1]);
					}
				}
			}
			if (name) {
				return gets[name];
			} else {
				return '';
			}
		}
	},
	set : function(name, value, expires, path, domain, secure) {
		if (!name || !value) {
			return false;
		}
		if ('' == name || '' == value) {
			return false;
		}
		var today = new Date();
		if (expires) {
			if (/^[0-9]+$/.test(expires)) {
				expires = new Date(today.getTime() + expires * 1000)
						.toGMTString();
			} else if (!/^wed, d{2} w{3} d{4} d{2}:d{2}:d{2} GMT$/
					.test(expires)) {
				expires = undefined;
			}
		} else {
			expires = new Date(today.getTime() + 3600000 * 24 * 365)
					.toGMTString();
		}
		var cookies = name + '=' + escape(value) + ';'
				+ ((expires) ? ' expires=' + expires + ';' : '')
				+ ((path) ? 'path=' + path + ';' : '')
				+ ((domain) ? 'domain=' + domain + ';' : '')
				+ ((secure && secure != 0) ? 'secure' : '');
		if (cookies.length < 4096) {
			document.cookie = cookies;
			return true;
		} else {
			return false;
		}
	},
	del : function(name, path, domain) {
		if (!name || !this.Get(name)) {
			return false;
		}
		document.cookie = name + '=;' + ((path) ? 'path=' + path + ';' : '')
				+ ((domain) ? 'domain=' + domain + ';' : '')
				+ 'expires=Thu, 01-Jan-1970 00:00:01 GMT;';
		return true;
	}
};

/**
 * String
 */
Base.string = {
	trim : function(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	enUnicode : function(str) {
		return escape(str).toLocaleLowerCase().replace(/%u/gi, '\\u');
	},
	deUnicode : function(str) {
		return unescape(str.replace(/\\u/gi, '%u'));
	},
	/**
	 * 补全字符串
	 * 
	 * @param <string>
	 *            str 需要补全的字符
	 * @param <int>
	 *            len 补全至多少位
	 * @param <string>
	 *            type 在前补全/后补全
	 * @param <string>
	 *            pChar 自定义补全的字符
	 */
	parseStandard : function(str, len, type, pChar) {
		var c = len - str.length;
		var isAfter = false;
		var char = '0';
		if (pChar) {
			char = pChar;
		}
		if (type && 'after' == type) {
			isAfter = true;
		}
		if (c * 1 > 0) {
			for ( var i = 0; i < c; i++) {
				if (isAfter) {
					str = str + char;
				} else {
					str = char + str;
				}
			}
		}
		return str;
	}
};

/**
 * Date
 */
Base.date = {
	format : function(t, fmt) {
		if (!t) {
			t = new Date();
		}
		if (!fmt) {
			fmt = 'yyyy-MM-dd hh:mm:ss'
		}
		var o = {
			'M+' : t.getMonth() + 1, // 月份
			'd+' : t.getDate(), // 日
			'h+' : t.getHours(), // 小时
			'm+' : t.getMinutes(), // 分
			's+' : t.getSeconds(), // 秒
			'q+' : Math.floor((t.getMonth() + 3) / 3), // 季度
			'S' : t.getMilliseconds()
		// 毫秒
		}
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (t.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp('(' + k + ')').test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	},
	/**
	 * 日期转时间戳
	 * 
	 * @param <string>
	 *            <object> obj 时间对象或者时间格式字符串
	 */
	toUnix : function(obj) {
		var type = typeof (obj);
		var time = 0;
		var date;
		if ('object' == type) {
			date = obj;
		} else if ('string' == type) {
			obj = obj.replace(/-/g, '/');
			date = new Date(obj);
		} else {
			date = new Date();
		}
		time = date.getTime();
		var time_str = new String(time);
		if (time_str == 10) {
			time = time * 1000;
		}
		return parseInt(time);

	},
	/**
	 * 时间戳转换日期
	 * 
	 * @param <int>
	 *            unixTime 待时间戳(秒)
	 * @param <string>
	 *            fmt 返回时间格式
	 */
	unixToDate : function(unixTime, fmt) {
		if (parseInt(unixTime).toString().length == 10) {
			unixTime = unixTime * 1000
		}
		var time = new Date(unixTime);
		return this.format(time, fmt);
	}
}

/**
 * JSON
 */
Base.json = {
	toString : function(object) {
		var arr = [];
		var fmt = function(obj) {
			if ('object' == typeof obj && obj != null) {
				return this.toString(obj);
			}
			return /^(string|number)$/.test(typeof obj) ? '\'' + obj + '\''
					: obj;
		}
		for ( var i in object) {
			arr.push('\'' + i + '\':' + fmt(object[i]));
		}
		return '{' + arr.join(',') + '}';
	}
};
/**
 * LOG
 */
Base.log = {
	info : function(info, func) {
		console.info(info);
	},
	error : function(error, func) {
		console.error(error);
	}
}

// Init
Base.include.css("/css/ace.css");
Base.include.css("/css/bootstrap.min.css");
Base.include.css("/css/dashboard/dashboard.css");
Base.include.js("/js/jquery.form.js");
Base.include.js("/js/login/login.js");
Base.include.js("/js/bootstrap.min.js", function() {
	var _type = $('#_type').val();
	if (_type) {
		$('#' + _type).addClass('active');
		var id = $('#' + _type).parent().attr('id');
		$('#' + id).collapse('show');
	}
});