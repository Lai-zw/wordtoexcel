package com.laizw.wordtoexcel;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.laizw.wordtoexcel.entity.BusColumn;
import com.laizw.wordtoexcel.mapper.BusColumnMapper;
import com.laizw.wordtoexcel.utils.WordToExcelUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WordtoexcelApplicationTests {

    private long start = 0;
    @Resource
    private BusColumnMapper busColumnMapper;

    @BeforeEach
    public void testBefore() {
        start = System.currentTimeMillis();
    }
    @AfterEach
    public void testAfter() {
        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");
    }

    
    @Test
    public void test02(){
        System.out.println(("----- selectAll method test ------"));
        List<BusColumn> busColumnList = busColumnMapper.selectList(null);
        busColumnList.forEach(System.out::println);
    }

    @Test
    public void test01() throws IOException {
        String path = "C:\\Users\\MSI\\Desktop\\新建 DOCX 文档.docx";
        Map<String, Object> map = WordToExcelUtil.importWord(path);
        List<String> list = (List<String>) map.get("data");
        String fileName = map.get("title") + ".xlsx";
        // EasyExcel.write(fileName, ExcelEntity.class).sheet("模板").doWrite(wordToExcel.getWordToExcelService().data(list));
    }

}
