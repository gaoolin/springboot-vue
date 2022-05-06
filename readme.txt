1、mac电脑创建mysql数据库是报错（errno:13无权限）
    1-终端中输入 sudo su 输入密码,显示 sh-3.2
    2-用绝对路径登入mysql，/usr/local/mysql/bin/mysql -u root -p 输入密码
    3-查看数据库数据存放路径 show variables like '%datadir%'
    4-修改文件夹的权限，chown -R mysql:mysql /usr/local/mysql/data
2、封装axios工具类时，需引入对应的包，方法为：必须 cd vue 文件夹内，然后安装 npm i axios -S
3、Mac电脑查看端口是否被占用
    1-sudo lsof -i tcp:9090(如果没被占用无显示，如被占用则找到PID，杀掉进程（程序）
    2-sudo kill -9 PID
    3-查看端口的常用命令 lsof, lsof | less, lsof -i:xxxx
4、@RequestMapping("/user")映射请求不生效问题
    主程序SpringbootEndApplication必须和各个业务层的包同级，放在artifacts包下，其他各业务层包也必须放在artifacts下。
    这样Springboot在启动的时候才能把各组件扫描到放入ioc容器中。
5、SpringBoot单元测试注解(自动注入servicei或dao报空指针异常)
      1）@RunWith注解
          @RunWith注解是类级别的注解, 它提供了一种更改测试运行程序的默认行为的机制。简单理解，@RunWith注解就是一个运行器，其接受一个类的签名来指定是使用什么类、在什么环境下运行。需要注意的是，该注解的参数必须是Runner类的子类，JUnit本身有提供几个Runner，默认值为JUnit4，一个常见的替代方法是参数化类。
          当使用@RunWith注解对JUnit测试进行注解时，将对测试的生命周期和测试的运行方式进行一些更改。
          比如@RunWith(JUnit4.class)就是指用JUnit4来运行。
          比如@RunWith(SpringJUnit4ClassRunner.class)就是让测试运行于Spring测试环境。
          比如@RunWith(Suite.class)的话就是一套测试集合。
          比如@RunWith(SpringJUnit4ClassRunner.class)使用了Spring的SpringJUnit4ClassRunner，以便在测试开始的时候自动创建Spring的应用上下文。一般情况下想创建spring容器的话，需要通过web.xml配置classloder，但如果注解了@RunWith注解的话，就可以直接使用spring容器，直接使用@Test注解，不用启动spring容器。
          在上面的代码中，使用了@RunWith(SpringRunner.class)，实际上，SpringRunner类就是继承于Spring的SpringJUnit4ClassRunner类。
      2）@SpringBootTest注解
          @SpringBootTest注解是一个Spring Boot提供的注解，通过这个注解可以使JUnit单元测试跑在Spring Boot的运行环境中。
          我们可以通过classes参数来指定Spring Boot的启动类。