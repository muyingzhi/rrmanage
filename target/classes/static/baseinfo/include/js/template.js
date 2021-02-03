var initdata = new Vue({
	el: '.cont',
	data: {
		fullname:'',
		age:'',
		status:'',
		tableData: [
		],
		currentPage: 1,
		pageSize: 10,
		totalNum: '',
		dialogVisible: false,
		isAdd: true,
		form: {
			id: '',
			fullname: '',
			sex: '',
			symptom: [],
			symptomOther:"",
			familyHistory: [],
			p1: [],
			p1One: [],
			p2: [],
			p3: [],
			p4: [],
			p5: [],
			h1: [],
			h2: [],
			h3: [],
			h4: [],
			h5: [],
			h6: []
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
			],
			birthday: [{
				required: true,
				message: '请输入',
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
			var params = +'?fullname=' + this.fullname + '&pageNum=' + this.currentPage +
			'&pageSize=' + this.pageSize;
			axios.get(serverurl + 'mock/baseinfolist.json').then(function(res) {
				that.totalNum = res.data.data.total;
				that.tableData = res.data.data.pageData;
			});
		},
		dataChange(pageData){
			for(let one in pageData){
				
			}
		},
		initform(){
			
			this.form.symptom = [];
			this.form.familyHistory = [];
			this.form.p1=[];
			this.form.p1One=[];
			this.form.p2=[];
			this.form.p3=[];
			this.form.p4=[];
			this.form.p5=[];
			this.form.h1=[];
			this.form.h2=[];
			this.form.h3=[];
			this.form.h4=[];
			this.form.h5=[];
			this.form.h6=[];

		},
		handleadd: function() {
			var that = this;

			axios.get(serverurl + 'mock/baseinfonew.json').then(function(res) {
				that.form = res.data.data.baseinfo;
				that.initform();
			});
			
			this.dialogVisible = true;
			this.isAdd = true;
		},
		saveAdd: function(formName) {
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
		// 关闭弹出框
		closeDialog: function() {
			this.initform();
		},
		handleEdit: function(index, row) {
			this.initform();
			this.form = Object.assign(this.form, row);
			this.dialogVisible = true;
			this.isAdd = false;
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
		handleSizeChange: function(val) {
			this.pageSize = val;
			this.currentPage = 1;
			this.loadtable();
		},
		handleCurrentChange: function(val) {
			this.currentPage = val;
			this.loadtable();
		},
		removeDomain: function(item) {
			var index = this.form.zujianList.indexOf(item);
			if (index !== -1) {
				this.form.zujianList.splice(index, 1)
			}
		},
		edittx: function() {
			this.txdialogVisible = true;
		}
	}
});
