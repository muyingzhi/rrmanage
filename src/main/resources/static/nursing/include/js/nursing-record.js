
/***
 * 选择客户：
 * deleteClick
 * props:
 *     datas:[{}],
 *     nursingType:1/2
 * 
 */
var selectPatientWidget = Vue.component('nursing-record', {
    props:{
		datas:[],
		userList: [],
		nursingType:1
	},
    data: function () {
      return {
        list: [],
        selectedOne:{},
		form:{fullname:""},
		currentPage:0,
		pageSize:10,
		totalNum:0,
		form:{},
		isShowEdit:false
      }
    },
	template: `
			<div>
			<span>
				<a class="bluebtn mr20" @click="isShowEdit=true">新增记录</a>
			</span>
				<el-table :data="datas" style="width: 100%" stripe="true">
					</el-table-column>
						<el-table-column type="index" width="50" label="序号">
					</el-table-column>
					<el-table-column label="时间" width="100" >
						<template slot-scope="scope">
							<span>{{ scope.row.nursingDate }}</span>
						</template>
					</el-table-column>
					<el-table-column label="项目">
						<template slot-scope="scope">
							<span>{{ scope.row.items }}</span>
						</template>
					</el-table-column>
					<el-table-column label="护理记录">
						<template slot-scope="scope">
							<span>{{ scope.row.nursingNote }}</span>
						</template>
					</el-table-column>
					<el-table-column label="签名" width="80" >
						<template slot-scope="scope">
							<span>{{ scope.row.nurseFullname }}</span>
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
				<el-dialog title="细胞深度净化护理" :visible.sync="isShowEdit" width="400px" append-to-body>
					<el-form :model="form" label-width="100px" label-position="right" ref="form">
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="时间">
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
								<el-form-item label="护理记录">						
									<el-input type="textarea" v-model="form.nursingNote" resize="none" rows="3" size="mini"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row :gutter="10">
							<el-col :span="24">
								<el-form-item label="护士签名">		
									<el-select v-model="form.nurseName" placeholder="请选择" size="mini"
									@change="nurseChange">
										<el-option v-for="one in userList"
											:key="one.username"
											:value="one.username"
											:label="one.fullname"
										>
										</el-option>
									</el-select>					
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
    },
    watch: {
      chartData: {
        handler: function(newData){
  
        }
      }
    },
    methods: {
		handleDelete:function(index,record){
			this.$emit("delete",record,this.nursingType)
		},
		handleEdit(){
			this.isShowEdit = true
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
  