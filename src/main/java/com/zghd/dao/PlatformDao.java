package com.zghd.dao;

import com.zghd.entity.JuKanDian.request.ArticleReport;
import com.zghd.entity.JuKanDian.request.Upstream;
import com.zghd.entity.ZGHDReport.EventLog;
import com.zghd.entity.platform.GetUpstream;
import com.zghd.entity.platform.ReportDownstream;
import com.zghd.entity.platform.ReportUpstream;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository("platformDao")
public interface PlatformDao {

    /**
     * 统计下游请求数-新增
     */
    void insertDownStreamReport(ReportDownstream downstream);

    /**
     * 统计下游请求数-修改
     */
    void updateDownStreamReport(ReportDownstream downstream);

    /**
     * 调度查询上游概率
     */
    List<GetUpstream> getUpstream(String slotId);

    /**
     * 广告位上游统计信息-添加
     * type 1请求 2返回 3曝光 4点击
     */
    void insertUpstreamReport(ReportUpstream upstream);

    /**
     * 广告位上游统计信息-修改
     * type 1请求 2返回 3曝光 4点击
     */
    void updateUpstreamReport(ReportUpstream upstream);

    /**
     * 查询新闻类上游id
     */
    GetUpstream getNewsUpstream(String slotId);

    /**
     * 获取测试素材
     */
    GetUpstream getAdTest(Map<String, Object> params);

    /**
     * 文章-查询上游信息
     */
    Upstream getArticleUpstream(Upstream u);

    /**
     * 文章-查询上游信息
     */
    Upstream getArticleApp(Upstream u);

    /**
     * 文章-统计
     */
    void insertArticleReport(ArticleReport ar);
    void updateArticleReport(ArticleReport ar);

    /**
     * 查询此id是否进行上报日记统计
     */
    int eventStatus(String slotId);
    //保存日志
    void insertEventLog(EventLog el);
}
