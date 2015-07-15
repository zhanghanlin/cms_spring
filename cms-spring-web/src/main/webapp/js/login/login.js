var passport = {
	login : function() {
		$('#navbar').delegate('button#login-btn','click',function() {
			$('#loginForm').ajaxSubmit({
				url : '/login/valid',
				type : 'POST',
				dataType:'JSON',
				success : function(obj) {
					if (obj.code * 1 == 200) {
						$('#loginForm').remove();
						$('#navbar').html(passport.loginHtml(obj.data));
					}
				}
			});
		})
	},
	logout : function() {
		$('#navbar').delegate('a#logout','click',function() {
			$('#navbar > ul.nav').remove();
			$('#navbar').html(passport.logoutHtml());
		});
	},
	loginHtml : function(t) {
		return '<ul class="nav navbar-nav navbar-right"><li class="dropdown">\
					<a id="dropLogin" href="###" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">\
					'+t.userName+'<span class="caret"></span></a>\
					<ul class="dropdown-menu" aria-labelledby="dropLogin">\
						<li><a href="###">管理</a></li>\
						<li><a href="###" id="logout">退出</a></li>\
					</ul>\
				</li></ul>'
	},
	logoutHtml : function() {
		return '<form class="navbar-form navbar-right" id="loginForm">\
					<div class="form-group">\
						<input type="text" name="userName" placeholder="UserName" class="form-control">\
					</div>\
					<div class="form-group">\
						<input type="password" name="password" placeholder="Password" class="form-control">\
					</div>\
					<button type="button" id="login-btn" class="btn btn-success">登陆</button>\
				</form>';
	}
}
passport.login();
passport.logout();