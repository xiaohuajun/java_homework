import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/8 下午2:51
 * @description HelloClassLoader，自定义类加载器 加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class
 * 文件所有字节（x=255-x）处理后的文件
 */
public class HelloClassLoader extends ClassLoader {


  /**
   * 加载对应的类
   *
   * @param name 类名或者包名
   * @return 加载后对应的类
   * @throws ClassNotFoundException
   */
  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    String filePath = name.replace(".", "/") + ".xlass";
    //文件流加载
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
    try {
      //读取数据
      int available = inputStream.available();
      byte[] bytes = new byte[available];
      //放入字节数组
      inputStream.read(bytes);
      // 转换
      byte[] classBytes = decodeByte(bytes);
      //定义类defineClass
      return defineClass(name,classBytes,0,classBytes.length);
    } catch (IOException e) {
      throw new ClassNotFoundException(name,e);
    }finally {
      //关闭流
      if(inputStream != null){
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 解码字节数组
   *
   * @param bytes 字节数组
   * @return 解码后的字节数组
   */
  public static byte[] decodeByte(byte[] bytes){
    //解码后字节数组
    byte[] targetBytes = new byte[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
        targetBytes[i] = (byte) (255 - bytes[i]);
    }
    return targetBytes;
  }


  public static void main(String[] args) {
    //类名
    String className = "Hello";
    //方法名
    String methodName = "hello";
    //创建类加载器
    ClassLoader classLoader = new HelloClassLoader();
    try {
      //加载对应的类
      Class<?> aClass = classLoader.loadClass(className);
      for (Method method : aClass.getMethods()) {
        System.out.println("methodName-->" + method.getName());
      }
      //创建对象
      Object instance = aClass.getDeclaredConstructor().newInstance();
      //调用方法
      Method declaredMethod = aClass.getDeclaredMethod(methodName);
      declaredMethod.invoke(instance);
    } catch (Exception e) {
      throw new RuntimeException("error",e);
    }
  }


}
