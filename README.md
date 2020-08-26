# JavaEELearning



# 第二季

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



## day17

- 教育信息编辑
- Java对象转JSON
  - jackjson的使用
- jsp通用模块抽取



## day18

- 加入 service业务层
  - servlet控制器层 ：负责接收用户请求，把参数传递给service业务层
  - service业务层 ：负责业务处理，并调用 dao 数据库层
  - dao层 : 负责数据库的访问



## day19

- 面向接口编程
  - 想拥有一个对象，把它声明为一个接口类型
- skill技能实现



## day20

- 错误码404 、 500对应页面在web.xml中的配置
- 抛异常，获取最深层次的原因



## day21

- 文件上传
- 获奖成就



## day22

- 转发 、重定向抽取
- Java反射机制
  - 自动生成表名 
  - 自动生成 Dao
  - 自动生成 service
- tmplate 模板
  - 使用模板创建类似java类





## day23

- 工作经验
- 项目经验



## day24

- 登录功能
  - md5加密password
- 验证码功能
  - google kaptacha随机生成验证码图片



## day25

- 使用session不同请求间共享数据
- cookie， session的使用
  - cookie
    - 数据存储在浏览器客户端
    - 数据有大小和数量限制
    - 适合存储一些小型，不敏感的数据
    - 默认情况下，关闭服务器后销毁
  - session
    - 数据存储在服务器端
    - 数据没有大小和数量限制
    - 可以存储大型，敏感的数据(比如用户信息)
    - 默认情况下，未使用30分钟后销毁
- session内部原理
  - 检查客户端是否有发送一个叫做 JSESSIONID的cookie
    - 如果没有
      - 创建一个新的session对象，并且这个session对象有一个id
      - 这个session对象保存在服务器的内存中
      - 在响应的时候，会添加一个cookie(JESSIONID = session兑现的id) 给客户端
    - 如果有
      - 返回 id 为 JSESSIONID的session对象



## day26

- Filter的使用 - 过滤请求
- 用户个人信息页面



## day27

- 前台页面
  - 首页 ，教育经验， 工作经验，项目经验



## day28

- 留言信息
- 多条件查询



## day29

- 使用 AJAX 发送异步请求
- 修改 登录请求为  AJAX 提高体验



# 第三季

## day01

- 数据库事务 (DataBase Transaction)
  - 数据库事务，简称事务。
    - 如果将N个数据库操作放到同一个事务中，那么这N个操作最终要么全部生效，要么全都不生效
  - 事务的使用
    - 开启事务 : STAET TRANSACTIOn
    - 回滚事务 : ROLLBACK
      - 只要事务中的一个操作失败，那么其他所有操作都需要回滚(rollback), 回到开始事物之前的状态
    - 提交事务 : COMMIT
      - 如果事务中的所有操作都成功了，就提交事务，让这些操作真正生效
  - 事务的四大特征 (ACID)
    - 原子性 (Atomicity)
      - 事务作为一个整体被执行，包含在其中的对数据库的操作要么全部被执行，要么都不执行
    - 一致性 (Consistency)
      - 事务应确保数据库的状态从一个一致状态转变为另一个一致状态。一致状态的含义是数据库中的数据应满足完整性约束
    - 隔离性 (Isolation)
      - 多个事务并发执行时，一个事务的执行不影响其他事务的执行
    - 持久性 (Durability)
      - 已被提交的事务对数据库的修改应该永久保存在数据库中
  - JDBC事务管理
    - 在JDBC中，使用 Connection 对象来管理事务
      - setAutoCommit(false) : 开启事务
      - rollback() : 回滚事务
      - commit() : 提交事务
  - MyBatis
    - MyBatis是数据持久层(Dao层)的框架
      - 封装了JDBC的很多操作细节，让开发者简化了DAO层的代码
    - 使用步骤 ：
      1. 添加依赖
      2. 核心文件配置 (mybatis-config.xml)
      3. 创建session
      4. 配置实体映射
      5. 查询
      6. 多表关联查询
         1. 将 resultType设置为 Map, HashMap,LinkedHashMap
         2. 将resultType设置为JavaBean

