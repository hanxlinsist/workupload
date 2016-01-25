package hxl.insist.oa.view.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Team;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ExportExcelAction extends BaseAction<Team> {

	/**
	 * ExcelBean信息页面
	 */
	public String excelBeanInfoUI() {
		List<Team> teamList = teamService.findByProject(getSessionAccount().getProject());
		Map<String, Integer> map = new HashMap<String, Integer>();
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (teamList != null && teamList.size() > 0) {
			for (Team team : teamList) {
				String academy = team.getAcademy(); //获取当前团队学院信息
				Integer rowNum = map.get(academy);  //获取待插入行号
				if (rowNum == null) {  //还没有当前学院Sheet信息，所以创建一个学院Sheet
					HSSFSheet sheet = workbook.createSheet(academy);
					createExcelTitle(sheet, map, academy);
					createExcelBody(sheet, map, team);
				} else {  //已经有当前学院Sheet信息
					createExcelBody(workbook.getSheet(academy), map, team);
				}
			}
			ActionContext.getContext().put("teamList", teamList);
			try {
				generateExcel(workbook);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "excelBeanInfo";
	}
	
	/**
	 * 创建Excel表头项
	 */
	private void createExcelTitle(Sheet sheet, Map<String, Integer> map, String academy) {
		Row headRow = sheet.createRow(0); //创建给定Sheet的表头行

		headRow.createCell(0).setCellValue("作品名称");
		headRow.createCell(1).setCellValue("学院");
		headRow.createCell(2).setCellValue("推荐级别");
		headRow.createCell(3).setCellValue("所属项目");
		headRow.createCell(4).setCellValue("队长姓名");
		headRow.createCell(5).setCellValue("队长学号");
		headRow.createCell(6).setCellValue("参与学生人数");
		headRow.createCell(7).setCellValue("项目其他成员信息");
		headRow.createCell(8).setCellValue("指导老师信息");
		headRow.createCell(9).setCellValue("项目简介");
		
		map.put(academy, 1);  //把待插入行设为第一行
	}
	
	
	/**
	 * 创建Excel的数据
	 */
	private void createExcelBody(Sheet sheet, Map<String, Integer> map, Team team) {
		Integer rowNum = map.get(team.getAcademy());  //获取待插入行号
		Row row = sheet.createRow(rowNum);
		
		row.createCell(0).setCellValue(team.getOpus().getOpusName());
		row.createCell(1).setCellValue(team.getAcademy());
		row.createCell(2).setCellValue(team.getOpus().getLevel());
		row.createCell(3).setCellValue(team.getProject().getProjectName());
		row.createCell(4).setCellValue(team.getCaptainName());
		row.createCell(5).setCellValue(team.getCaptainId());
		row.createCell(6).setCellValue(team.takeTotalJoinNum());
		row.createCell(7).setCellValue(team.getTeamMemberName());
		row.createCell(8).setCellValue(team.getConductTeacherName());
		row.createCell(9).setCellValue(team.getOpus().getOpusSummary());
		
		map.put(team.getAcademy(), rowNum + 1);  //将待插入行号加1
	}

	/**
	 * 把给定的workbook输出到根目录下的temp.xls中
	 */
	private void generateExcel(HSSFWorkbook workbook) throws Exception {
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		File file = new File(realPath + "temp.xls");
		// 如果文件存在，就先删除
		if (file.exists())
			file.delete();
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
	    out.close();
	    System.out.println("Excel written successfully..");
	}

}
