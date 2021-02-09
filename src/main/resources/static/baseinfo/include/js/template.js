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
			patientid:'',
			fullname: '',
			sex: '',
			birthday:'1977-01-01',
			nation: '',
			cardno: '',
			address: '',
			marriage: '',
			height: 0,
			weight: 0,
			bmi: 0,
			phone: '',
			temperature: 0,
			bloodpressure: 0,
			heartrate:0,
			breathe: 0,
			symptom: [],
			symptomOther:"",
			ai01:"",
			ai02:"",
			ai03:"",
			ai04:"",
			ai11:"",
			ai12:"",
			ai13:"",
			ai14:"",
			familyHistory: [],
			p1: [],
			p1Date:"",
			p1One: [],
			p1OneDate:"",
			p2:[],
			p201Diagnose:"",
			p201Date:"",
			p201Status:"",
			p202Diagnose:"",
			p202Date:"",
			p202Status:"",
			p3:[],
			p301Diagnose:"",
			p301Date:"",
			p301Name:"",
			p302Diagnose:"",
			p302Date:"",
			p302Name:"",
			p4:[],
			p401Name:"",
			p401Date:"",
			p401Usage:"",
			p402Name:"",
			p402Date:"",
			p402Usage:"",
			p5:[],
			p5Name:"",
			h1: [],
			h1Years:0,
			h1Few:0,
			h2:[],
			h2Text:"",
			h3:[],
			h3Time:"",
			h4:[],
			h4Many:0,
			h5:[],
			h6:[],
			h6Date:"0000-00-00",
			h6Days:0,
			others:"",
			treat:"",
			nurseName:'',
			createDate: new Date()
		},
		arrayColumns:["symptom","familyHistory","p1One","p1","p2","p3","p4","p5",
							"h1","h2","h3","h4","h5","h6"],
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
			patientid: [{
				required: true,
				message: '请输入档案编号',
				trigger: 'blur'
			}],
			birthday: [{
				required: true,
				message: '请输入出生日期',
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
			var tmp = {
				fullname:this.fullname?this.fullname:'',
				pageNum: this.currentPage,
				pageSize: this.pageSize,
			};
			axios.post(serverurl + 'api/baseinfo/list?fullname='+tmp.fullname, tmp).then(function(res) {
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
		dataChange(pageData){
			for(let one in pageData){
				
			}
		},
		initform(){
			this.form.symptomOther="";
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
			this.createDate= new Date();
		},
		handleadd: function() {
			var that = this;
			//---新建，从获得获得空对象
			axios.post(serverurl + 'api/baseinfo/new').then(function(res) {
				var one = res.data.data;
				for(var p in one){
					that.form[p]=one[p];
				}
				that.initform();
			});
			
			this.dialogVisible = true;
			this.isAdd = true;
		},
		saveAdd: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					var data = Object.assign({}, that.form);
					for(var p in data){
						if(that.arrayColumns.indexOf(p)>=0){
							var ss="";
							for(var i=0;i<data[p].length;i++){
								ss+=data[p][i]+";";
							}
							if(ss.endsWith(";")){
								ss= ss.substr(0,ss.length -1);
							}
							data[p]=ss;
						}
					}
					
					axios({
						method: 'post',
						url: serverurl + 'api/baseinfo/save',
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
			for(var p in this.form){
				if(this.arrayColumns.indexOf(p)>=0){
					if(this.form[p]==null){
						this.form[p]=[]
					}else{
						var ss = this.form[p];
						this.form[p]=ss.split(";")
					}
				}
			}
			this.dialogVisible = true;
			this.isAdd = false;
		},
		saveEdit: function(formName) {
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					var data = Object.assign({}, that.form);
					for(var p in data){
						if(that.arrayColumns.indexOf(p)>=0){
							var ss="";
							for(var i=0;i<data[p].length;i++){
								ss+=data[p][i]+";";
							}
							if(ss.endsWith(";")){
								ss= ss.substr(0,ss.length -1);
							}
							data[p]=ss;
						}
					}
					
					axios.post(serverurl + 'api/baseinfo/save',
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
				axios.delete(serverurl + 'api/baseinfo/' + row.id).then(
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
