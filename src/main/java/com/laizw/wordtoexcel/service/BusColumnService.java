package com.laizw.wordtoexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laizw.wordtoexcel.entity.BusColumn;
import com.laizw.wordtoexcel.entity.ExcelEntity;

import java.util.List;

/**
* 针对表【bus_column(业务字段表)】的数据库操作Service
*/
public interface BusColumnService extends IService<BusColumn> {
    List<ExcelEntity> enData(List<String> list);
}
