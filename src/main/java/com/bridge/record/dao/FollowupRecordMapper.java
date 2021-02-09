package com.bridge.record.dao;

import java.util.List;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.FollowupRecord;


public interface FollowupRecordMapper extends CommonMapper<FollowupRecord> {

    List<FollowupRecord> findByFullname(String fullname);

}
