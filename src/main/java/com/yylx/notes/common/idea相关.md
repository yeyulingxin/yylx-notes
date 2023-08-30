## mac 项目启动慢

打开 系统偏好设置 -> 共享，找到其中的电脑名称对应的主机名

打开 终端 sudo vim /etc/hosts, 在localhost后边加上主机名

问题在于 如果服务的启动使用了主机名，hosts文件中没有指明主机名和ip的关系，无法快速解析主机名的ip，
项目启动后需要执行系统算法获取，比较慢

## idea maven failed to transfer from http://0.0.0.0/ during a previous attempt

Since Maven 3.8.1 http repositories are blocked.

Blocked mirror for repositories 问题是由于Maven3.8.1 开始默认在配置文件中block了Http连接。

idea解决此问题有如下方案

1、使用的Maven为3.8以下的版本
2、注释以下配置：当idea加载外部配置文件时会优先加载安装目录下\plugins\maven\lib\maven3\conf文件夹内的settings.xml，

    <mirror>
    <id>maven-default-http-blocker</id>
    <mirrorOf>external:http:*</mirrorOf>
    <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
    <url>http://0.0.0.0/</url>
    <blocked>true</blocked>
    </mirror>

即可生效