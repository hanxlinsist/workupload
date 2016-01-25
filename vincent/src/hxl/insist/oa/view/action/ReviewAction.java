package hxl.insist.oa.view.action;

import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Review;
import hxl.insist.oa.domain.Reviewer;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ReviewAction extends BaseAction<Review> {
	
	private Long opusid;
	
	private String appraisedAdvice;
	
	/**
	 * 保存作品的打分
	 */
	public String saveOpusScore() {
		//更新当前作品的状态
		Opus opus = opusService.getById(opusid);
		double totalScore = opus.getTotalScore() + model.getScore();
		int appraisedNumber = opus.getAppraisedNumber() + 1;
		opus.setTotalScore(totalScore);
		opus.setAppraisedNumber(appraisedNumber);
		opus.setAverageScore(totalScore / appraisedNumber);
		if (opus.getAppraisedAdvice() == null)
			opus.setAppraisedAdvice(appraisedAdvice + "@@@");
		else
			opus.setAppraisedAdvice(opus.getAppraisedAdvice() + appraisedAdvice + "@@@");
		opusService.update(opus);
		
		Reviewer reviewer = reviewerService.getByAccount(getSessionAccount());
		
		//保存评分表到数据库中
		Review review = new Review();
		review.setProject(getSessionAccount().getProject());
		review.setOpus(opus);
		review.setReviewer(reviewer);
		review.setScore(model.getScore());
		review.setIsRecommend(model.getIsRecommend());
		reviewService.save(review);
		
		//更新当前评审的状态
		Set<Opus> works = reviewer.getWorks();
		works.add(opus);
		reviewer.setWorks(works);
		
		reviewerService.update(reviewer);
		
		ServletActionContext.getRequest().setAttribute("siteUrl", "reviewer/opus_listUI");
		return "siteUrl";
	}

	public Long getOpusid() {
		return opusid;
	}

	public void setOpusid(Long opusid) {
		this.opusid = opusid;
	}

	public String getAppraisedAdvice() {
		return appraisedAdvice;
	}

	public void setAppraisedAdvice(String appraisedAdvice) {
		this.appraisedAdvice = appraisedAdvice;
	}
}
