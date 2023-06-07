# BookStore

## 项目简介

该项目基于项目[bookStore](https://github.com/eson15/Javaweb_bookstore)。

我们对该项目的后端基本进行了完全的重构(删除重写)，在这个过程中同样对`jsp`页面中的部分代码进行了修改(主要在参数传递以及部分参数显示)。

我们的修改主要体现在以下几个点：

* 原项目的Javabean中存在嵌套的对象。
  * 在`Order`的`Javabean`中嵌套了`User`和`OrderItem`，我们在`Order`使用了外键`uid`来标识所属用户，`orderItem`中使用`oid`来标识订单项所属的订单，解除了bean之间的依赖。
  * `Cart`中嵌套了`CartItem`。
* 原项目的`Cart`模块使用`session`技术来进行开发，我们改用了使用MySQL关系型数据库来进行实现。
  * 在库中新建表`cart`，字段为：`cid`，`uid`，`bid`，`price`，`num`。`uid`标识所属用户，bid标识该购物车项的图书，`price`表示价格，`num`表示该购物车项的数量。
  * 因为前端展示购物车的列表时除上述字段外还需要`Book`表中的`author`，`name`字段，这种情况下需要进行联表查询。而现有的`bean`中没有能够满足显示以上字段的要求，我们需要再创建一个`CartVo`的`bean`来表示以上所有的字段。
* 原项目中MVC架构的`service`层仅仅只是笼统的写了一个`BusinessService`接口以及实现类，并且接口只定义了3个方法。
  * 我们根据项目的`Book`，`Cart`，`Order`，`User`，`Category`五个模块将`service`层分为了`BookService`，`CartService`，`OrderService`，`UserService`，`CategoryService`五个接口并进行了实现。
* 原项目为实现分页功能而定义的`page`类中实现太繁杂，包括了`8`个属性和以及一个复杂的构造方法。
  * 我们将`pageBean`的属性缩减为`3`个属性：商品总数`totalCount`，总页数`totalPages`以及当列数据`rows`。
  * 需要分页的页面需要从前端传回当前页码pageNow以及每页大小pageSize，我们在service层中调用Dao层中的方法获取到商品总数`totalCount`,再根据`pageNow`以及`pageSize`计算`Dao`层中`limit`分页查询中需要的`begin`和`end`参数，将`begin`和`end`传入`Dao`层中获取到当页需要展示的数据，再将分页数据传回前端进行显示。

* 实现了原项目中未实现的各个模块的删除功能

* 原项目中并没有对项目中的异常进行处理。
  * 我们自定义了两个继承于`RuntimeException`的异常`BusinessException`和`SystemException`，分别用来表示业务异常和系统异常。
  * 我们会在`service`层中将可能会出现的异常抛出，到了web层来进行处理并向前端发送消息。
* 原项目中出现的中文乱码。
  * 将能更改编码的地方更改为`UTF-8`.

## 项目结构

### 后端(java目录)

* `dao `：数据访问层，对表进行操作。
* `service `：业务逻辑层，依赖dao层，对逻辑进行封装给web层调用，
* `web`层：表示层，处理前端请求并作出响应。
* `filter`：过滤器，拦截请求和响应，以变换或使用包含在请求或响应中的信息。
* `Exception `：自定义异常。
* `entity`：存放JavaBean，与数据库中的表对应。
* `Vo`：表现层对象，通常可以使用Javabean替代。
* `utils`：存放一些静态方法。

### 前端(webapp目录)

* `client `：存放用户端的页面
* `manager `：存放管理端的页面
* `images`：存放用户上传的图片。

## 项目功能

项目有**用户端**和**管理端(/manager)**。

* `User`：注册，登录，退出登录。

* `Book`：添加(可以选择当前存在的分类)，删除，分页显示，分类分页显示。
* `Crder`：添加，删除，根据订单状态显示，发货。
* `Cart`：添加，删除，加入订单。
* `Category`：添加，删除。

## 快速开始

1. 使用`git`或其他方式将项目拷贝到本地`ide`中。
2. 更改`resource`目录下的`c3p0-config.xml`,将`mysql`的用户名和密码更改为自己的。

```xml
<?xml version="1.0" encoding="UTF-8"?>

<c3p0-config>
   
   <default-config>
      <property name="driverClass">com.mysql.cj.jdbc.Driver</property>
      <property name="jdbcUrl">jdbc:mysql://localhost:3306/bookstore?useSSL=false&amp;serverTimezone=Asia/Shanghai</property>
      <property name="user">yourUserName</property>
      <property name="password">yourPassword</property>
      <property name="acquireIncrement">5</property>
      <property name="initialPoolSize">10</property>
      <property name="minPoolSize">5</property>
      <property name="maxPoolSize">20</property>
      <property name="maxStatements">0</property>
      <property name="maxStatementsPerConnection">5</property>
   </default-config>

</c3p0-config>
```

3. 在`MySQL`中运行`resource`目录下的`bookstore.sql`，生成该项目所需要的数据库和表。
4. 将项目部署到本地的tomcat上，运行后打开浏览器并访问 `http://localhost:3000`。

## 技术栈

- JDK 17
- MySQL  8.0.11
- Servlet API 4.0.1
- DBUtils  1.7
- JSTL   1.2
- fileupload  1.4
- C3P0   0.9.5.5
- junit 5.8.1 测试
