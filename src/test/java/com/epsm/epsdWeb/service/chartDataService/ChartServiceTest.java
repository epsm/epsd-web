package com.epsm.epsdWeb.service.chartDataService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.epsm.epsdWeb.repository.SavedGeneratorStateDao;

@RunWith(MockitoJUnitRunner.class)
public class ChartServiceTest {

	@InjectMocks
	private ChartService service;
	
	@Mock
	private SavedGeneratorStateDao generatorDao;
	
	@Mock
	private ValueSourceOnDayValidator frequencyDataSource;
	
	@Before
	public void setUp(){
		when(frequencyDataSource.getChartData(isA(LocalDate.class))).thenReturn(new ChartData(null));
	}
	
	@Test
	public void triesToGetFreshestDataForCharts(){
		service.getDataForCharts();
		
		verify(generatorDao, atLeastOnce()).getLastEntryDate();
	}
	
	@Test
	public void triesToRefreshFrequencyChartDataIfDatabaseHasMoreFreshesData(){
		prepareDataBaseWithFreshData();
		
		service.getDataForCharts();
		
		verify(frequencyDataSource).getChartData(isA(LocalDate.class));
	}
	
	private void prepareDataBaseWithFreshData(){
		when(generatorDao.getLastEntryDate()).thenReturn(LocalDate.MAX);
	}
	
	@Test
	public void doesNottriesToRefreshFrequencyChartDataIfDatabaseDoesNotHaveMoreFreshesData(){
		prepareDataBaseWithOldData();
		
		service.getDataForCharts();
		
		verify(frequencyDataSource, never()).getChartData(isA(LocalDate.class));
	}
	
	private void prepareDataBaseWithOldData(){
		when(generatorDao.getLastEntryDate()).thenReturn(LocalDate.MIN);
	}
	
	@Test
	public void refreshChartsDataMethodRefreshesDataOnlyOnceForNewData() throws InterruptedException{
		prepareDataBaseWithFreshData();
		makeFrequencyDataSourceMakePausesBeforeReturnChartData();
		
		runMethodConcurrently();
		
		verify(frequencyDataSource).getChartData(any());
	}
	
	private void makeFrequencyDataSourceMakePausesBeforeReturnChartData(){
		when(frequencyDataSource.getChartData(isA(LocalDate.class))).thenAnswer(new Answer<ChartData>() {
			@Override
			public ChartData answer(InvocationOnMock invocation) throws InterruptedException{
				Thread.sleep(100);
				return new ChartData(null);
			}
		});
	}
	
	private void runMethodConcurrently() throws InterruptedException{
		List<Thread> threadsList = new ArrayList<Thread>();
		
		for(int i = 0; i < 1_000; i++){
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					service.getDataForCharts();
				}
			});
			
			threadsList.add(thread);
		}
		
		for(Thread thread: threadsList){
			thread.start();
		}
		
		for(Thread thread: threadsList){
			thread.join();
		}
	}
	
	@Test
	public void putsFrequencyChartDataToMap(){
		prepareDataBaseWithFreshData();
		prepareFrequencyDataSourceReturnsChartDataWithExpectedToStringResult();
		Map<String, String> chartsData= service.getDataForCharts();
		String frequencyData = chartsData.get("frequencyChartData");
		
		Assert.assertEquals("target", frequencyData);
	}
	
	private void prepareFrequencyDataSourceReturnsChartDataWithExpectedToStringResult(){
		ChartData chartData = mock(ChartData.class);
		when(chartData.toString()).thenReturn("target");
		when(frequencyDataSource.getChartData(any())).thenReturn(chartData);
	}
	
	@Test
	public void putsRetrievedDateDataToMap(){
		prepareDataBaseWithFreshData();
		prepareFrequencyDataSourceReturnsChartDataWithExpectedToStringResult();
		Map<String, String> chartsData= service.getDataForCharts();
		String dateMessage = chartsData.get("date");
		
		Assert.assertEquals(LocalDate.MAX.minusDays(1).toString(), dateMessage);
	}
	
	@Test
	public void putsDefaultDateMessageToMap(){
		Map<String, String> chartsData= service.getDataForCharts();
		String dateMessage = chartsData.get("date");
		
		Assert.assertEquals("still no information", dateMessage);
	}
}
