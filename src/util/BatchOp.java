package util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import java.io.*;

public class BatchOp {
    public static void changeName(String path,String sourceName , String targetName){
        File dic = new File(path);
        File[] list = dic.listFiles();
        if(dic.exists() && dic.isDirectory()){
            for (File file : list) {
                String name = file.getName();
                if(name.toLowerCase().contains(sourceName)){
                    String newName = name.replace(sourceName, targetName);
                    File newFile = new File(path + "\\" + newName);
                    file.renameTo(newFile);
                }else System.err.println("文件名中不包含要替换的内容");
            }
        }else System.err.println("文件或文件夹不存在");
    }

    public static void changeInside(String sourcePath,String targetPath) throws IOException {
        File dic = new File(sourcePath);
        File[] list = dic.listFiles();
        if(dic.exists() && dic.isDirectory()){
            for (File file: list) {
                if(file.isFile()){
                    String name = file.getName();
                    FileInputStream inputStream = new FileInputStream(file);
                    JSONObject jsonObj = JSON.parseObject(inputStream);
                    inputStream.close();
                    jsonObj.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("writer").replace("name","mysqlWriter");
                    jsonObj.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("writer").getJSONObject("parameter").remove("writerThreadCount");
                    jsonObj.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("writer").getJSONObject("parameter").remove("activeMemPercent");
                    FileWriter writer = new FileWriter(targetPath + "\\" + name);
                    writer.write("");
                    writer.write(jsonObj.toString());
                    writer.flush();
                    writer.close();
                }else System.err.println("还有下层文件夹，你看一下" + file.getName() + "里的东西要不要改，要改的话放出来");
            }
        }else System.err.println("文件或文件夹不存在");
    }
}
