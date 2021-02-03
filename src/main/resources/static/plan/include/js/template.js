var initdata = new Vue({
	el: '.cont',
	data: {
		fullname:'',
		isShowSelect:false,
		isShowEdit: false,
		isAdd:true,
		tableData: [],
		currentPage: 1,
		pageSize: 10,
		totalNum: '',
		form: {
			patientid: '',
			fullname: '',
			sex: '',
			birthday: '',
			address: '',
			examItems:[]
		},
		activeName: 'first',
		rules: {
			name: [{
					required: true,
					message: '请输入名称',
					trigger: 'blur'
				},
				{
					max: 30,
					message: '最多输入30个字符'
				}
			],
			type: [{
					required: true,
					message: '请输入类型',
					trigger: 'blur'
				},
				{
					max: 30,
					message: '最多输入30个字符'
				}
			],
			tpl: [{
				required: true,
				message: '请选择模板',
				trigger: 'blur'
			}]
		}
	},
	mounted: function() {
		var that = this;
		this.loadtable();
	},
	methods: {
		handleadd: function() {
			//点击“新增”，显示客户选择列表，隐藏编辑窗口
			this.isShowSelect = true;
			this.isShowEdit = false;
		},
		selectedPat:function(patient){
			//选中一个客户，打开编辑窗口，并为form设置初始值
			this.isShowSelect = false;
			this.isShowEdit = true;
			this.form = Object.assign({
				examDate:new Date(),
				explantDate:new Date(),
				nurseName: "刘护士",
				expertName: "李专家",
				examItems: []
			}, patient);
			this.isAdd = true;
		},
		loadtable: function() {
			//----查询，根据姓名获取客户列表
			var that = this;
			var params = '?fullname=' + this.fullname + '&pageNum=' + this.currentPage +
			'&pageSize=' + this.pageSize;
			axios.get(serverurl + 'mock/planlist.json').then(function(res) {
				that.totalNum = res.data.data.total;
				that.tableData = res.data.data.pageData;
			});
		},
		// 关闭弹出框
		closeDialog: function() {
			this.form = Object.assign({examItems:[]}, {});
		},
		//编辑
		handleEdit: function(index, row) {
			//当前行记录赋值给form，显示编辑窗口
			this.form = Object.assign({examItems:[]}, row);
			this.isShowEdit = true;
			this.isadd = false;
		},
		saveEdit: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					axios({
						method: 'get',
						url: serverurl + 'mock/baseinfoadd.json',
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
							that.$message({
								type: 'success',
								message: "保存成功"
							});
							that.loadtable();
							that.isShowEdit = false;
						});
				} else {
					that.$message({
						type: 'warn',
						message: "请检查输入内容"
					});
					return false;
				}
			});

		},
		handleDelete: function(index, row) {
			//删除当前行
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
			//行数变化
			this.pageSize = val;
			this.currentPage = 1;
			this.loadtable();
		},
		handleCurrentChange: function(val) {
			//当前页码变化
			this.currentPage = val;
			this.loadtable();
		}
	}
});
