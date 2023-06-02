<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="创建者" prop="userId">-->
<!--        <el-input-->
<!--          v-model="queryParams.userId"-->
<!--          placeholder="请输入创建者"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="组id" prop="groupId">
        <el-input
          v-model="queryParams.groupId"
          placeholder="请输入组id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['system:project:add']"
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
          v-hasPermi="['system:project:edit']"
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
          v-hasPermi="['system:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="组ID" align="center" prop="groupId" />
      <el-table-column label="项目ID" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="创建者" align="center" prop="userId" />
<!--      <el-table-column label="当前运行应用" align="center" prop="applicationId" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:project:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:project:remove']"
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

    <!-- 添加或修改项目管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
<!--        <el-form-item label="创建者" prop="userId">-->
<!--          <el-input v-model="form.userId" placeholder="请输入创建者" />-->
<!--        </el-form-item>-->
        <el-form-item label="组" prop="groupName">
          <el-select v-model="form.groupId" placeholder="请选择组" clearable :style="{width: '100%'}">
            <el-option
              v-for="item in groupOptions"
              :key="item.groupId"
              :label="item.groupName"
              :value="item.groupId"
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="应用" prop="applicationName">-->
<!--          <el-select v-model="form.applicationId" placeholder="请选择应用" clearable :style="{width: '100%'}">-->
<!--            <el-option-->
<!--              v-for="item in applicationOptions"-->
<!--              :key="item.applicationId"-->
<!--              :label="item.applicationName"-->
<!--              :value="item.applicationId"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-divider content-position="center">项目应用管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddProjectApplication">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteProjectApplication">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="projectApplicationList" :row-class-name="rowProjectApplicationIndex" @selection-change="handleProjectApplicationSelectionChange" ref="projectApplication">
          <el-table-column type="selection" width="50" align="center" />
<!--          <el-table-column label="序号" align="center" prop="index" width="50"/>-->
          <el-table-column label="项目中运行的应用" prop="applicationid" width="150">
<!--          <el-table-column label="应用名称" prop="applicationid" width="150">-->
            <template slot-scope="scope">
<!--              <el-input v-model="scope.row.applicationid" placeholder="请输入应用ID" />-->
              <el-select v-model="scope.row.applicationid" placeholder="请选择应用" clearable :style="{width: '100%'}">
                <el-option
                  v-for="item in applicationOptions"
                  :key="item.applicationId"
                  :label="item.applicationName"
                  :value="item.applicationId"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
<!--        <el-form-item label="组id" prop="groupId">-->
<!--          <el-input v-model="form.groupId" placeholder="请输入组id" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/system/project";
import {listGroup} from "@/api/system/group";
import { listApplication, getApplication, delApplication, addApplication, updateApplication } from "@/api/system/application";
export default {
  name: "Project",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedProjectApplication: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目管理表格数据
      projectList: [],
      // 项目应用管理表格数据
      projectApplicationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        userId: this.$store.state.user.userId,
        groupId: null
      },
      //下拉框查询group参数
      querys: {
        pageNum: 1,
        pageSize: 999,
        userId: this.$store.state.user.userId,
        groupId: null
      },
      // 表单参数
      form: {
        userId:this.$store.state.user.userId,
      },
      groupOptions: [],
      applicationOptions:[],
      // 表单校验
      rules: {
        // groupId: [
        //   { required: true, message: "组id不能为空", trigger: "blur" }
        // ],
        groupId: [
          { required: true, message: '请选择组', trigger: 'change'}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getGroup() {
      listGroup(this.queryParams).then(response => {
        this.groupOptions = response.rows;
      });
    },
    getApplication() {
      listApplication(this.queryParams).then(response => {
        this.applicationOptions = response.rows;
      });
    },
    /** 查询项目列表 */
    getList() {
      this.loading = true;
      listProject(this.queryParams).then(response => {
        this.projectList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        projectId: null,
        projectName: null,
        userId: this.$store.state.user.userId,
        groupId: null
      };
      this.projectApplicationList = [];
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目管理";
      this.getGroup();
      this.getApplication();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      this.getGroup();
      this.getApplication();
      getProject(projectId).then(response => {
        this.form = response.data;
        this.projectApplicationList = response.data.projectApplicationList;
        this.open = true;
        this.title = "修改项目";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.projectApplicationList = this.projectApplicationList;
          if (this.form.projectId != null) {
            updateProject(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.userId = this.$store.state.user.userId;
            // console.log(this.$store.state.user);
            console.log(this.form);
            addProject(this.form).then(response => {
              this.$modal.msgSuccess("项目新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目管理编号为"' + projectIds + '"的数据项？').then(function() {
        return delProject(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 项目应用管理序号 */
    rowProjectApplicationIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 项目应用管理添加按钮操作 */
    handleAddProjectApplication() {
      let obj = {};
      obj.applicationid = "";
      this.projectApplicationList.push(obj);
    },
    /** 项目应用管理删除按钮操作 */
    handleDeleteProjectApplication() {
      if (this.checkedProjectApplication.length == 0) {
        this.$modal.msgError("请先选择要删除的项目应用管理数据");
      } else {
        const projectApplicationList = this.projectApplicationList;
        const checkedProjectApplication = this.checkedProjectApplication;
        this.projectApplicationList = projectApplicationList.filter(function(item) {
          return checkedProjectApplication.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleProjectApplicationSelectionChange(selection) {
      this.checkedProjectApplication = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/project/export', {
        ...this.queryParams
      }, `project_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
