# JavaEELearning



## day 01

- tomcat 环境搭建

- idea配置 tomcat, 并起服务

- 简单 h5 登录页面, 发送请求, 并作出响应

  


## day 02
- html发送请求至 jsp

- jsp常用标签及其本质

  

## day 03
- jsp + servlet : 业务和页面展示分离模式
  - servlet 负责业务逻辑
  - 转发给 jsp 来绘制页面
- servlet 转发
- 重定向



## day 04

- mysql 安装
- navcat 安装
- DDL语句基本操作



## day 05

- DQL语句
- 主键 / 外键
- 级联 / 多表查询
- 排序 / 分页



## day 06

- java 操作数据库
- jdbc使用
- preparestatement / statement常用 api



## day 07

- 引入 dao 负责和数据库交互
- servlet 通过和 dao 交互. 而不直接操作数据库



## day 08

- dao类中还是有部分重复代码
- 所以封装 Dbs 类, 专门用于执行 sql 语句，操作数据库(DDL,DML, DQL等）
- dao类通过 Dbs 操作数据库
- 同一个 servelt 处理多个请求
- 数据库连接池 druid 使用



## day 09

- crm项目, 增删改查实现
- 重构 : 添加和编辑jsp合并



## day 10

- 初识js



## day11

- bootstrap 简单了解
- jquery 简单了解



## day12

- maven 使用



## day13

- 使用 idea部署 maven Java Web项目





## day14

- 开始简历管理项目 resume
- 主要注重后端逻辑，所以拷贝 html, js, css代码
- 起服务可以正常访问静态页面



## day15

- jdbc 连接数据库
- druid 连接池使用
- BaseDao封装
- 网站信息模块 
  - jsp改造
  - WebsiteBean定义 
  - WebsiteDao



## day16

- 教育信息列表
- 教育信息添加 / 删除操纵