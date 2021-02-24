var initdata = new Vue({
	el: '.cont',
	data: {
		itemValue:'',
		title:'',
		typeList:[{
			typeCode:"jcxm",typeName:"检测项目"
		},{
			typeCode:"zlfa",typeName:"治疗方案"
		}],
		tableData: [{
				title: 'jcxm',
				itemCode: "001",
				itemValue: "高血压治疗"
			},
			{
				title: 'jcxm',
				itemCode: "002",
				itemValue: "糖尿病治疗"
			},
			{
				title: 'zlfa',
				itemCode: "z001",
				itemValue: "活性检测治疗"
			}
		],
		currentPage: 1,
		pageSize: 10,
		totalNum: '',
		dialogVisible: false,
		examclick: true,
		form: {
			id: '',
			name: '',
			type: '',
			key: '',
			value: ''
		},
		rules: {
			title: [{
					required: true,
					message: '请选择类别',
					trigger: 'blur'
				}
			],
			itemCode: [{
				required: true,
				message: '请输入键',
				trigger: 'blur'
			}],
			itemValue: [{
				required: true,
				message: '请输入值',
				trigger: 'blur'
			}]
		}
	},
	mounted: function() {
		var that = this;
		this.loadtable();
	},
	methods: {
		loadtable: function() {
			var that = this;
			axios.post(serverurl + 'api/dict/getPageList?itemValue=' + this.itemValue + "&title=" + this.title + '&pageNum=' + this.currentPage +
				'&pageSize=' + this.pageSize).then(function(res) {
				that.totalNum = res.data.data.total;
				that.tableData = res.data.data.pageData;
			});
		},
		
		handleadd: function() {
			this.form={title:"",itemCode:"",itemValue:""};
			this.dialogVisible = true;
			this.examclick = true;
		},
		addparam: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					axios({
						method: 'post',
						url: serverurl + 'api/dict/addHspBaseinfo',
						params: that.form
					}).then(
						function(res) {
							if (res.data.code == 500) {
								that.$message({
									type: 'info',
									message: res.data.msg
								});
								return;
							}
							that.loadtable();
							that.dialogVisible = false;
						});
				} else {
					return false;
				}
			});

		},
		// 关闭弹出框
		closeDialog: function() {
			this.form = {};
		},
		handleEdit: function(index, row) {
			this.form = Object.assign({}, row);
			this.dialogVisible = true;
			this.examclick = false;
		},
		save: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					axios({
						method: 'post',
						url: serverurl + 'api/dict/save',
						data: that.form
					}).then(
						function(res) {
							if (res.data.code == 500) {
								that.$message({
									type: 'info',
									message: res.data.msg
								});
								return;
							}
							that.loadtable();
							that.dialogVisible = false;
						});
				} else {
					return false;
				}
			});

		},
		handleDelete: function(index, row) {
			var that = this;
			this.$confirm('您确定要删除该记录吗?', '提示', {
				cancelButtonText: '取消',
				confirmButtonText: '确定',
				type: 'warning'
			}).then(function() {
				axios.get(serverurl + 'paApi/hspBaseinfo/deleteHspBaseinfo?id=' + row.id).then(
					function(res) {
						if (res.data.code == 0) {
							that.loadtable();
							that.$message({
								type: 'success',
								message: '删除成功'
							});
						}
					});
		
			}).catch(function() {
				that.$message({
					type: 'info',
					message: '已取消删除'
				});
			});
		},
		handleSizeChange: function(val) {
			this.pageSize = val;
			this.currentPage = 1;
			this.loadtable();
		},
		handleCurrentChange: function(val) {
			this.currentPage = val;
			this.loadtable();
		},
		changeTitle(typeCode){
			for(var i=0;i<this.typeList.length;i++){
				if(this.typeList[i].typeCode==typeCode){
					return this.typeList[i].typeName;
				}
			}
		}
	}
});
