package com.epsm.epsdweb.repository;

import com.epsm.epsdweb.configuration.DbConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DbConfiguration.class, AbstractDaoTest.TestRepositoryConfiguration.class})
@Transactional
@Ignore
@SqlGroup({@Sql(value = {"classpath:test-clean.sql"}), @Sql})
@Rollback(false)
public class AbstractDaoTest {

	@Configuration
	@ComponentScan("com.epsm.epsdweb.repository")
	public static class TestRepositoryConfiguration {}
}
