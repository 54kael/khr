package com.kael.hr.controller.emp;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.entity.Employee;
import com.kael.hr.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/12 0012
 */
@Api(tags = "下载表格")
@Controller
@RequestMapping("/employee/basic")
public class ExportEmployeeDate {
    @Autowired
    EmployeeService employeeService;

    @ApiOperation("下载数据")
    @GetMapping("/export")
    public void exportEmployeeDate(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 标题头设置
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            // 标题背景设置为红色
            headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            // 标题居中
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 标题字体
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short)14);
            headWriteCellStyle.setWriteFont(headWriteFont);

            // 内容样式设置
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            // 样式内容居中
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // 加进配置
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), Employee.class).registerWriteHandler(horizontalCellStyleStrategy).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(new ObjectMapper().writeValueAsString(map));
        }
    }

    public List<Employee> data() {
        return employeeService.findExportDate();
    }
}
