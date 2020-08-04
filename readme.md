
# json生成实体类
## 环境：
### 1.springboot项目
### 2.使用的是gosn解析json字符串
### 3.idea要有lombok插件
### 4.pom文件要导入lombok的jar，因为这里只是创建属性字段，没有创建getter，setter，tostring还有构造函数等等，是使用lombok来替代的
## 优点：
### 1.网上的json转实体类工具只是将实体类都生成到一个文件夹，当不同对象有相同的属性值key，其value也是对象时，两个value对象里面的属性值不同，创建的实体类就会覆盖原来的生成的实体类
### 2.我这个是根据json对象的结构生成对应的实体类包结构，这样就避免了实体类被覆盖的情况。
### 3.生成的实体类的根目录是根据配置文件来指定的，最后json转的大的最顶层的Java实体类的名字也可以通过配置文件来指定

## 用法：
### 1.修改application属性文件，指定生成实体类的根目录，已经生成的最顶层的Java类的名字
### 2.调用MyJsonParser的parse方法执行。



## 缺点：
### 1.首先json对象里面的值不能为null，这里主要是创建实体类，为空就没意义了。
### 2.其次数组不能是  数组里面还是数组，这个暂时还没有实现。
### 3.这里只是将key设置为Java类的属性名，可能出现在json对象中的key的命名合法，但是在Java中命名不合法，还没修改


## 扩展：
### 1.添加了类包名的获取，就是可以正确的设置其类的包名，主要是通过配置文件中的坐标属性，其是要生成的实体类在 某一个项目下的 位置，即该坐标指定了项目（用于生成实体类的根目录的项目）的坐标，即 groupId+artifactId
### 2.添加了类中相关类属性的导入的类的路径，就是 一些 import
