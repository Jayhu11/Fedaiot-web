<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <!--      <el-form-item label="组id" prop="groupId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.groupId"-->
      <!--          placeholder="请输入组id"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->

      <el-form-item label="组名
" prop="groupName">
        <el-input
          v-model="queryParams.groupName"
          placeholder="请输入组名
"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!--      <el-form-item label="创建者" prop="createBy">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.createBy"-->
      <!--          placeholder="请输入创建者"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:group:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:group:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:group:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:group:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="groupList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column label="组名
" align="center" prop="groupName" />
      <!--<el-table-column label="创建者" align="center" prop="createBy" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:group:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:group:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改队伍管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="组名
" prop="groupName">
          <el-input v-model="form.groupName" placeholder="请输入组名
" />
        </el-form-item>
        <el-divider content-position="center">用户-组关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddUserGroup">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteUserGroup">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="userGroupList" :row-class-name="rowUserGroupIndex" @selection-change="handleUserGroupSelectionChange" ref="userGroup">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="用户id" prop="userId" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.userId" placeholder="请选择用户ID" clearable :style="{width: '100%'}">
                <el-option
                  v-for="item in relationOptions"
                  :key="item.userId"
                  :label="item.userId"
                  :value="item.userId"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="用户名" prop="userName" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.userName" placeholder="请选择用户名" clearable :style="{width: '100%'}">
                <el-option
                  v-for="item in relationOptions"
                  :key="item.userId"
                  :label="item.userName"
                  :value="item.userName"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGroup, getGroup, delGroup, addGroup, updateGroup } from "@/api/system/group";
import { listRelation } from "@/api/system/relation";
export default {
  name: "Group",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedUserGroup: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 队伍管理表格数据
      groupList: [],
      // 用户-组关联表格数据
      userGroupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupId: null,
        groupName: null,
        createBy: null
      },
      relationOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询队伍管理列表 */
    getList() {
      this.loading = true;
      listGroup(this.queryParams).then(response => {
        this.groupList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getRelation() {
      listRelation(this.queryParams).then(response => {
        this.relationOptions = response.rows;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        groupId: null,
        groupName: null,
        createBy: null
      };
      this.userGroupList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.groupId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加队伍管理";
      this.getRelation();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const groupId = row.groupId || this.ids
      this.getRelation();
      getGroup(groupId).then(response => {
        this.form = response.data;
        this.userGroupList = response.data.userGroupList;
        this.open = true;
        this.title = "修改队伍管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.userGroupList = this.userGroupList;
          if (this.form.groupId != null) {
            updateGroup(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGroup(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const groupIds = row.groupId || this.ids;
      this.$modal.confirm('是否确认删除队伍管理编号为"' + groupIds + '"的数据项？').then(function() {
        return delGroup(groupIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 用户-组关联序号 */
    rowUserGroupIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 用户-组关联添加按钮操作 */
    handleAddUserGroup() {
      let obj = {};
      obj.userId = "";
      obj.userName = "";
      this.userGroupList.push(obj);
    },
    /** 用户-组关联删除按钮操作 */
    handleDeleteUserGroup() {
      if (this.checkedUserGroup.length == 0) {
        this.$modal.msgError("请先选择要删除的用户-组关联数据");
      } else {
        const userGroupList = this.userGroupList;
        const checkedUserGroup = this.checkedUserGroup;
        this.userGroupList = userGroupList.filter(function(item) {
          return checkedUserGroup.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleUserGroupSelectionChange(selection) {
      this.checkedUserGroup = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/group/export', {
        ...this.queryParams
      }, `group_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
