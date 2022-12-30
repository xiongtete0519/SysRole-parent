## 一、项目介绍

### 1、介绍

权限管理是所有后台系统都会涉及的一个重要组成部分，而权限管理的核心流程是相似的，如果每个后台单独开发一套权限管理系统，就是重复造轮子，是人力的极大浪费，本项目就是针对这个问题，提供了一套通用的权限解决方案。

项目服务器端架构：SpringBoot + MyBatisPlus + SpringSecurity

前端架构：Node.js + Npm + Vue + ElementUI + Axios<br>
项目部署：Nging+Docker

### 2、核心技术

| 基础框架：SpringBoot                              |
| ------------------------------------------------- |
| 数据缓存：Redis                                   |
| 数据库：Mysql                                     |
| 权限控制：SpringSecurity                          |
| 全局日志记录：AOP                                 |
| 前端模板：vue-admin-template                      |
| 前端技术：Node.js + Npm + Vue + ElementUI + Axios |

### 3、项目模块

最终服务器端架构模块

guigu-auth-parent：根目录，管理子模块：

​	common：公共类父模块

​		common-log：系统操作日志模块

​		common-util：核心工具类

​		service-util：service模块工具类

​		spring-security：spring-security业务模块

​	model：实体类模块

​	service-system：系统权限模块

### 4、项目演示地址

硅谷通用权限系统演示地址：http://codeleader.top/guigu

根据演示了解项目相关业务

### 5、数据库设计

数据库从资料文件中获取，导入数据库，数据库表如下：

![](http://blog.codeleader.top/img/image-20220527143312775.png)

### 6、界面演示
![image-20221230171226854](http://blog.codeleader.top/img/image-20221230171226854.png)

![image-20221230171736165](http://blog.codeleader.top/img/image-20221230171736165.png)

![image-20221230171711121](http://blog.codeleader.top/img/image-20221230171711121.png)
