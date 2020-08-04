package zy.blue7.jsontoobj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author blue7
 * @date 2020/8/4 8:58
 **/
@Component
public class FileUtils{



    /**
     * 通过传入的包路径，生成的Java文件路径以及类中的属性名字和类型来生成 一个Java类
     * @param packageFile 包路径
     * @param filePath  生成的Java类文件路径
     * @param params 类的属性名称和数据类型
     * @throws Exception
     */
    public static void writeToJava(String packageFile, String filePath, Map<String,String> params, List<String> importList) throws Exception {

        StringBuilder stringBuilder=new StringBuilder();
        /**
         * 这里是生成Java类的包，以及应该导入的包，以及lombok注解
         **/
        stringBuilder.append("package "+packageFile+";");

        /**
         * 添加应该导入的类
         */
        stringBuilder.append("\r\n");


        for(String impor:importList){
            stringBuilder.append("\r\n");
            stringBuilder.append("import "+impor+";");
        }



        stringBuilder.append("\r\n");
        stringBuilder.append("\r\n");
        stringBuilder.append("import lombok.AllArgsConstructor;");
        stringBuilder.append("\r\n");
        stringBuilder.append("import lombok.Data;");
        stringBuilder.append("\r\n");
        stringBuilder.append("import lombok.NoArgsConstructor;");
        stringBuilder.append("\r\n");
        stringBuilder.append("\r\n");
        stringBuilder.append("@AllArgsConstructor");
        stringBuilder.append("\r\n");
        stringBuilder.append("@NoArgsConstructor");
        stringBuilder.append("\r\n");
        stringBuilder.append("@Data");
        stringBuilder.append("\r\n");
        stringBuilder.append("\r\n");

        /**
         * 这里是生成作者信息，以及创建时间
         */
        stringBuilder.append("/**");
        stringBuilder.append("\r\n");
        stringBuilder.append("*"+" @author blue7");
        stringBuilder.append("\r\n");
        stringBuilder.append("*"+" @date "+DateUtils.getCurrentDate());
        stringBuilder.append("\r\n");
        stringBuilder.append("**/");
        stringBuilder.append("\r\n");


        /**
         * 这里是生成 类的主体部分
         */
        stringBuilder.append("\r\n");
        String className=filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf("."));
        stringBuilder.append("public class "+className+"{");

        for(Map.Entry<String,String> entry:params.entrySet()){
            String dataName=entry.getKey();
            String dataType=entry.getValue();

            stringBuilder.append("\r\n  private "+dataType+" "+dataName+";");
        }


        stringBuilder.append("\r\n}");

        org.apache.commons.io.FileUtils.writeStringToFile(new File(filePath),stringBuilder.toString(),"utf8");
    }
}
