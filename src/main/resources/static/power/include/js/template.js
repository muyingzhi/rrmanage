var initdata = new Vue({
	el: '.cont',
	data: {
		fullname:'',
		roleList:[{roleId:1,roleName:"管理员"},{roleId:2,roleName:"护士"},{roleId:4,roleName:"检验员"}],
		tableData: [
		],
		currentPage: 1,
		pageSize: 10,
		totalNum: '',
		dialogVisible: false,
		isAdd: true,
		form: {
			id: '',
			username:'',
			fullname: '',
			password: '',
			roles: [],
			roleId:"",
			createDate: new Date()
		},
		rules: {
			fullname: [{
					required: true,
					message: '请输入姓名',
					trigger: 'blur'
				},
				{
					max: 30,
					message: '最多输入30个字符'
				}
			]
		}
	},
	mounted: function() {
		var that = this;
		this.loadtable();
	},
	methods: {
		loadtable: function() {
			var that = this;
			var tmp = {
				fullname:this.fullname?this.fullname:''
			};
			axios.post(serverurl + 'api/user/list?fullname='+tmp.fullname, tmp).then(function(res) {
				that.totalNum = res.data.data.total;
				that.tableData = res.data.data.pageData;
				for(var index=0;index<that.tableData.length;index++){
					var one = that.tableData[index];
					for(var p in one){
						if(p=="p1" && one[p]==null){
							one[p]=[];
						}
					}
				}
			});
		},
		initform(){
			this.form.fullname="";
			this.form.username="";
			this.form.password="";
			this.form.roles=[];
			
			this.createDate= new Date();
		},
		handleadd: function() {
			var that = this;
			//---新建，从获得获得空对象
			axios.post(serverurl + 'api/user/new').then(function(res) {
				var one = res.data.data;
				for(var p in one){
					that.form[p]=one[p];
				}
				that.initform();
			});
			
			this.dialogVisible = true;
			this.isAdd = true;
		},
		// 关闭弹出框
		closeDialog: function() {
			this.initform();
		},
		handleEdit: function(index, row) {
			this.initform();
			this.form = Object.assign(this.form, row);
			this.form.roles=[];
			this.form.roles.push(this.form.roleId)
			
			this.dialogVisible = true;
			this.isAdd = false;
		},
		saveEdit: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					var data = Object.assign({}, that.form);
					
					axios.post(serverurl + 'api/user/save',
						data
					).then(
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
							that.dialogVisible = false;
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
				axios.delete(serverurl + 'api/user/' + row.id).then(
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
		nurseChange(newValue){
			for(let i=0;i<this.userList.length;i++){
				if(this.userList[i].username==newValue){
					this.form.nurseFullname=this.userList[i].fullname;
				}
			}
		},
		changeRole(roleId){
			for(let i=0;i<this.roleList.length;i++){
				if(this.roleList[i].roleId==roleId){
					return this.roleList[i].roleName;
				}
			}
		}
	}
});
