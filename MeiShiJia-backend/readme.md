1.配置需要：
确保java的版本和javac的版本相同，同时跟mvn的版本兼容

![](https://github.com/Shdityr/MSJ/blob/main/MeiShiJia-backend/image/485ccb34fc6cc6f379d634ac98f4f135.png)
![](https://github.com/Shdityr/MSJ/blob/main/MeiShiJia-backend/image/5b2310916f113d1aec5c46163ffa022b.png)
![](https://github.com/Shdityr/MSJ/blob/main/MeiShiJia-backend/image/d5eddff0add78cdc5e30e4ba02c32e31.png)
如上图所示，java-version都是17.0.10，一致

2.本地运行
在根路径运行
mvn spring-boot:run

3.打包后部署
在根路径运行
mvn clean package
生成target文件夹，里面有一个后缀jar的文件
java -jar xxx.jar
