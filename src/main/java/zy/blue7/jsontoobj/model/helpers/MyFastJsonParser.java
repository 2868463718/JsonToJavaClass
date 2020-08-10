package zy.blue7.jsontoobj.model.helpers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import zy.blue7.jsontoobj.model.interfaces.IMyJsonParser;
import zy.blue7.jsontoobj.utils.FileUtils;
import zy.blue7.jsontoobj.utils.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author blue7
 * @date 2020/8/6 17:58
 **/
@Component
public class MyFastJsonParser implements IMyJsonParser {
    @Autowired
    Environment environment;


    @Override
    public void parse(String packageRelativePath, String jsonStr) throws Exception {
        Object object = JSON.parse(jsonStr);
        this.parse(packageRelativePath, (JSONObject) object);
    }

    @Override
    public void parse(String packageRelativePath, JsonObject jsonObj) throws Exception {

    }


    public void parse(String packageRelativePath, JSONObject jsonObj) throws Exception {
        Set<String> importSet=new HashSet<>();
        Map<String,String> map=new HashMap<>();
//这里遍历json对象的属性，生成对应的类属性与类
        for(Map.Entry<String,Object> entry:jsonObj.entrySet()){
//这里是 将value的类型设为 类的属性的 类型
            Object dataTypeObj=entry.getValue();
//这里是将 key作为属性名，属性名小写
            String dataName=entry.getKey();

//            这里是只考虑一种情况，还需要通过配置文件来 设置所有的不合法的变量值
            if(dataName.contains("@")){
                dataName=dataName.replace("@","");
            }

//          属性名小写
            dataName= StringUtils.toLowerCaseFirstOne(dataName);

            if(dataTypeObj instanceof JSONObject){
//                String packagePath1=StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName);
                this.parse(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName), (JSONObject) dataTypeObj);
//  a/b/c 这是个类名字，c后面会添加.java后缀，所以这里将c 大写，然后将 /  变成.  就是要导入的类

//----------------------------获取import列表--------------------------------------------------------
////              这里就是将 c 大写
//                String strLast=StringUtils.toUpperCaseFirstOne(packagePath1.substring(packagePath1.lastIndexOf("/")+1));
//                //这里是获取 a/b,并且将 / 变成 .
//                String strPro=packagePath1.substring(0,packagePath1.lastIndexOf("/")).replace("/",".");
//
//                //拼接生成 要导入的类的字符串
//                String importPath=environment.getProperty("coordinate")+"."+strPro+"."+strLast;


//                //放入list集合
//                importSet.add(importPath);
                    importSet.add(this.getImportPath(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName)));

//--------------------------------------------------------------------------------
//              如果是对象，就使用使用key首字母大写作为类型，key首字母小写作为属性名，上面已经使用key首字母大写创建一个对应的类了
                map.put(dataName,StringUtils.toUpperCaseFirstOne(dataName));//使用key创建一个对象
            }
            else if(dataTypeObj instanceof JSONArray){
//                如果是数组，获取其中一个值，判断其类型，如果是对象，那么就是list集合对象，如果是基本类型，就是list集合（里面存放的是基本类型），如果是数组
//                Object objArr = ((JSONArray) dataTypeObj).get(0);
//                if(objArr instanceof  JSONObject){
//                    this.parse(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName), (JSONObject) objArr);
//                    importSet.add(this.getImportPath(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName)));
//
//                    map.put(dataName,"List<"+StringUtils.toUpperCaseFirstOne(dataName)+">");//使用key创建一个对象
//                }else if (objArr instanceof  JSONArray){
//                    importSet.add(this.getImportPath(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName)));
//
//                    map.putAll(this.parseArray(dataName,"List<"+StringUtils.toUpperCaseFirstOne(dataName)+">",StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName),(JSONArray)objArr));
//                    throw new RuntimeException("别搞了，数组中嵌套数组，这个暂时还没想明白，还没有实现这个功能");
//                }else if(objArr instanceof String){
//                    map.put(dataName,"List<String>");
//                }
//                else if(objArr instanceof Integer){
//                    map.put(dataName,"List<Integer>");
//                }
//                else if (objArr instanceof BigDecimal){
//                    map.put(dataName,"List<BigDecimal>");
//                    importSet.add("java.math.BigDecimal");
//                }
//                else if (objArr instanceof Number){
//                    map.put(dataName,"List<Number>");
//                }
//                else if(objArr instanceof Boolean){
//                    map.put(dataName,"List<Boolean>");
//                }

//                ---------------------这里是我又改变的，不晓得行不行，上面的肯定行------------------------------------------
//                首先数组里面不是基本类型就是对象或者数组（里面也是上面的类型），有可能会创建一个对象，所有传入类的相对路径，用于创建类和导入类路径
                map.putAll(this.parseArray(dataName,StringUtils.toUpperCaseFirstOne(dataName),StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName), (JSONArray) dataTypeObj,importSet));

//                ---------------------------------------------------------------
//                如果是数组，则添加list的 import类路径
//                importSet.add("java.util.List");
            }
            else if(dataTypeObj instanceof String){
                map.put(dataName,"String");
            }
            else if(dataTypeObj instanceof Integer){
                map.put(dataName,"int");
            }
            else if (dataTypeObj instanceof BigDecimal){
                map.put(dataName,"BigDecimal");
                importSet.add("java.math.BigDecimal");
            }
            else if (dataTypeObj instanceof Number){
                map.put(dataName,"Number");
            }
            else if(dataTypeObj instanceof Boolean){
                map.put(dataName,"boolean");
            }
        }

