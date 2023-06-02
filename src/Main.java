import util.BatchOp;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        String sourcePath = "C:\\Users\\宋佳纬\\Desktop\\新建文件夹1";
        String targetPath = "C:\\Users\\宋佳纬\\Desktop\\新建文件夹2";
        BatchOp.changeName(sourcePath,"coolob_hq","ty_rdb");
        BatchOp.changeInside(sourcePath,targetPath);
    }
}