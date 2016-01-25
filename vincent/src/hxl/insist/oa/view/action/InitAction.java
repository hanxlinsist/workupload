package hxl.insist.oa.view.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Academy;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.domain.notpersistent.QueryResult;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class InitAction extends BaseAction<Team> {

	/**
	 * 初始化数据库数据
	 */
	public String init() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		QueryResult<Account> scrollData = accountService.getScrollData(Account.class, 0, 1, null, null, null);
		
		if (scrollData.getTotalrecord() > 0) {
			request.setAttribute("errMsg", "不能重复初始化数据");
			return "errMsg";
		}
		
		String md5Digest = DigestUtils.md5Hex("1234");
		
		//root账号
		Account account = new Account();
		account.setUserName("root");
		account.setPassword(md5Digest);
		account.setRole(3);
		accountService.save(account);
		
		//保存学院信息
		Academy academyone = new Academy();
		academyone.setName("化学化工与环境学部");
		Academy academytwo = new Academy();
		academytwo.setName("信息与控制工程学院");
		Academy academythree = new Academy();
		academythree.setName("计算机与通讯工程学院");
		Academy academyfour = new Academy();
		academyfour.setName("机械工程学院");
		Academy academyfive = new Academy();
		academyfive.setName("石油天然气工程学院");
		Academy academysix = new Academy();
		academysix.setName("经济管理学院");
		Academy academyseven = new Academy();
		academyseven.setName("理学院");
		Academy academyeight = new Academy();
		academyeight.setName("马克思主义学院");
		Academy academynine = new Academy();
		academynine.setName("外国语学院");
		Academy academyten = new Academy();
		academyten.setName("体育学院");
		Academy academyeleven = new Academy();
		academyeleven.setName("矿业工程学院");
		Academy academytwelve = new Academy();
		academytwelve.setName("教育实验学院");
		Academy academythirteen = new Academy();
		academythirteen.setName("民族教育学院");
		
		academyService.save(academyone);
		academyService.save(academytwo);
		academyService.save(academythree);
		academyService.save(academyfour);
		academyService.save(academyfive);
		academyService.save(academysix);
		academyService.save(academyseven);
		academyService.save(academyeight);
		academyService.save(academynine);
		academyService.save(academyten);
		academyService.save(academyeleven);
		academyService.save(academytwelve);
		academyService.save(academythirteen);
		
		request.setAttribute("errMsg", "数据初始化成功");
		return "errMsg";
	}
}
