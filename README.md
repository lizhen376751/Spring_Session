本项目主要是解决session共享的问题\n 

集群情况下，我们会有如下解决方案来解决session共享问题\n
1.spring-session+redis来解决（最为靠谱的一种解决方案）\n 
2.使用cookie来存放session对象信息，但是不安全\n 
3.将session对象存放在数据库中，占用资源\n 
4.tomcat之间来session复制，但是消耗资源\n 
5.nginx情况下我们使用路由的ip绑定策略，但是集群的主要目的就是减轻服务器的压力，失去了集群的意义\n 

这个项目主要是使用spring boot + Spring-session + redis来解决问题\n 
其实我感觉就是redis将session重写了，并返回cookie一个SESSION值，这个值就是redis自己生成的\n 
而redis将这个SESSION值当做key，session对象当做value值存储在redis中\n 
每次请求的时候，因为是集群域名是肯定相同的，cookie中的SESSION将一起带到后端，我们获取到之后，\n 
就可以将redis里面的session对象取出来了，当然中间存储和获取session对象，都由spring-session帮我们处理了\n 

需要注意的问题：\n 
1.每次cookie中都会有SESSION和JSESSIONID，我们每次用的都是redis处理过的SESSION，不用JSESSIONID\n 
2.因为是集群，所以浏览器会将cookie值带过去，如果跨域是不能将cookie里面的属性带过去的\n 
这就需要思考跨域的解决方案了
