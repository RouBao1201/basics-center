# basics-center
基础中心代码包

> 该基础版本项目（基于Springboot框架搭建使用，不会用Springboot请退出IT界），封装一系列的工具和配置，旨在少造轮子，提升开发效率。大多配置已经做到到手即用（PS：@陈大牛）。

| 应用模块 |              模块描述              |
| :------: | :--------------------------------: |
|  common  | 公共工具、常量、pojo、自定义异常等 |
|  nosql   |   mongodb、redis、elasticsearch    |
|   orm    |       关系数据库交互公共模块       |
|   web    |          web交互公共模块           |



- #### Common

  > 公共配置模块
  >
  > - 自定义异常、异常工具
  > - 公共基础POJO
  > - 基础常量、字符、枚举、正则
  > - 基础线程池（可通过yml配置文件指定线程参数）
  > - Spring上下文操作工具

- #### NoSQL

  - MongoDB
  - Redis
  - Elasticsearch

- #### ORM

  > - PageHelper
  > - JdbcTemplate封装工具类 -->  JdbcAdapter
  > - 分页公共响应体PageResult

- #### Web

  > - 统一异常响应
  > - 统一异常捕获响应
  > - Swagger
