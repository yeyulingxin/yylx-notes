## redis的数据结构

String
list
hash
set
zset
stream

## redis的数据结构底层结构

string：int、embstr、（SDS）建单动态字符串
list：zipList、linkedList
hash：zipList、hashtable
set：intset、hashtable
zset：zipList、skipList

## redis的数据结构使用大小范围

string

    int：整数值
    embstr：长度<=39字节
    SDS：>39字节

    SDS说明：
        1. SDS通过len字段存储长度；
        2. SDS修改时，检查空间是否足够，不够的话自动扩容；
        3. SDS 能减少修改字符串时带来的内存重分配次数：
            空间预分配：  当SDS 扩容时不只是会增加需要的空间大小，还会额外的分配一些未使用的空间。
                        分配的规则是：如果分配后SDS的长度小于 1MB，那么会分配等于分配后SDS 的大小的未使用空间，
                        简单说就是，SDS 动态分配后是 16KB，那么就会多分配 16KB 的未使用空间；如果小于 1MB，那么久分配 1MB 的未使用空间。
            惰性空间释放：惰性空间释放用于优化 SDS 的字符串缩短操作：当 SDS 的 API 需要缩短 SDS 保存的字符串时，并不会立即内存重分配来回收多出来的字节，
                        而是用 free 来记录未使用空间。

list

    ziplist：长度 <64 字节 && 数量 <512 个 
    linkedlist：其他

hash

    ziplist：长度 <64 字节 && 数量 <512 个
    hashtable：其他

Set

    intset：元素都是整数值 && 数量 <512 个
    hashtable：其他

zset

    zipList：数量 <128 个 && 每个元素成员 <64 字节
    skipList：其他

## redis 常用场景

## redis集群场景

主从

Redis集群主要有三种模式：
主从复制模式（Master-Slave）
哨兵模式（Sentinel）
Cluster模式

proxy + 哨兵

    可分片，横向扩展
    容灾方案：异地机房部署从库

proxy + cluster

    如果redis实例重启后ip变化，业务找不到redis cluster入口
    动态扩缩容节点，为redis维护客户端链接
    切换后端集群，实现业务无感知的平滑迁移

proxy + 多cluster

物理集群

容器化集群

## 时间事件和文件事件

https://zhuanlan.zhihu.com/p/555813131

