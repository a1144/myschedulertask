package com.shuangyu.service;

import com.shuangyu.model.BbsTiandaoEmailSummary;

import java.util.List;

public interface BbsTiandaoEmailSummaryService {
    public List<BbsTiandaoEmailSummary> listBbsTiandaoEmailSummary();

    public BbsTiandaoEmailSummary findBbsTiandaoEmailSummaryById();

    public void saveBbsTianDaoEmailSummaryList(List<BbsTiandaoEmailSummary> bbsTiandaoEmailSummaryList);

    public void delete(long id);

}
