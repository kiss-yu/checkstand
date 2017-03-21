package com.checkstand.ZFBUtil.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.transaction.Transactional;

/**
 * Created by 11723 on 2017/3/19.
 */
@Entity
@Transactional
public interface Order {
    String toJson();
}
