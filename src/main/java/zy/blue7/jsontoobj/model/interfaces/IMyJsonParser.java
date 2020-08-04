package zy.blue7.jsontoobj.model.interfaces;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

/**
 * @author blue7
 * @date 2020/8/4 9:51
 **/
@Component
public interface IMyJsonParser {
    void parse(String packagePath,String jsonStr) throws Exception;
    void parse(String packagePath, JsonObject jsonObj) throws Exception;
}
