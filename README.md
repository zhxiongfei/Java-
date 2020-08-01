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

