package com.laizw.wordtoexcel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.laizw.wordtoexcel.entity.BusColumn;
import com.laizw.wordtoexcel.entity.BusColumnCtrl;
import com.laizw.wordtoexcel.entity.BusColumnDTO;
import com.laizw.wordtoexcel.entity.ExcelEntity;
import com.laizw.wordtoexcel.mapper.BusColumnDTOMapper;
import com.laizw.wordtoexcel.mapper.BusColumnMapper;
import com.laizw.wordtoexcel.service.BusColumnService;
import com.laizw.wordtoexcel.utils.WordToExcelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 针对表【bus_column(业务字段表)】的数据库操作Service实现
 */
@Service
public class BusColumnServiceImpl extends ServiceImpl<BusColumnMapper, BusColumn> implements BusColumnService {

    @Resource
    private BusColumnDTOMapper busColumnDTOMapper;

    public List<ExcelEntity> enData(List<String> list) {
        Map<String,ExcelEntity> map = new HashMap<>();
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
            map.put(excelEntity.getAnnotation(), excelEntity);
        }

        List<ExcelEntity> entityList = new ArrayList<>();
        for (String s : list) {
            if (s.contains("日期")) {
                entityList.add(new ExcelEntity(s, "", "", "", "日期型", "", "", "日期控件", "yyyy-MM-dd", "", "", ""));
            } else if (s.contains("人")||s.contains("员")) {
                entityList.add(new ExcelEntity(s, "", "", "", "字符串", "50", "", "单行文本", "", "", "", ""));
                entityList.add(new ExcelEntity(s + "id", "", "", "", "字符串", "50", "", "单行文本", "", "", "", ""));
                entityList.add(new ExcelEntity(s + "签名", "", "", "", "大文本", "", "", "附件上传", "", "", "", ""));
            } else if (s.contains("结论") || s.contains("备注")) {
                entityList.add(new ExcelEntity(s, "", "", "", "大文本", "", "", "单行文本", "", "", "", ""));
            } else if (s.contains("图")) {
                entityList.add(new ExcelEntity(s, "", "", "", "大文本", "", "", "附件上传", "", "", "", ""));
            } else {
                entityList.add(new ExcelEntity(s, "", "", "", "字符串", "50", "", "单行文本", "", "", "", ""));
            }
        }
        for (String s : list) {
            if (map.containsKey(s)) {
                entityList.add(map.get(s));
            } else {
                entityList.add(new ExcelEntity(s, "", "", "", "字符串", "50", "", "单行文本", "", "", "", ""));
            }
        }
        return entityList;
    }
}
