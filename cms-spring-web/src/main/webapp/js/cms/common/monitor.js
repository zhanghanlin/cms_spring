$(function() {
	var cpu = echarts.init(document.getElementById('cpu'));
	var jvm = echarts.init(document.getElementById('jvm'));
	var mem = echarts.init(document.getElementById('mem'));
	var line = echarts.init(document.getElementById('line'));
	var cpu_option = {
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			title : {
				show : true,
				offsetCenter : [ 0, "95%" ]
			},
			name : 'CPU使用率',
			type : 'gauge',
			detail : {
				formatter : '{value}%',
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto',
					fontWeight : 'bolder',
					fontSize : 21
				}
			},
			data : [ {
				value : 10,
				name : 'CPU使用率'
			} ],
			radius : [ 0, '95%' ],
			axisLine : { // 坐标轴线
				lineStyle : { // 属性lineStyle控制线条样式
					width : 3
				}
			},
			splitLine : {
				length : 10
			// 属性length控制线长
			}
		} ]
	};
	var jvm_option = {
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			title : {
				show : true,
				offsetCenter : [ 0, "95%" ]
			},
			name : 'JVM使用率',
			type : 'gauge',
			detail : {
				formatter : '{value}%',
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto',
					fontWeight : 'bolder',
					fontSize : 21
				}
			},
			data : [ {
				value : 10,
				name : 'JVM使用率'
			} ],
			radius : [ 0, '95%' ],
			axisLine : { // 坐标轴线
				lineStyle : { // 属性lineStyle控制线条样式
					width : 3
				}
			},
			splitLine : {
				length : 10
			// 属性length控制线长
			}
		} ]
	};
	var mem_option = {
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			title : {
				show : true,
				offsetCenter : [ 0, "95%" ]
			},
			name : '内存使用率',
			type : 'gauge',
			detail : {
				formatter : '{value}%',
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto',
					fontWeight : 'bolder',
					fontSize : 21
				}
			},
			data : [ {
				value : 10,
				name : '内存使用率'
			} ],
			radius : [ 0, '95%' ],
			axisLine : { // 坐标轴线
				lineStyle : { // 属性lineStyle控制线条样式
					width : 3
				}
			},
			splitLine : {
				length : 10
			// 属性length控制线长
			}
		} ]
	};
	var line_option = {
		grid : {
			x : 40,
			y : 30,
			x2 : 10,
			y2 : 35,
			borderWidth : 1,
			borderColor : "#FFFFFF"
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			data : [ 'CPU', 'JVM', 'Mem' ]
		},
		xAxis : [ {
			axisLabel : {
				rotate : 20
			},
			type : 'category',// 坐标轴类型，横轴默认为类目型'category'，纵轴默认为数值型'value'
			data : (function() {
				var now = new Date();
				var res = [];
				var len = 20;
				while (len--) {
					res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
					now = new Date(now - 2000);
				}
				return res;
			})()
		} ],
		yAxis : [ {
			min : 0,
			max : 100,
			axisLabel : {
				formatter : '{value}%'
			}
		} ],
		series : [ {
			name : 'CPU',
			type : 'line',
			data : queue('cpu')
		}, {
			name : 'JVM',
			type : 'line',
			data : queue('jvm')
		}, {
			name : 'Mem',
			type : 'line',
			data : queue('mem')
		} ]
	};

	var axisData;
	line.setOption(line_option, true);
	cpu.setOption(cpu_option, true);
	jvm.setOption(jvm_option, true);
	mem.setOption(mem_option, true);
	clearInterval(timeTicket);
	var timeTicket = setInterval(function() {
		$.ajax({
			url : '/monitor',
			type : 'GET',
			async : false,
			dataType : 'JSON',
			success : function(data) {
				cpu_option.series[0].data[0].value = data.cpuUsage;
				cpu.setOption(cpu_option, true);
				jvm_option.series[0].data[0].value = data.JvmUsage;
				jvm.setOption(jvm_option, true);
				mem_option.series[0].data[0].value = data.serverUsage;
				mem.setOption(mem_option, true);

				axisData = (new Date()).toLocaleTimeString()
						.replace(/^\D*/, '');
				//axisData = axisData.substr(axisData.indexOf(":") + 1);
				queue('cpu', data.cpuUsage);
				queue('jvm', data.JvmUsage);
				queue('mem', data.serverUsage);
				line.addData([ [ 0, // 系列索引
				data.cpuUsage, // 新增数据
				false, // 新增数据是否从队列头部插入
				false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				], [ 1, // 系列索引
				data.JvmUsage, // 新增数据
				false, // 新增数据是否从队列头部插入
				false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				], [ 2, // 系列索引
				data.serverUsage, // 新增数据
				false, // 新增数据是否从队列头部插入
				false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				axisData ] ]);
			},
			error : function(data) {
				window.location.href = '/login';
			}
		});
	}, 2000);

	function queue(name, data) {
		var res = window.localStorage[name];
		if (!res) {
			res = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
		} else {
			res = res.split(',');
		}
		if (!data) {
			return res;
		}
		res.shift();
		res.push(data);
		window.localStorage[name] = res;
		return res;
	}
});