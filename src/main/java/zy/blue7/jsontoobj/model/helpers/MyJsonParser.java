package zy.blue7.jsontoobj.model.helpers;

import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import zy.blue7.jsontoobj.model.interfaces.IMyJsonParser;
import zy.blue7.jsontoobj.utils.FileUtils;
import zy.blue7.jsontoobj.utils.MyProperties;
import zy.blue7.jsontoobj.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author blue7
 * @date 2020/8/4 9:54
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class MyJsonParser implements IMyJsonParser {

    private JsonParser jsonParser;
    @Autowired
    private Environment environment;

    @Override
    public void parse(String packagePath,String jsonStr) throws Exception {
        jsonParser=new JsonParser();
        JsonObject jsonObject= (JsonObject) jsonParser.parse(jsonStr);
//        System.out.println(jsonObject.toString());
        this.parse(packagePath,jsonObject);
    }

    @Override
    public void parse(String packagePath, JsonObject jsonObj) throws Exception {
//      这里传进来的classname都是小写 a/b/c


        Map<String,String> map=new HashMap<>();
        for(Map.Entry<String, JsonElement> entry:jsonObj.entrySet()){


            JsonElement dataTypeEl=entry.getValue();

            String dataName=entry.getKey();

//            System.out.println(entry.toString());
            /**
             * 判断属性 是什么类型，生成对应的数据类型名字和数据属性名
             */
            if(dataTypeEl.isJsonPrimitive()){  //如果值是基本类型
                JsonPrimitive type=dataTypeEl.getAsJsonPrimitive();

                if(type.isBoolean()){
                    map.put(dataName,"boolean");
                }else if(type.isNumber()){
                    map.put(dataName,"Number");//这里我还不清楚怎么判断 int double等等细致的数据类型
                }else if (type.isString()){
                    map.put(dataName,"String");
                }else{
                    throw new RuntimeException("不知道的数据类型");
                }
            }else if(dataTypeEl.isJsonObject()){//如果是对象，就调用本方法递归下去
                this.parse(StringUtils.toLowerCaseFirstOne(packagePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName), (JsonObject) dataTypeEl);
                map.put(dataName,StringUtils.toUpperCaseFirstOne(dataName));//相当于用key创建一个类，类名就是key，类在本类的属性名也是key，也可以小写

            }else if(dataTypeEl.isJsonArray()){//如果是数组
                JsonElement jsonElement = dataTypeEl.getAsJsonArray().get(0);
                if(jsonElement.isJsonPrimitive()){
                    JsonPrimitive type=jsonElement.getAsJsonPrimitive();

                    if(type.isBoolean()){
                        map.put(dataName,"List<boolean>");
                    }else if(type.isNumber()){
                        map.put(dataName,"List<Number>");//这里我还不清楚怎么判断 int double等等细致的数据类型
                    }else if (type.isString()){
                        map.put(dataName,"List<String>");
                    }else{
                        throw new RuntimeException("不知道的数据类型");
                    }
                }else if(jsonElement.isJsonObject()){//如果数组里是 对象，就生成list《dataname》，就调用本方法递归下去
                    this.parse(StringUtils.toLowerCaseFirstOne(packagePath)+"/"+ StringUtils.toLowerCaseFirstOne(dataName), (JsonObject) jsonElement);
                    map.put(dataName,"List<"+StringUtils.toUpperCaseFirstOne(dataName)+">");//相当于用key创建一个类，类名就是key，类在本类的属性名也是key，也可以小写

                }else if(jsonElement.isJsonArray()){
                    throw new RuntimeException("数组里嵌套数组，太麻烦，没实现，也用的不多");
                }else if(jsonElement.isJsonNull()){
                    throw new RuntimeException("数组里是null，这里只是为了 生成实体类，请不要设置为null，方便判断生成的实体类的数据类型");
                }

            }else if(dataTypeEl.isJsonNull()){//如果为空
                throw new RuntimeException("数组里是null，这里只是为了 生成实体类，请不要设置为null，方便判断生成的实体类的数据类型");
            }

        }
//      判断classname是否包含 / 包含说明是包下的一个类，要将其最后面的类名首字母大写 a.java--》A.java
        if(!packagePath.contains("/")){
            FileUtils.writeToJava("zy.blue7.model", environment.getProperty("rootPath")+"/" +StringUtils.toUpperCaseFirstOne(packagePath)+".java",map);
        }else{
            //这里是将类名取出来，改为大写，因为classname是  a/b/c.java  ---->a/b/C.java, 包名小写，类名大写
            String classNameUpper=StringUtils.toUpperCaseFirstOne(packagePath.substring(packagePath.lastIndexOf("/")+1));
            String classNamePro=packagePath.substring(0,packagePath.lastIndexOf("/")+1);
            FileUtils.writeToJava("zy.blue7.model", environment.getProperty("rootPath")+"/" +classNamePro+classNameUpper+".java",map);
        }

    }
}
