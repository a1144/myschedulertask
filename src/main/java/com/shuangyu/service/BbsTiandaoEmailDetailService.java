package com.shuangyu.service;

import com.shuangyu.model.BbsTiandaoEmailDetail;

import java.util.List;

public interface BbsTiandaoEmailDetailService {
    List<BbsTiandaoEmailDetail> listBbsTiandaoEmailDetail();

    BbsTiandaoEmailDetail findBbsTiandaoEmailDetailId();

    void save(BbsTiandaoEmailDetail bbsTiandaoEmailDetail);

    void delete(long id);

}
