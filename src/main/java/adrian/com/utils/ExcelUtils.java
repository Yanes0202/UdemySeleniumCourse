package adrian.com.utils;

import adrian.com.commons.excel.Fruits;
import com.alibaba.excel.EasyExcel;
import com.poiji.bind.Poiji;

import java.io.File;
import java.util.List;

public class ExcelUtils {

    public static List<Fruits> convertExcelToFruits(String filePath) {
        System.out.println(filePath);
        return Poiji.fromExcel(new File(filePath), Fruits.class);
    }

    public static void fruitsToExcel(String filePath, List<Fruits> fruits) {
        EasyExcel.write(filePath, Fruits.class).sheet().doWrite(fruits);
    }
}
