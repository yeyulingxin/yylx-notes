## mac 查询 端口占用

    lsof -i -P | grep LISTEN

## 拷贝文件和文件夹命令

    // 拷贝文件
    cp 文件  拷贝到的目录文件夹地址
    
    // 递归选项（-r）用来拷贝文件夹
    cp -r /path/to/source/folder /path/to/destination/folder/

## gz文件查询

    zcat file | grep ''

## 查询java进程

    jps -v

## 查询操作系统

cat /etc/os-release

## 连接端口

telnet ip port

## 开放端口

