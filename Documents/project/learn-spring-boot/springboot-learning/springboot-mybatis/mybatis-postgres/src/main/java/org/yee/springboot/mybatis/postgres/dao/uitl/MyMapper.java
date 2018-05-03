package org.yee.springboot.mybatis.postgres.dao.uitl;

/**
 * package: org.yee.springboot.mybatis.postgres.dao.uitl
 * class: MyMapper
 * describe:
 *
 * @author: brucewong
 * time: 06/02/2018 16:56
 **/

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author huangwh
 * @date 2017/4/11 13:57.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
