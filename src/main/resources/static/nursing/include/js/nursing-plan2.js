
/***
 * 选择客户：
 * chartData:[{title:"平均就诊距离",value:900,unit:"km"}]
 * feature: {url:"../images/tz.gif"}
 */
var selectPatientWidget = Vue.component('nursing-plan2', {
    props:{
		patientid:""
	},
    data: function () {
      return {
        list: [],
		nursingType:4,
        selectedOne:{},
		form:{id:"",nursingDate:'',items:"",isFee:true,operator:"",checker:"",doTime:""},
		currentPage:0,
		pageSize:10,
		totalNum:0,
		isShowEdit:false
      }
    },
	template: `
			<div>
				<span>
					<a class="bluebtn mr20" @click="newRecord">新增记录</a>
				</span>
				<el-table :data="list" style="width: 100%" stripe="true">
					</el-table-column>
						<el-table-column type="index" width="50" label="序号">
					</el-table-column>
					<el-table-column label="输液日期" width="100">
						<template slot-scope="scope">
							<span>{{ scope.row.nursingDate }}</span>
						</template>
					</el-table-column>
					<el-table-column label="项目">
						<template slot-scope="scope">
							<span>{{ scope.row.items }}</span>
						</template>
					</el-table-column>
					<el-table-column label="收费">
						<template slot-scope="scope">
							<span>{{ scope.row.isFee?"已收费":"未收费" }}</span>
						</template>
					</el-table-column>
					<el-table-column label="执行人">
						<template slot-scope="scope">
							<span>{{ scope.row.operator }}</span>
						</template>
					</el-table-column>
					<el-table-column label="核对人">
						<template slot-scope="scope">
							<span>{{ scope.row.checker }}</span>
						</template>
					</el-table-column>
					<el-table-column label="输液时间">
						<template slot-scope="scope">
							<span>{{ scope.row.doTime }}</span>
						</template>
					</el-table-column>
					<el-table-column label="操作" width="150" fixed="right" align="center">
						<template slot-scope="scope">
							<el-tooltip class="item" content="编辑">
								<a @click="handleEdit(scope.$index, scope.row)"><img src="../include/images/table/edit-blue.png" /></a>
							</el-tooltip>
							<el-tooltip class="item" content="删除">
								<a @click="handleDelete(scope.$index, scope.row)"><img src="../include/images/table/del.png" /></a>
							</el-tooltip>
						</template>
					</el-table-column>
				</el-table> 
				<el-dialog title="细胞干预方案记录" :visible.sync="isShowEdit" width="400px" append-to-body>
					<el-form :model="form" label-width="100px" label-position="right" ref="form">
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="输液日期">
									<el-date-picker
										v-model="form.nursingDate"
										type="date" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
										placeholder="选择日期"
										size="mini">
										</el-date-picker>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="项目">						
									<el-input type="input" v-model="form.items" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="是否收费">						
									<el-radio v-model="form.isFee" :label="true">已收费</el-radio>
									<el-radio v-model="form.isFee" :label="false">未收费</el-radio>	
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="执行人">						
									<el-input type="input" v-model="form.operator" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="核对人">						
									<el-input type="input" v-model="form.checker" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="输液时间">						
									<el-input type="input" v-model="form.doTime" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
					
					<span slot="footer" class="dialog-footer">
						<a class="bluebtn mr20" @click="saveEdit('form')">保存</a>
						<a class="graybtn_b" @click="isShowEdit=false">取消</a>
					</span>
				</el-dialog>
			</div>
              `,
	mounted: function () {
		this.loadtable();
	},
	watch: {
		patientid(){		
			this.loadtable();
		}
	},
    methods: {
		initform(){
			this.form={id:"",patientid:"",isFee:true,nursingDate:'',items:"",nursingNote:"",nurseFullname:"",nurseName:"",doTime:""}
		},
		newRecord(){
			this.initform();
			this.isShowEdit=true;
		},
		handleEdit(index, row){
			this.initform();
			this.form = Object.assign(this.form, row);
			this.isShowEdit = true
		},
		loadtable: function() {
			var that = this;
			
			axios.post(serverurl + 'api/nursing/list?patientid='+this.patientid+"&nursingType="+this.nursingType).then(function(res) {
				that.list = res.data.data;
			});
		},
		saveEdit(formName){
			var that = this;
			this.$refs[formName].validate(function(valid) {
				if (valid) {
					var data = Object.assign(
						{}, that.form,
						{patientid:that.patientid,
							nursingType:"4"});
					// var ss="";
					// for(var i=0;i<data.examItems.length;i++){
					// 	ss+=data.examItems[i]+";";
					// }
					// if(ss.endsWith(";")){
					// 	ss= ss.substr(0,ss.length -1);
					// }
					// data.examItems=ss;
						
					axios({
						method: 'post',
						url: serverurl + 'api/nursing/save',
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
				axios.delete(serverurl + 'api/nursing/' + row.id).then(
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
		}
    }
  })
  