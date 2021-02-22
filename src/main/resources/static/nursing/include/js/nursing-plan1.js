
/***
 * 选择客户：
 * chartData:[{title:"平均就诊距离",value:900,unit:"km"}]
 * feature: {url:"../images/tz.gif"}
 */
var selectPatientWidget = Vue.component('nursing-plan1', {
    props:{
		userList:[]
	},
    data: function () {
      return {
        list: [],
        selectedOne:{},
		form:{fullname:""},
		currentPage:0,
		pageSize:10,
		totalNum:0,
		isShowEdit:false
      }
    },
	template: `
			<div>
				<span>
					<a class="bluebtn mr20" @click="isShowEdit=true">新增记录</a>
				</span>
				<el-table :data="list" style="width: 100%" stripe="true">
					</el-table-column>
						<el-table-column type="index" width="50" label="序号">
					</el-table-column>
					<el-table-column label="采血日期">
						<template slot-scope="scope">
							<span>{{ scope.row.fullname }}</span>
						</template>
					</el-table-column>
					<el-table-column label="采血项目">
						<template slot-scope="scope">
							<span>{{ scope.row.sex }}</span>
						</template>
					</el-table-column>
					<el-table-column label="收费">
						<template slot-scope="scope">
							<span>{{ scope.row.birthday }}</span>
						</template>
					</el-table-column>
					<el-table-column label="执行人">
						<template slot-scope="scope">
							<span>{{ scope.row.address }}</span>
						</template>
					</el-table-column>
					<el-table-column label="核对人">
						<template slot-scope="scope">
							<span>{{ scope.row.address }}</span>
						</template>
					</el-table-column>
					<el-table-column label="功能">
						<template slot-scope="scope">
							<span>{{ scope.row.address }}</span>
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
				<el-dialog title="早期防癌检测方案记录" :visible.sync="isShowEdit" width="400px" append-to-body>
					<el-form :model="form" label-width="100px" label-position="right" ref="form">
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="采血日期">
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
								<el-form-item label="采血项目">						
									<el-input type="input" v-model="form.items" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="是否收费">						
									<el-input type="input" v-model="form.items" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="执行人">						
									<el-input type="input" v-model="form.nursingNote" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="核对人">						
									<el-input type="input" v-model="form.nursingNote" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="功能">						
									<el-input type="input" v-model="form.nursingNote" size="mini"></el-input>
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
		this.list=[{},{},{}]
    },
    watch: {
      chartData: {
        handler: function(newData){
  
        }
      }
    },
    methods: {
		handleEdit(index,pat){
			this.isShowEdit=true
		},
		handleDelete(index,record){
			this.$emit("delete",record,3)
		},
		loadpatient: function() {
			var that = this;
			var tmp = {
				fullname:this.form.fullname?this.form.fullname:'',
				pageNum: this.currentPage,
				pageSize: this.pageSize,
			};
			axios.post(serverurl + 'api/baseinfo/list?fullname='+tmp.fullname,tmp).then(function(res) {
				that.totalNum = res.data.data.total;
				that.list = res.data.data.pageData;
				
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
		}
    }
  })
  