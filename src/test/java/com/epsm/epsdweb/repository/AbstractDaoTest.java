package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.configuration.TestDbConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDbConfiguration.class})
@Transactional
@Ignore
@SqlGroup({@Sql(value = {"classpath:test-clean.sql"}), @Sql})
@Rollback(false)
public class AbstractDaoTest{

}
