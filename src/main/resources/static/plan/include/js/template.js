var initdata = new Vue({
	el: '.cont',
	data: {
		fullname:'',
		isShowSelect:false,
		isShowEdit: false,
		isAdd:true,
		tableData: [],
		userList: [],
		currentPage: 1,
		pageSize: 10,
		totalNum: '',
		form: {
			id:'',
			patientid: '',
			fullname: '',
			sex: '',
			birthday: '',
			address: '',
			examDate:null,
			examItems:[],
			explanation:'',
			suggestion:'',
			plan1:'',plan2:'',plan3:'',
			plan1Date:null,plan2Date:null,plan3Date:null,
			nurseName:'',expertName:'',
			explantDate:null
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
		loadtable: function() {
			//----查询，根据姓名获取客户列表
			var that = this;
			var params ={fullname: this.fullname?this.fullname:"" ,
				pageNum:this.currentPage,
				pageSize:this.pageSize}
				;
			axios.post(serverurl + 'api/plan/list?fullname='+params.fullname,params).then(function(res) {
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
			this.form.examDate=null;
			this.form.examItems=[];
			this.form.explanation='';
			this.form.suggestion=null;
			this.form.plan1=null;
			this.form.plan2=null;
			this.form.plan3=null;
			this.form.plan1Date=null;
			this.form.plan2Date=null;
			this.form.plan3Date=null;
			this.form.nurseName=null;
			this.form.expertName=null;
			this.form.explantDate=null;
		},
		// 关闭弹出框
		closeDialog: function() {
			this.initform();
		},
		//编辑
		handleEdit: function(index, row) {
			//当前行记录赋值给form，显示编辑窗口
			this.form = Object.assign(this.form, row);
			if(row.examItems==null){
				this.form.examItems=[];
			}else{
				this.form.examItems=row.examItems.split(";")
			}
 			this.isShowEdit = true;
			this.isAdd = false;
		},
		saveEdit: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					var data = Object.assign({}, that.form);
					var ss="";
					for(var i=0;i<data.examItems.length;i++){
						ss+=data.examItems[i]+";";
					}
					if(ss.endsWith(";")){
						ss= ss.substr(0,ss.length -1);
					}
					data.examItems=ss;
						
					axios({
						method: 'post',
						url: serverurl + 'api/plan/save',
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
		handleDelete: function(index, row) {
			//删除当前行
			var that = this;
			this.$confirm('您确定要删除该记录吗?', '提示', {
				cancelButtonText: '取消',
				confirmButtonText: '确定',
				type: 'warning'
			}).then(function() {
				axios.delete(serverurl + 'api/plan/' + row.id).then(
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
