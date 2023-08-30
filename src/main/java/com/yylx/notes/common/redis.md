## linux 安装redis

wget http://download.redis.io/releases/redis-3.0.7.tar.gz

tar xzf redis-3.0.7.tar.gz

yum -y install gcc automake autoconf libtool make

cd redis-3.0.7

make

make install

./reids-server 启动服务端

./redis-cli -h {地址} -p {端口} -a {密码}

## mac 安装redis

下载安装包
解压
cd redis-7.0.12
make test
make install

在redis-7.0.12中建立bin，etc，db三个文件夹
mkdir bin
mkdir etc
mkdir db

进入src目录
cp mkreleasehdr.sh to bin
cp redis-benchmark to bin
cp redis-chek-rdb to bin
cp redis-cli to bin
cp redis-server to bin

配置redis.conf文件
![img_2.png](..%2Fwork%2Fsource%2Fimg_2.png)

启动加载配置文件
./redis-server ../redis.config

./redis 启动客户端

## 停止redis服务

当我们需要停止Redis服务器，并且希望将数据保存到磁盘上时，可以使用以下命令：
redis-cli SHUTDOWN SAVE
执行上述命令后，Redis服务器会暂停服务，并将数据保存到磁盘上。
保存的数据将在下次启动Redis服务器时被加载。

当我们希望停止Redis服务器，并且不需要保存数据时，可以使用以下命令：
redis-cli SHUTDOWN NOSAVE
执行上述命令后，Redis服务器会暂停服务，并不会将数据保存到磁盘上。
下次启动Redis服务器时，数据将不会被恢复。

## redis debug执行lua脚本

./redis-cli --ldb --eval lua文件 参数list
LDB 工作模式
默认情况下，当某个客户端向服务端发起debugging会话的时候，并不会阻塞服务端，即服务端仍能正常的处理请求，
而且也能同时处理多个debugging会话，因为会话是并行的，并不会互相干扰
另外一点是，当debugging会话结束的时候，Lua脚本中对redis数据的所有修改都会回滚，
这样做的好处是当多个会话同时debug的时候不会互相干扰，并且可以在不用清理/还原数据的情况下，
使用restart重新开启debug会话时，使每次的执行效果都相同

./redis-cli --ldb-sync-mode --eval lua文件 参数list
我们还可以通过--ldb-sync-mode参数，指定LDB为同步模式，在该工作模式下调试Lua脚本会阻塞服务端，
debug的过程中服务端不会处理其他请求，并且不会对数据做回滚操作

    s 执行下一步
    n 执行下一步，跳过函数
    break 行号 设置断点
    c 跳到下一个断电

## redis lua脚本执行 性能分析

Redis提供了EVAL或EVALSHA命令来执行lua脚本，区别在于EVAL命令将Lua脚本直接传给Redis服务器来执行，
而EVALSHA命令需要先将Lua脚本编译成SHA1哈希值，然后将其存储在SCRIPTS哈希集内，
最后使用EVALSHA命令来执行该脚本。

Redis将Lua脚本编译成一个SHA1哈希值，并将其存储在一个键名为SCRIPTS的Redis哈希集中。
在脚本第一次运行后，这个哈希值将被存储在Redis缓存中。

Redis的配置文件中提供了如下配置项来规定最大执行时长

Lua-time-limit 5000 Lua脚本最大执行时间，默认5秒
但这里有个坑，当一个脚本达到最大执行时长的时候，Redis并不会强制停止脚本的运行，
仅仅在日志里打印个警告，告知有脚本超时。