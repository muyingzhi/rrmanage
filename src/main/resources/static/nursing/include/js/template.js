var initdata = new Vue({
	el: '.cont',
	data: {
		activeName:"second",
		isShowSelect:false,
		isShowEdit: false,
		isAdd: true,
		fullname:'',
		tableData: [],
		userList: [],
		currentPage: 1,
		pageSize: 10,
		totalNum: '',
		form: {
			id: '',
			patientid:"",
			nursingDate: '',
			items: '',
			nursingNote: '',
			nurseName: '',
		},
		activeName: 'first',
		
	},
	mounted: function() {
		var that = this;
		axios.post(serverurl + 'api/user/nurse/list').then(function(res) {
			that.userList = res.data.data;
		})
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
			this.initform();
			this.form = Object.assign(
				this.form,
				patient);
			this.isAdd = true;
		},
		deleteRecord:function(record,nursingType){
			if(1==nursingType){
				this.firstDatas=[{},{}]
			}else if(2==nursingType){
				this.secondDatas=[{},{}]
			}

			console.log(record)
		},
		handleClick:function(tab, event){
			console.log(tab, event);
		},
		//编辑
		handleEdit: function(index, row) {
			//当前行记录赋值给form，显示编辑窗口
			this.initform();
			this.form = Object.assign(this.form, row);
			this.isShowEdit = true;
			this.isAdd = false;
			this.activeName="first";
		},
		loadtable: function() {
			//----查询，根据姓名获取客户列表
			var that = this;
			var params ={fullname: !(this.fullname==null)?this.fullname:"" ,
				pageNum:this.currentPage,
				pageSize:this.pageSize}
				;
			axios.post(serverurl + 'api/baseinfo/list?fullname='+params.fullname,params).then(function(res) {
				that.totalNum = res.data.data.total;
				that.tableData = res.data.data.pageData;
			});
		},
		initform(){
			this.form.id='';
			this.form.fullname='';
			this.form.sex='';
			this.form.birthday='';
			this.form.nation='';
			this.form.address=null;
			this.form.visitDate= null;
			this.form.nurseName=null;
			this.form.temperature=36;
			this.form.selfReported='';
			this.form.publicity='';
		},
		// 关闭弹出框
		closeDialog: function() {
			this.initform()
		},
		saveEdit: function(formName) {
			var that = this;
			
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					var data = Object.assign({},that.form);
					axios({
						method: 'post',
						url: serverurl + 'api/followup/save',
						data: data
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
		handleDelete1: function(index, row) {
			//删除当前行
			var that = this;
			this.$confirm('您确定要删除该记录吗?', '提示', {
				cancelButtonText: '取消',
				confirmButtonText: '确定',
				type: 'warning'
			}).then(function() {
				axios.delete(serverurl + 'api/followup/' + row.id).then(
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
		},
		nurseChange(newValue){
			for(let i=0;i<this.userList.length;i++){
				if(this.userList[i].username==newValue){
					this.form.nurseFullname=this.userList[i].fullname;
				}
			}
		}
	}
});
