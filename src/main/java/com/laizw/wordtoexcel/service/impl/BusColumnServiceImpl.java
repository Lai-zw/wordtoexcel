package com.laizw.wordtoexcel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laizw.wordtoexcel.entity.BusColumn;
import com.laizw.wordtoexcel.service.BusColumnService;
import com.laizw.wordtoexcel.mapper.BusColumnMapper;
import org.springframework.stereotype.Service;

/**
 * 针对表【bus_column(业务字段表)】的数据库操作Service实现
 */
@Service
public class BusColumnServiceImpl extends ServiceImpl<BusColumnMapper, BusColumn> implements BusColumnService {

}
