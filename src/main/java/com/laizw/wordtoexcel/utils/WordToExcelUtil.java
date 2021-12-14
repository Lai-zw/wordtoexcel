package com.laizw.wordtoexcel.utils;

import com.laizw.wordtoexcel.entity.BusColumnDTO;
import com.laizw.wordtoexcel.entity.ExcelEntity;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class WordToExcelUtil {
    /**
     * 读取doc文件
     *
     * @throws IOException
     */
    public static Map<String, Object> importWord(String path) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        XWPFDocument document = new XWPFDocument(new FileInputStream(path));
        if (!document.getTables().isEmpty() || !document.getParagraphs().isEmpty()) {
            // 获取全部段落
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                String[] split = paragraph.getText().split(":|：|/");
                for (String s : split) {
                    s = s.replaceAll("\\s*", "");
                    s = s.replaceAll("^[0-9]*", "");
                    list.add(s);
                }
            }
            // 获取文档中的所有表格
            List<XWPFTable> tables = document.getTables();
            List<XWPFTableRow> rows;
            List<XWPFTableCell> cells;
            for (XWPFTable table : tables) {
                // 获取表格对应行
                rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    // 获取对应的单元格
                    int size = row.getTableCells().size();
                    cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        String[] split = cell.getText().split(":|：|/");
                        for (String s : split) {
                            s = s.replaceAll("\\s*", "");
                            s = s.replaceAll("^[0-9]*", "");
                            list.add(s);
                        }
                    }
                }
            }
            list.removeIf(String::isEmpty);
            map.put("title", list.get(0));
            list.remove(0);
            map.put("data", list);
        }
        return map;
    }

    public static List<ExcelEntity> data(List<String> list) {
        // LinkedList<String> linkedList = list.stream().collect(Collectors.toCollection(LinkedList::new));
        List<ExcelEntity> entityList = new ArrayList<>();
        for (String s : list) {
            if (s.contains("日期")) {
                entityList.add(new ExcelEntity(s, "", "", "", "日期型", "", "", "日期控件", "yyyy-MM-dd", "", "", ""));
            } else if (s.contains("人")) {
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
        entityList.add(new ExcelEntity("创建人", "creater", "creater", "", "字符串", "50", "${currentUserName}", "单行文本", "", "", "", ""));
        entityList.add(new ExcelEntity("创建人id", "createrId", "creater_id", "", "字符串", "50", "${currentUserId}", "单行文本", "", "", "", ""));
        entityList.add(new ExcelEntity("创建人部门", "createrDep", "creater_dep", "", "字符串", "50", "${currentOrgName}", "单行文本", "", "", "", ""));
        entityList.add(new ExcelEntity("创建人部门id", "createrDepId", "creater_dep_id", "", "字符串", "50", "${currentOrgId}", "单行文本", "", "", "", ""));
        entityList.add(new ExcelEntity("创建日期", "createTime", "create_time", "", "日期型", "", "${currentDateTime}", "日期控件", "yyyy-MM-dd", "", "", ""));
        return entityList;
    }

    public static List<ExcelEntity> busColumnToExcelEntity(List<BusColumnDTO> columnLists) {
        List<ExcelEntity> entityList = new ArrayList<ExcelEntity>();
        int size = columnLists.size();
        for (BusColumnDTO columnList : columnLists) {
            entityList.add(new ExcelEntity(
                    columnList.getComment(),
                    columnList.getKey(),
                    columnList.getName(),
                    "",
                    "",
                    columnList.getLength().toString(),
                    columnList.getDefaultValue(),
                    "",
                    "",
                    "",
                    "",
                    ""));
        }
        return entityList;
    }
}
