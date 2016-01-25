package hxl.insist.oa.view.action;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.RecommendedLevel;
import hxl.insist.oa.domain.WorkType;
import hxl.insist.oa.util.Utils;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RootAction extends BaseAction<Project> {
	// 上传图片的3个属性
	private File indexImage;
	private String contentType;
	private String filename;

	/**
	 * 包含作品类型
	 */
	private String worktypes;
	/**
	 * 作品推荐级别
	 */
	private String relevels;

	/**
	 * 创建项目界面
	 */
	public String createUI() {
		return "projectCreateUI";
	}

	/**
	 * 创建项目
	 */
	public String create() {
		try {
			//接收上传的图片
			String filePath = ServletActionContext.getServletContext().getRealPath("/");
			Date date = new Date();
			File fileToCreate = new File(filePath + "images/" + date, this.filename);
			FileUtils.copyFile(this.indexImage, fileToCreate);
			
			//把新项目保存到数据库中
			Project project = new Project();
			project.setProjectName(model.getProjectName());
			project.setIronAddress("images/" + date + "/" + this.filename);
			projectService.save(project);
			
			//把作品类型和推荐级别保存到数据库
			Set<String> wts = splitData(worktypes);
			Set<String> rels = splitData(relevels);
			for (String name : wts) {
				WorkType wt = new WorkType();
				wt.setName(name);
				wt.setProject(project);
				workTypeService.save(wt);
			}
			for (String name : rels) {
				RecommendedLevel rl = new RecommendedLevel();
				rl.setName(name);
				rl.setProject(project);
				recommendedLevelService.save(rl);
			}
			
			//为当前项目分配一个管理员账户
			String md5Digest = DigestUtils.md5Hex("1234");
			Account account = new Account();
			account.setUserName("admin_" + Utils.consistNameByDate());
			account.setPassword(md5Digest);
			account.setRole(0);
			account.setStatus(1);
			account.setProject(project);
			
			accountService.save(account);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "toFunctionList";
	}

	private Set<String> splitData(String data) {
		String[] split = data.split("、");
		Set<String> set = new HashSet<String>(Arrays.asList(split));
		return set;
	}

	public String getWorktypes() {
		return worktypes;
	}

	public void setWorktypes(String worktypes) {
		this.worktypes = worktypes;
	}

	public String getRelevels() {
		return relevels;
	}

	public void setRelevels(String relevels) {
		this.relevels = relevels;
	}

	public void setIndexImage(File indexImage) {
		this.indexImage = indexImage;
	}

	public void setIndexImageContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setIndexImageFileName(String filename) {
		this.filename = filename;
	}
}