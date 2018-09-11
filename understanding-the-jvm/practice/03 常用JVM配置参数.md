>- Trace跟踪参数  
>- 堆的分配参数  
>- 栈的分配参数

- jvm的GC日志分析  
https://blog.csdn.net/doc_sgl/article/details/46594123

#### Trace跟踪参数  
>-XX:+PrintGC 可以打印GC的简要信息  
-XX:+PrintGCDetails 打印GC详细信息  
-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）  
-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）  
-XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息  
-XX:+PrintGCApplicationStoppedTime 输出GC造成应用暂停的时间  
-Xloggc:../logs/gc.log 日志文件的输出路径  
>>在程序末尾加System.gc();即可使参数生效.