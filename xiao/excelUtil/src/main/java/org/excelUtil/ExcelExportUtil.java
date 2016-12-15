package org.excelUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.google.common.collect.Lists;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import lombok.extern.log4j.Log4j;

/** 
* @ClassName: ExcelExportUtil 
* @Description: 导出excel工具类
* @author chen.gao@baidao.com   
* @date 2016年8月19日 下午4:24:52 
*/
@Log4j
public class ExcelExportUtil {

	public static <T> void exportExcel(Class<T> clazz, Collection<T> data, HttpServletResponse response, String excelName) {
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			os = getOutputStream(response, excelName);
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = createSheet(wwb, excelName);
			List<ExcelTitleModel> titleList = readExcelAnnotation(clazz);
			titleBuild(ws, titleList);
			bodyBuild(ws, titleList, data);
			wwb.write();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				wwb.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @Title: getOutputStream 
	 * @Description: 创建文件流
	 * @param response
	 * @param excelName
	 * @return
	 * @throws IOException
	 */
	private static OutputStream getOutputStream(HttpServletResponse response, String excelName) throws IOException {
		String billingName = excelName + (new SimpleDateFormat(".yyyy-MM-dd.HH:mm:ss").format(new Date())) + ".xls";
		billingName = new String(billingName.getBytes("GBK"), "ISO-8859-1");
		response.setHeader("Content-disposition", "attachment;filename=" + billingName);
		response.setContentType("application/xls");
		return response.getOutputStream();
	}

	/**
	 * @Title: createSheet 
	 * @Description: 创建Sheet
	 * @param wwb
	 * @param excelName
	 * @return
	 */
	private static WritableSheet createSheet(WritableWorkbook wwb, String excelName) {
		WritableSheet ws = wwb.createSheet(excelName == null || "".equals(excelName) ? "sheet" : excelName, 0);
		ws.getSettings().setProtected(false); //设置xls的保护，单元格为只读的
		ws.getSettings().setDefaultColumnWidth(15); // 设置列的默认宽度
		return ws;
	}

	/**
	 * @Title: titleBuild 
	 * @Description: 创建表头
	 * @param ws
	 * @param clazz
	 * @throws WriteException
	 */
	private static void titleBuild(WritableSheet ws, List<ExcelTitleModel> titleList) throws WriteException {
		WritableCellFormat wcfTitle = getTitleStyle();
		for (int i = 0; i < titleList.size(); i++) {
			ws.addCell(new Label(i, 0, titleList.get(i).getName(), wcfTitle));
		}
	}

	/**
	 * @Title: getTitleStyle 
	 * @Description: 表头风格设置
	 * @return
	 * @throws WriteException
	 */
	private static WritableCellFormat getTitleStyle() throws WriteException {
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);
		wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		wcfFC.setAlignment(Alignment.CENTRE); //设置文本对齐
		wcfFC.setBackground(Colour.PALE_BLUE);
		return wcfFC;
	}

	/**
	 * @Title: bodyBuild 
	 * @Description: 创建数据
	 * @param ws
	 * @param titleList
	 * @param data
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static <T> void bodyBuild(WritableSheet ws, List<ExcelTitleModel> titleList, Collection<T> data) throws WriteException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		WritableCellFormat wcfBody = getBodyStyle();
		int i = 1;
		for (T t : data) {
			for (int j = 0; j < titleList.size(); j++) {
				ws.addCell(new Label(j, i, getValue(titleList.get(j), t), wcfBody));
			}
			i++;
		}
	}

	/**
	 * @Title: getBodyStyle 
	 * @Description: 数据格式设置
	 * @return
	 * @throws WriteException
	 */
	private static WritableCellFormat getBodyStyle() throws WriteException {
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat wcfFD = new WritableCellFormat(wfc);
		wcfFD.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		wcfFD.setAlignment(Alignment.RIGHT);//设置文本对齐
		return wcfFD;
	}

	/**
	 * @Title: readExcelAnnotation 
	 * @Description: 从注解中获取excel表头信息
	 * @param clazz
	 * @return
	 */
	private static List<ExcelTitleModel> readExcelAnnotation(Class<?> clazz) {
		List<ExcelTitleModel> titleList = Lists.newArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);
			if (excelTitle != null) {
				ExcelTitleModel excelTitleModel = new ExcelTitleModel();
				excelTitleModel.setName(excelTitle.name());
				excelTitleModel.setSort(excelTitle.sort());
				Method method = MethodUtils.getAccessibleMethod(clazz, "get" + StringUtils.capitalize(field.getName()));
				excelTitleModel.setMethod(method);
				excelTitleModel.setType(excelTitle.type());
				excelTitleModel.setFormat(excelTitle.format());
				titleList.add(excelTitleModel);
			}
		}
		Collections.sort(titleList);
		return titleList;
	}

	/**
	 * @Title: getValue 
	 * @Description: 根据数据类型格式化数据
	 * @param title
	 * @param t
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static <T> String getValue(ExcelTitleModel title, T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object value = title.getMethod().invoke(t);
		if (value == null) {
			return "";
		}
		if (title.getType() == ExcelExportTypeEnum.DOUBLE) {
			String format = StringUtils.isEmpty(title.getFormat()) ? "" : title.getFormat();
			DecimalFormat df = new DecimalFormat(format);
			return df.format(value);
		}
		if (title.getType() == ExcelExportTypeEnum.TIME) {
			return DateUtil.formatDateTime((Date) value);
		}
		if (title.getType() == ExcelExportTypeEnum.DATE) {
			return DateUtil.formatDateTime((Date) value, DateUtil.LONG_DATE_FORMAT);
		}
		if (title.getType() == ExcelExportTypeEnum.FORMAT) {
			String format = StringUtils.isEmpty(title.getFormat()) ? "%s" : title.getFormat();
			return String.format(format, value);
		}
		return title.getMethod().invoke(t).toString();
	}

}
