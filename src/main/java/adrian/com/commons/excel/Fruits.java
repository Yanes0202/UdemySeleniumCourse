package adrian.com.commons.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.poiji.annotation.ExcelCellName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fruits {

    @ExcelProperty("sno")
    @ExcelCellName("sno")
    int id;
    @ExcelProperty("fruit_name")
    @ExcelCellName("fruit_name")
    String name;
    @ExcelProperty("color")
    @ExcelCellName("color")
    String color;
    @ExcelProperty("price")
    @ExcelCellName("price")
    int Price;
    @ExcelProperty("season")
    @ExcelCellName("season")
    String Season;
}
