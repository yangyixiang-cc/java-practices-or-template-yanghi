# 概述

* 是一个集成SpringSecurity、Redis、MyBatisPlus、SpringBoot快速启动模板
* 集成token以及权限认证。

# 使用

1. 克隆本项目或下载压缩包

```shell
git clone https://github.com/yangyixiang-cc/springboot-springsecurity-template.git
```

2. 将sql文件夹下的sql文件导入数据库：

打开MySQL命令行工具，创建新的数据库

```sql
create database database_name;
```

使用该数据库并导入sql文件

```sql
use database_name;
source sql_url;
```

查看数据库表，看是否导入成功

```sql
show tables;
```

3. 修改`src/main/resources`下的application.yml中的mysql数据库名、用户名、密码。
4. 修改`src/main/resources`下的application.yml中的redis的ip地址、密码（无密码可不设设置）。
5. 加载maven配置
6. 运行项目



