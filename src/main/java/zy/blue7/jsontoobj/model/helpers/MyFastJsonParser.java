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

        for(Map.Entry<String,Object> entry:jsonObj.entrySet()){

            Object dataTypeObj=entry.getValue();

            String dataName=entry.getKey();

            if(dataName.contains("@")){
                dataName=dataName.replace("@","");
            }


            dataName= StringUtils.toLowerCaseFirstOne(dataName);

            if(dataTypeObj instanceof JSONObject){
                String packagePath1=StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName);
                this.parse(packagePath1, (JSONObject) dataTypeObj);
//  a/b/c 这是个类名字，c后面会添加.java后缀，所以这里将c 大写，然后将 /  变成.  就是要导入的类

//----------------------------获取import列表--------------------------------------------------------
//              这里就是将 c 大写
                String strLast=StringUtils.toUpperCaseFirstOne(packagePath1.substring(packagePath1.lastIndexOf("/")+1));
                //这里是获取 a/b,并且将 / 变成 .
                String strPro=packagePath1.substring(0,packagePath1.lastIndexOf("/")).replace("/",".");

                //拼接生成 要导入的类的字符串
                String importPath=environment.getProperty("coordinate")+"."+strPro+"."+strLast;

//                //放入list集合
                importSet.add(importPath);


//--------------------------------------------------------------------------------

                map.put(dataName,StringUtils.toUpperCaseFirstOne(dataName));//使用key创建一个对象
            }
            else if(dataTypeObj instanceof JSONArray){
                Object objArr = ((JSONArray) dataTypeObj).get(0);
                if(objArr instanceof  JSONObject){
                    this.parse(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName), (JSONObject) objArr);
                    importSet.add(this.getImportPath(StringUtils.toLowerCaseFirstOne(packageRelativePath)+"/"+StringUtils.toLowerCaseFirstOne(dataName)));

                    map.put(dataName,"List<"+StringUtils.toUpperCaseFirstOne(dataName)+">");//使用key创建一个对象
                }else if (objArr instanceof  JSONArray){
                    throw new RuntimeException("别搞了，数组中嵌套数组，这个暂时还没想明白，还没有实现这个功能");
                }else if(objArr instanceof String){
                    map.put(dataName,"List<String>");
                }
                else if(objArr instanceof Integer){
                    map.put(dataName,"List<Integer>");
                }
                else if (objArr instanceof BigDecimal){
                    map.put(dataName,"List<BigDecimal>");
                    importSet.add("java.math.BigDecimal");
                }
                else if (objArr instanceof Number){
                    map.put(dataName,"List<Number>");
                }
                else if(objArr instanceof Boolean){
                    map.put(dataName,"List<Boolean>");
                }



//                如果是数组，则添加list的 import类路径
                importSet.add("java.util.List");
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
