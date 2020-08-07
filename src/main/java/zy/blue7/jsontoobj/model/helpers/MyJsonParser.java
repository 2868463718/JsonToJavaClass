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
import zy.blue7.jsontoobj.utils.StringUtils;

import java.util.*;

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
    public void parse(String packageRelativePath,String jsonStr) throws Exception {
        jsonParser=new JsonParser();
        JsonObject jsonObject= (JsonObject) jsonParser.parse(jsonStr);
//        System.out.println(jsonObject.toString());
        this.parse(packageRelativePath,jsonObject);
    }

    @Override
    public void parse(String packageRelativePath, JsonObject jsonObj) throws Exception {
//      这里传进来的classname都是小写 a/b/c


        Set<String> importSet=new HashSet<>();
        Map<String,String> map=new HashMap<>();
        for(Map.Entry<String, JsonElement> entry:jsonObj.entrySet()){


            JsonElement dataTypeEl=entry.getValue();


            String dataName=entry.getKey();
//            这里是硬编码只考虑一种情况，如果不去掉@，转换成的Java 属性名 就会出现不合法的情况
//            当然可能还有其他不合法的情况，这里先不说，
            if(dataName.startsWith("@")){
                dataName=dataName.substring(1);
            }
 //          将类的属性名首字母小写，后面需要大写的地方再更改
            dataName=StringUtils.toLowerCaseFirstOne(dataName);

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
                String packagePath1=StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName);
                this.parse(packagePath1, (JsonObject) dataTypeEl);
System.out.println(packagePath1);
//  a/b/c 这是个类名字，c后面会添加.java后缀，所以这里将c 大写，然后将 /  变成.  就是要导入的类

//----------------------------获取import列表--------------------------------------------------------
//              这里就是将 c 大写
                String strLast=StringUtils.toUpperCaseFirstOne(packagePath1.substring(packagePath1.lastIndexOf("/")+1));
                //这里是获取 a/b,并且将 / 变成 .
                String strPro=packagePath1.substring(0,packagePath1.lastIndexOf("/")).replace("/",".");

                //拼接生成 要导入的类的字符串
                String importPath=environment.getProperty("coordinate")+"."+strPro+"."+strLast;

                //放入list集合
                importSet.add(importPath);


//--------------------------------------------------------------------------------
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
                    this.parse(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+ StringUtils.toLowerCaseFirstOne(dataName), (JsonObject) jsonElement);
System.out.println(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+ StringUtils.toLowerCaseFirstOne(dataName));
                    importSet.add(this.getImportPath(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+ StringUtils.toLowerCaseFirstOne(dataName)))  ;

                map.put(dataName,"List<"+StringUtils.toUpperCaseFirstOne(dataName)+">");//相当于用key创建一个类，类名就是key，类在本类的属性名也是key，也可以小写

                }else if(jsonElement.isJsonArray()){
                    throw new RuntimeException("数组里嵌套数组，太麻烦，没实现，也用的不多");
                }else if(jsonElement.isJsonNull()){
                    throw new RuntimeException("数组里是null，这里只是为了 生成实体类，请不要设置为null，方便判断生成的实体类的数据类型");
                }
//                如果是数组，则添加list的 import类路径
                importSet.add("java.util.List");
            }else if(dataTypeEl.isJsonNull()){//如果为空
                throw new RuntimeException("数组里是null，这里只是为了 生成实体类，请不要设置为null，方便判断生成的实体类的数据类型");
            }

        }
//      判断classname是否包含 / 包含说明是包下的一个类，要将其最后面的类名首字母大写 a.java--》A.java
        if(!packageRelativePath.contains("/")){
            FileUtils.writeToJava(environment.getProperty("coordinate"), environment.getProperty("rootPath")+"/" +StringUtils.toUpperCaseFirstOne(packageRelativePath)+".java",map,importSet);
        }else{
            //这里是将类名取出来，改为大写，因为classname是  a/b/c  ---->a/b/C, 包名小写，类名大写,这里C 是类名，下面会拼写.java
            String classNameUpper=StringUtils.toUpperCaseFirstOne(packageRelativePath.substring(packageRelativePath.lastIndexOf("/")+1));
            //这里是截取前缀，即a/b/
            String classNamePro=packageRelativePath.substring(0,packageRelativePath.lastIndexOf("/")+1);

            //获取包名的相对位置，相对于坐标的相对位置，就是 坐标 加这个字符串就是这个类的包名
            String packAgeRelativeName=classNamePro.substring(0,classNamePro.lastIndexOf("/")).replace("/",".");

            FileUtils.writeToJava(environment.getProperty("coordinate")+"."+packAgeRelativeName, environment.getProperty("rootPath")+"/" +classNamePro+classNameUpper+".java",map,importSet);
        }

    }


    /**
     * 获取需要导入的类路径
     * @param packageRelativePath  包的相对路径就是生成的实体类的根路径到该类的全限定名之间的路径
     * @return
     */
    private String getImportPath(String packageRelativePath){
//        这里就是将 c 大写
        String strLast=StringUtils.toUpperCaseFirstOne(packageRelativePath.substring(packageRelativePath.lastIndexOf("/")+1));
        //这里是获取 a/b,并且将 / 变成 .
        String strPro=packageRelativePath.substring(0,packageRelativePath.lastIndexOf("/")).replace("/",".");

        //拼接生成 要导入的类的字符串
        String importPath=environment.getProperty("coordinate")+"."+strPro+"."+strLast;
        return importPath;
    }

}
