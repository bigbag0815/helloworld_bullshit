程序入口
  v1.DocController
 (v1,v2 是什么意思呢，当版本升级的时候，不至于冲突)

自定义mapper的时候，mapper.java 和mapper.xml 最好用一个名字，最好放同一位置 ，
如果需要自定义其他的位置的时候 ，记得修改applicationContext.xml中的MapperScannerConfigurer配置

mapper.xml 中的namespace名字是mapper的包名+类名

自定义异常  ApiException 传入参数错误状态码和message
throw new ApiException(500, "服务器异常");
