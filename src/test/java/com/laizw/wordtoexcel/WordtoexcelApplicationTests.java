package com.laizw.wordtoexcel;

import com.alibaba.excel.EasyExcel;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.laizw.wordtoexcel.entity.BusColumn;
import com.laizw.wordtoexcel.entity.BusColumnCtrl;
import com.laizw.wordtoexcel.entity.BusColumnDTO;
import com.laizw.wordtoexcel.entity.ExcelEntity;
import com.laizw.wordtoexcel.mapper.BusColumnDTOMapper;
import com.laizw.wordtoexcel.service.BusColumnService;
import com.laizw.wordtoexcel.utils.WordToExcelUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WordtoexcelApplicationTests {

    private long start = 0;
    @Resource
    private BusColumnDTOMapper busColumnDTOMapper;
    @Autowired
    private BusColumnService busColumnService;
    @Autowired
    private RedisTemplate redisTemplate;

    @BeforeEach
    public void testBefore() {
        start = System.currentTimeMillis();
    }

    @AfterEach
    public void testAfter() {
        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");
    }


    @Test
    public void test02() throws IOException {

        List<BusColumnDTO> busColumnDTOList = busColumnDTOMapper.selectJoinList(BusColumnDTO.class,
                new MPJLambdaWrapper<BusColumn>()
                        .select(BusColumn::getComment, BusColumn::getKey, BusColumn::getName,BusColumn::getLength, BusColumn::getDefaultValue)
                        .selectAs(BusColumn::getType,BusColumnDTO::getDataType)
                        .select(BusColumnCtrl::getConfig)
                        .selectAs(BusColumnCtrl::getType, BusColumnDTO::getFieldControl)
                        .leftJoin(BusColumnCtrl.class, BusColumnCtrl::getColumnId,BusColumn::getId));
        // busColumnDTOList.forEach(System.out::println);
        List<ExcelEntity> excelEntityList = WordToExcelUtil.busColumnToExcelEntity(busColumnDTOList);
        for (ExcelEntity excelEntity : excelEntityList) {
           redisTemplate.opsForHash().put("excelEntity",excelEntity.getAnnotation(), excelEntity);
        }

    }

    @Test
    public void test01() throws IOException {
        String path = "C:\\Users\\MSI\\Desktop\\新建 DOCX 文档.docx";
        Map<String, Object> map = WordToExcelUtil.importWord(path);
        List<String> list = (List<String>) map.get("data");
        String fileName = map.get("title") + ".xlsx";
        EasyExcel.write(fileName, ExcelEntity.class).sheet("模板").doWrite(WordToExcelUtil.data(list));
    }

    @Test
    public void test03() throws IOException {
        String path = "C:\\Users\\MSI\\Desktop\\新建 DOCX 文档.docx";
        Map<String, Object> map = WordToExcelUtil.importWord(path);
        List<String> list = (List<String>) map.get("data");
        String fileName = map.get("title") + ".xlsx";
        EasyExcel.write(fileName, ExcelEntity.class).sheet("模板").doWrite(busColumnService.enData(list));
    }
}
