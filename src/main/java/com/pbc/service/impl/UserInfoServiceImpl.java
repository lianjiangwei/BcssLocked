package com.pbc.service.impl;

import com.pbc.mapper.UserInfoMapper;
import com.pbc.po.UserInfo;
import com.pbc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Alex on 2016/10/5.
 */
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo get(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> getAll() {
        return userInfoMapper.selectByExample(null);
    }

    @Override
    public int add(UserInfo u) {
        return userInfoMapper.insert(u);
    }

    @Override
    public int upd(UserInfo u) {
        return userInfoMapper.updateByPrimaryKey(u);
    }

    @Override
    public int del(int id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }
}