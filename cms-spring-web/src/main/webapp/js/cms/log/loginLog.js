$(function() {
	var loginLog = {
		list : function() {
			$('#loginLogTable').ajaxTable({
				url : '/loginLog/list',
				paging : true,
				pageSize : 10,
				columns : [ 'loginAccount', 'loginName', 'ip', 'createdAt' ]
			});
		}
	};
	loginLog.list();
});