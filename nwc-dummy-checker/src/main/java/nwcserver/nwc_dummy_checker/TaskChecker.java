package nwcserver.nwc_dummy_checker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import com.nwchecker.server.utils.CheckerMessage;


public class TaskChecker {
	 private static List<String> compilerErrors = Arrays.asList("NullPointerException", "Compiler error",
	            "RunTimeException", "IOException");

	    public static LinkedHashMap<String, Object> checkTask(CheckerMessage input) {
	        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
	        Random rd = new Random();
	        
	        boolean passed = rd.nextBoolean();
	        if (passed) {
	            result.put("passed", true);
	            //generate result:
	            result.put("time", rd.nextInt(1000));
	            result.put("memory", rd.nextInt(1000));
	            return result;
	        } else {
	            result.put("passed", false);
	            result.put("time", 1000 + rd.nextInt(3000));
	            result.put("memory", 1000 + rd.nextInt(3000));
	            result.put("message", compilerErrors.get(rd.nextInt(compilerErrors.size())));
	            return result;
	        }
	    }

}