//        判断classname是否包含 / 包含说明是包下的一个类，要将其最后面的类名首字母大写 a.java--》A.java
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

    private Map<String,String> parseArray(String dataName, String dataType, String packageRelativePath, JSONArray objArr,Set<String> importSet) throws Exception {
        Map<String,String> map=new HashMap<>();
        if(objArr==null){
            throw  new RuntimeException("数组对象为空，这不应该的，请确定json字符串的每一个key都应该有值");
        }
        Object obj=objArr.get(0);
        if(obj instanceof JSONObject){
            this.parse(packageRelativePath, (JSONObject) obj);
            importSet.add(this.getImportPath(packageRelativePath));
            map.put(dataName,"List<"+dataType+">");
        }else if(obj instanceof JSONArray){
//               List<List<A>> a  数组中嵌套数组
            map.putAll(this.parseArray(dataName,"List<"+dataType+">",packageRelativePath, (JSONArray) obj,importSet));
        }else if(obj instanceof String){
//            List<List<String>>,由于是数组，集合中的类型是基本类型，所以需要再加一层list
            map.put(dataName,"List<"+this.replaceStr(dataType,"String")+">");
        }
        else if(obj instanceof Integer){
            map.put(dataName,"List<"+this.replaceStr(dataType,"Integer")+">");
        }
        else if (obj instanceof BigDecimal){
            map.put(dataName,"List<"+this.replaceStr(dataType,"BigDecimal")+">");
            importSet.add("java.math.BigDecimal");
        }
        else if (obj instanceof Number){
            map.put(dataName,"List<"+this.replaceStr(dataType,"Number")+">");
        }
        else if(obj instanceof Boolean){
            map.put(dataName,"List<"+this.replaceStr(dataType,"Boolean")+">");
        }
        importSet.add("java.util.List");
        return map;
    }

    /**
     * 替换list集合中存放的数据类型字符串
     * @param dataType
     * @param string
     * @return
     */
    private String replaceStr(String dataType, String string) {
        //       List<List<dataType>> ----->     List<List<String>>
        if(dataType.contains("List")){
            String strPro=dataType.substring(0,dataType.indexOf("<")+1);
            String strLast=dataType.substring(dataType.indexOf(">"));
            return strPro+string+strLast;
        }
        return string;
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
