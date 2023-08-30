启动arthas

curl -O https://alibaba.github.io/arthas/arthas-boot.jar
java -jar arthas-boot.jar

彻底退出Arthas
stop

watch
watch com.xiaomi.kfs.queueplatform.api.service.QueueRequestService cancelQueueRequest
'{params, throwExp, returnObj}' -x 4
-x 4 展开4层

watch com.mi.info.cc.pss.service.sku.impl.SkuBaseServiceImpl getGoodsIdCategoryPathMap
'{params, throwExp, returnObj}' -x 4 -e