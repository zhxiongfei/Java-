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



## day02

- MyBatis 使用 Druid 连接池
- MyBatis 动态SQL
- 批量删除 / 批量上传
- 第三方日志输出库, logback使用





## day03

- 查询主键的另一种方式
- 设置 别名 typeAliases
- 数据库分页查询
  - pageHelper 的使用
    - [pageHelper Maven 导入失败的解决方法](https://www.cnblogs.com/Ye-ye/p/12775066.html)



## day04

### 多表级联查询

- 数据库的表之间可能存在一定的关系
  - 一对多 / 多对一
  - 一对一
  - 多对多
    - 联合主键



## day05

- 立即加载
  - 一条sql语句
  - 可能会造成资源的浪费
  - 如果不是集合类型，比较小的对象，可以考虑立即加载

- 延迟加载

  - 多条sql语句
  - 不会造成资源浪费
  - 如果是集合类型，使用延迟加载

- 全局延迟加载开关

  - mybatis-config.xml中添加配置

    ```sql
    <!-- 所有设置了 select属性 的关联对象延迟加载 -->
    <setting name="lazyLoadingEnabled" value="true"/>
    ```

- 缓存

  - 是指为了减少数据库直接访问次数，提高访问效率，而临时存储在内存中的数据。
  - 适合存放到缓存中的数据
    - 经常查询，不经常改变，数据的正确性对最终结果影响不大
  - 举例 : 不适合放在缓存中的数据:
    - 商品库存，价格，汇率等

- MyBatis缓存

  - 用于缓存 select 的结果，分为一级缓存和二级缓存

  - 一级缓存

    - 一级缓存存放在 SqlSession对象中
    - 同一个Sqlsession的select共享缓存
    - 所以当关闭Sqlsession时，缓存就失效了
    - 执行 insert , update , delete, commit等方法时，会自动清理一级缓存
    - 由于在很多时候，每次查询用的都是不同的SqlSession，所以一级缓存的命中率并不高

  - 二级缓存

    - 为了提高缓存的命中率，可以考虑开启MyBatis的二级缓存， 它是namespace(mapper)级别的缓存

      - 同一个namespace下的select共享数据
      - 默认情况下，namespace下update，insert，delete执行成功时，会自动清理二级缓存
      - 当调用SqlSession的close方法时，会将查询结果放入二级缓存

    - 开启二级缓存

      - 在mybatis-config.xml 中配置

        ```xml
        <!-- 开启二级缓存 -->
        <setting name="cacheEnabled" value="true"></setting>
        ```

      - 在映射文件的mapper中添加 cache 标签, 默认会缓存映射文件中的所有select结果

        ```xml
        <cache />
        ```

      - cache标签的常用属性

        - size : 缓存多少个存储结果 (单个对象 / 一个列表) 的引用，默认值是1024
        - eviction : 当缓存数量超过size时的清除策略. 可选值 : LRU,FIFO,SOFT,WEAK
        - flushInterval : 每隔多少秒清楚一个缓存，默认不会定期清除缓存
        - readOnly : true代表缓存的是原对象的引用, false代表缓存的是原对象的序列化后的拷贝对象
          - 所以false要求实现 Serializable 接口。默认是false

      - useCache, flushCache

        - 可以通过设置 useCache属性来决定某个select是否需要开启二级缓存
        - 可以通过设置 flushCache属性来决定某个操作之后，是否需要清除缓存



## day06

### 使用MyBatis实现dao层

- 使用MyBatis实现dao层的几种方式
  - 自定义dao实现类，在实现中调用SqlSession的相关方法(使用XML)
  - 只定义dao接口类，SqlSession的getMapper方法生成dao的代理对象(使用XML)
  - 只定义dao接口类，SqlSession的getMapper方法生成dao的代理对象(使用注解)
    - 目前注解的功能并没有XML强大，所以也可以使用XML + 注解混合使用





## day07

### Spring框架

- Spring框架可以算是Java开发中最常用的框架，功能非常强大
- Spring框架的几个核心概念
  - IoC : Inversion of Control,控制反转
  - DI : Dependency Injection , 依赖注入
  - AOP: Aspect Oriented Prigramming, 面向切片编程

### Spring基本使用

- 添加Maven依赖
- 新建一个核心配置文件 : applicacitonContext.xml
- **解耦**, 充当**工厂**角色



## day08

### IoC 

- Inversion of Control
- 控制反转
- 对象创建的权利反转
- 对象创建的控制权交给了 IoC容器

### DI

- Dependency Injection 依赖注入
- 比如， Spring把 dao 对象注入到 Service属性中
- 常用的注入内容分为3大类
  - bean (自定义类型)
  - 基本类型 (int , String, BigDecimal 等)
  - 集合类型 (数组，Map，List，Set，Properties)
- 常见的注入方式有2种
  - 基于setter (属性)
  - 基于 constructor (构造方法)





## day09

### Spring 依赖注入创建复杂对象

- 静态工厂
  - 使用**factory类对象**创建对象
  - 缺点是**无法注入值**
- 实例工厂
  - 使用 **factory实例对象**创建对象
  - **可以注入值**
- FactoryBean
  - Spring 提供的方式
  - FactoryBean类实现 **FactoryBean**接口
    - 并实现接口中两个方法



## day10

### SpEL表达式

(Spring Expression Language)

### scope

- 可以通过scope属性控制bean是否单例
  - singleton ：单例
  - 通过同一个**id**值，在同一个**IoC容器**中获取的永远是同一个单例
  - 在IoC容器创建的时候，就会创建bean，可以设置**lazy-init : true**修改创建时机
- prototype : 非单例。每次getBean时创建一次bean



## day11

### converter基本使用

- Spring已经内置了基本的类型转换功能，比如:
  - String转int, String转Date (支持yyyy/MM/dd格式)
- 自定义 Convert
  - 自定义 DateConvert类，实现Converter接口
  - xml配置文件中注册Converter 



## day12

### 整合MyBatis

- MyBatis中比较繁琐的地方
  - 如果mapper文件比较多，就需要配置很多的mapper文件路径
  - 需要事先创建 SqlSessionFactory 对象
  - 每次需要调用 openSession 方法 (最后还要close掉session)
- 整合MyBatis
  - pom.xml 添加依赖
  - 配置 Spring核心配置文件 applicationContext.xml
    - 配置数据源(druid)
    - 配置 SqlSessionFactoryBean
    - 配置映射文件的位置
    - 配置扫描dao
    - 设置sqlSessionFactory的id
    - 设置dao的包



## day13

### Bean生命周期

#### 一个bean从出生到死亡，经历的生命周期方法是 :

- 构造方法
- setter注入方法
- BeanNameAware 的 setBeanName方法
- ApplicationContextAware的setApplicationContext
- BeanPostProcessor 的 postProcessBeforeInitializetion
- InitializingBean的 afterPropertiesSet
- init-method
- BeanPostProcessor的postProcessorAfterInitialization
- 业务方法
- DisposableBean的destroy
- destroy-method





## day14

### 静态代理

#### 业务层的一些问题

- 业务层 (Service)的主要内容
  - 业务代码 : 业务运算，dao操作等
  - 附加代码 : 事务，日志，性能监控，异常处理等
- 问题？
  - 在业务层加入附加代码会显得很臃肿，累赘
  - 很多时候又不得不加

#### 代理

##### 概念 ： 

- 在不修改目标类的目标方法代码的前提下，为目标方法增加额外功能
- 在代理类必须有同样的目标方法
  - 代理类实现跟目标类同样的接口
  - 若目标类没有实现接口，代理类继承目标类

##### 实现方案 : 

###### 静态代理 (Static Proxy): 

- 开发人员手动编写类 (创建对应的 *.java 文件)
- 基本上，一个目标类就要编写一个代理类

###### 动态代理 (Dynamic Proxy)

- 程序运行过程中动态生成代理类的字节码

- 动态代理的常见实现方案有两种

  - JDK自带的
    - 代理类实现和目标类一样的接口
  - 开源项目 CGLib (Code Generation Library)
    - 代理类继承目标类
    - Spring已经集成 CGLib

  

## day15

### Spring AOP

- AOP(Aspect Oriented Programming) ，面向切面编程
- Spring使用AOP技术封装了动态代理的功能，使用起来非常简单
- 依赖 AspectJ 库
  - aspectjrt (aspectj runtime)
  - aspectjweaver 



### AOP - MethodBeforeAdvice

- 实现 MethodBeforeAdvice 接口，用来编写额外功能 (会在目标方法执行之前执行)



#### AOP - MethodInterceptor

- 实现 org.aopllicance.intercept.MethodInterceptor接口, 用来编写额外功能