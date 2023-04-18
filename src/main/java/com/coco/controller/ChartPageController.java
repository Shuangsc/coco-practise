package com.coco.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import com.coco.view.HistoryView;
import com.coco.CocoPractiseApplication;
import com.coco.common.UserHolder;
import com.coco.config.Config;
import com.coco.view.ExpenditurePageView;
import com.coco.view.HistoryView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.coco.pojo.OrderPojo;
import com.coco.service.OrderService;
import com.coco.util.SpringContextUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ResourceBundle;
import javafx.scene.control.Hyperlink;

@FXMLController
public class ChartPageController implements Initializable {


	@FXML
	private TextField pageIndexText;
	@FXML
	private TextField pageIndexText1;
	@FXML
	private TextField pageIndexText11;
	@FXML
	private Label pageCount1;
	@FXML
	private Label pageCount2;
	@FXML
	private Label pageCount3;
	@FXML
	private PieChart pieChart;
	@FXML
	private PieChart pieChart1;
	@FXML
	private PieChart pieChart11;
	@FXML
	private BarChart barChart;
	@FXML
	private BarChart barChart1;
	@FXML
	private BarChart barChart11;

	@FXML
	private Hyperlink backToHistory;
	@FXML
	private Hyperlink backToHistory2;
	@FXML
	private Hyperlink backToHistory3;


	@FXML
	void onJumpHistory(ActionEvent event) {
		Stage stage = CocoPractiseApplication.getStage();
		stage.close();
		CocoPractiseApplication.showView(HistoryView.class);

	}
	@FXML
	public void pageIndex() {
		initChartComboValue();
	}

	@FXML
	public void pageIndex1() {
		initChartComboValue();
	}

	@FXML
	public void pageIndex11() {
		initChartComboValue();
	}

	Double totalmonth=0.00;
	Double totalweek=0.00;
	Double totalyear=0.00;

	public static String getWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, 0);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	// get the last date of this month
	public static String getWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	// get the first date of this month
	public static String getMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * get the last date of the month
	 *
	 * @return String
	 **/
	public static String getMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	int pageCount = 100;
	java.text.DecimalFormat df=new java.text.DecimalFormat("##.##%");

	//refresh
	public void initChartComboValue() {

		String monthStart = getMonthStart();
		//System.out.println("start: "+monthStart);
		String monthEnd = getMonthEnd();
		//System.out.println("end: "+monthEnd);
		UserHolder userHolder = UserHolder.getInstance();
		int userId =userHolder.getUserId();
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        List<OrderPojo> listRaw = orderService.findByUserId(userId);

        List<OrderPojo> listSort = listRaw.stream().sorted(Comparator.comparing(OrderPojo::getDate))
                .collect(Collectors.toList());
		List<OrderPojo> list = new ArrayList<>();
		for(OrderPojo item:listSort){
			if(item.getDate().compareTo(monthStart)>=0&&item.getDate().compareTo(monthEnd)<=0){
				list.add(item);
			}
		}
		System.out.println("month sort list is: "+list);


		if(list.size()%pageCount==0){
			pageCount1.setText(String.valueOf(list.size()/pageCount));
		}else{
			System.out.println(list.size()/pageCount);
			pageCount1.setText(String.valueOf(list.size()/pageCount+1));
		}
		XYChart.Series series = new XYChart.Series();
		series.setName("Month Rank");

		if (list.size() != 0) {
			barChart.getData().clear();
			int start=(Integer.valueOf(pageIndexText.getText())-1)*pageCount;
			int end=Integer.valueOf(pageIndexText.getText())*pageCount;
			if(end>=list.size()){
				end=list.size();
			}
			if(start>=list.size()){
				start=list.size();
			}
			list=list.subList(start, end);
			for (int i = 0; i < list.size(); i++) {
				Double value=format1(Double.valueOf(list.get(i).getAmount()));
				XYChart.Data xd = new XYChart.Data(list.get(i).getType()+"("+value+")",value);
			//	xd.setNode(new Label(df.format(value/totalmonth)));
				series.getData().add(xd);
			}
			barChart.getData().add(series);
		}

		// week
		String weekStart = getWeekStart();

		String weekEnd = getWeekEnd();
		List<OrderPojo> listWeek = new ArrayList<>();
		for(OrderPojo item:listSort){
			if(item.getDate().compareTo(weekStart)>=0&&item.getDate().compareTo(weekEnd)<=0){
				listWeek.add(item);
			}
		}
		System.out.println("month sort list is: "+listWeek);
//		String sql1 = "select * from order_info where date>=str_to_date(\"" + monthStart1 + "\",\"%Y-%m-%d %T\")"
//				+ " and date<=" + "str_to_date(\"" + monthEnd1 + "\",\"%Y-%m-%d %T\") order by date desc ";
//		List<Order_info> list1 = (List<Order_info>) DBUnitHelper.executeQuery(sql1, Order_info.class);

		if(list.size()%pageCount==0){
			pageCount2.setText(String.valueOf(list.size()/pageCount));
		}else{
			pageCount2.setText(String.valueOf(list.size()/pageCount+1));
		}
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Week Rank");
		if (listWeek.size() != 0) {
			barChart1.getData().clear();
			int start=(Integer.valueOf(pageIndexText1.getText())-1)*pageCount;
			int end=Integer.valueOf(pageIndexText1.getText())*pageCount;
			if(end>=listWeek.size()){
				end=listWeek.size();
			}
			if(start>=listWeek.size()){
				start=listWeek.size();
			}
			listWeek=listWeek.subList(start, end);
			for (int i = 0; i < listWeek.size(); i++) {
				Double value=format1(Double.valueOf(listWeek.get(i).getAmount()));
				XYChart.Data xd = new XYChart.Data(listWeek.get(i).getType()+"("+value+")",value);
				//xd.setNode(new Label(df.format(value/totalweek)));
				series1.getData().add(xd);
			}
			barChart1.getData().add(series1);
		}


		// year

		// Preparing ObservbleList object
		String yearStart = getYearStart();

		String yearEnd = getYearEnd();
//		String sql11 = "select * from order_info where date>=str_to_date(\"" + monthStart11 + "\",\"%Y-%m-%d %T\")"
//				+ " and date<=" + "str_to_date(\"" + monthEnd11 + "\",\"%Y-%m-%d %T\") order by date desc";
//		List<Order_info> list11 = (List<Order_info>) DBUnitHelper.executeQuery(sql11, Order_info.class);
		List<OrderPojo> listYear = new ArrayList<>();
		for(OrderPojo item:listSort){
			if(item.getDate().compareTo(yearStart)>=0&&item.getDate().compareTo(yearEnd)<=0){
				listYear.add(item);
			}
		}
		System.out.println("month sort list is: "+listYear);

		if(listYear.size()%pageCount==0){
			pageCount3.setText(String.valueOf(listYear.size()/pageCount));
		}else{
			pageCount3.setText(String.valueOf(listYear.size()/pageCount+1));
		}
		XYChart.Series series11 = new XYChart.Series();
		series11.setName("Year Rank");

		if (listYear.size() != 0) {
			barChart11.getData().clear();
			int start=(Integer.valueOf(pageIndexText11.getText())-1)*pageCount;
			int end=Integer.valueOf(pageIndexText11.getText())*pageCount;
			if(end>=listYear.size()){
				end=listYear.size();
			}
			if(start>=listYear.size()){
				start=listYear.size();
			}
			listYear=listYear.subList(start, end);
			for (int i = 0; i < listYear.size(); i++) {
				Double value=format1(Double.valueOf(listYear.get(i).getAmount()));
				XYChart.Data xd = new XYChart.Data(listYear.get(i).getType()+"("+value+")",value);
				//xd.setNode(new Label(df.format(value/totalyear)));
				series11.getData().add(xd);
			}
			barChart11.getData().add(series11);
		}



	}

	public static Double format1(double value) {

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}


	/**
	 * get the first date of the year
	 *
	 * @return String
	 **/
	public static String getYearStart() {
		return new SimpleDateFormat("yyyy").format(new Date()) + "-01-01 00:00:00";
	}

	/**
	 * get the last date of the year
	 *
	 * @return String
	 **/
	public static String getYearEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date currYearLast = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast) + " 23:59:59";
	}

	//initialize
	public void initialize(URL location, ResourceBundle resources) {
		pageIndexText.setText("1");
		pageIndexText1.setText("1");
		pageIndexText11.setText("1");
		Map<String, Double> map = new HashMap<String, Double>();

		// Preparing ObservbleList object
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		String monthStartPie = getMonthStart();
		String monthEndPie = getMonthEnd();
//		String sql = "select * from order_info where date>=str_to_date(\"" + monthStart + "\",\"%Y-%m-%d %T\")"
//				+ " and date<=" + "str_to_date(\"" + monthEnd + "\",\"%Y-%m-%d %T\") order by date desc";
//		List<Order_info> list = (List<Order_info>) DBUnitHelper.executeQuery(sql, Order_info.class);
		UserHolder userHolder = UserHolder.getInstance();
		int userId =userHolder.getUserId();
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        List<OrderPojo> listRaw = orderService.findByUserId(userId);
		List<OrderPojo> listSort = listRaw.stream().sorted(Comparator.comparing(OrderPojo::getDate))
				.collect(Collectors.toList());
		List<OrderPojo> listPie = new ArrayList<>();
		for(OrderPojo item:listSort){
			if(item.getDate().compareTo(monthStartPie)>=0&&item.getDate().compareTo(monthEndPie)<=0){
				listPie.add(item);
			}
		}

		for (int i = 0; i < listPie.size(); i++) {
			totalmonth+=Double.valueOf(listPie.get(i).getAmount());
			if (map.get(listPie.get(i).getType()) != null) {
				map.put(listPie.get(i).getType(),
						map.get(listPie.get(i).getType()) + Double.valueOf(listPie.get(i).getAmount()));
			} else {
				map.put(listPie.get(i).getType(), Double.valueOf(listPie.get(i).getAmount()));
			}
		}
		for (String str : map.keySet()) {
			PieChart.Data pd = new PieChart.Data(str, map.get(str));
			pd.setName(str+"  "+df.format(pd.getPieValue()/totalmonth));
			pieChartData.add(pd);
		}


		pieChart.setData(pieChartData);

		// week
		Map<String, Double> map1 = new HashMap<String, Double>();

		// Preparing ObservbleList object
		ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList();
		String weekStartPie = getWeekStart();

		String weekEndPie = getWeekEnd();
//		String sql1 = "select * from order_info where date>=str_to_date(\"" + monthStart1 + "\",\"%Y-%m-%d %T\")"
//				+ " and date<=" + "str_to_date(\"" + monthEnd1 + "\",\"%Y-%m-%d %T\") order by date desc ";
//		List<Order_info> list1 = (List<Order_info>) DBUnitHelper.executeQuery(sql1, Order_info.class);
		List<OrderPojo> listWeekPie = new ArrayList<>();
		for(OrderPojo item:listSort){
			if(item.getDate().compareTo(weekStartPie)>=0&&item.getDate().compareTo(weekEndPie)<=0){
				listWeekPie.add(item);
			}
		}
		for (int i = 0; i < listWeekPie.size(); i++) {
			totalweek+=Double.valueOf(listWeekPie.get(i).getAmount());
			if (map1.get(listWeekPie.get(i).getType()) != null) {
				map1.put(listWeekPie.get(i).getType(),
						map1.get(listWeekPie.get(i).getType()) + Double.valueOf(listWeekPie.get(i).getAmount()));
			} else {
				map1.put(listWeekPie.get(i).getType(), Double.valueOf(listWeekPie.get(i).getAmount()));
			}
		}
		for (String str : map1.keySet()) {
			PieChart.Data pd = new PieChart.Data(str, map1.get(str));
			pd.setName(str+"  "+df.format(pd.getPieValue()/totalweek));
			pieChartData1.add(pd);
		}

		pieChart1.setData(pieChartData1);

		// year
		Map<String, Double> map11 = new HashMap<String, Double>();

		// Preparing ObservbleList object
		ObservableList<PieChart.Data> pieChartData11 = FXCollections.observableArrayList();
		String yearStartPie = getYearStart();

		String yearEndPie = getYearEnd();
		List<OrderPojo> listYearPie = new ArrayList<>();
		for(OrderPojo item:listSort){
			if(item.getDate().compareTo(yearStartPie)>=0&&item.getDate().compareTo(yearEndPie)<=0){
				listYearPie.add(item);
			}
		}
//
//		String sql11 = "select * from order_info where date>=str_to_date(\"" + monthStart11 + "\",\"%Y-%m-%d %T\")"
//				+ " and date<=" + "str_to_date(\"" + monthEnd11 + "\",\"%Y-%m-%d %T\") order by date desc";
//		List<Order_info> list11 = (List<Order_info>) DBUnitHelper.executeQuery(sql11, Order_info.class);

		for (int i = 0; i < listYearPie.size(); i++) {
			totalyear+=Double.valueOf(listYearPie.get(i).getAmount());

			if (map11.get(listYearPie.get(i).getType()) != null) {
				map11.put(listYearPie.get(i).getType(),
						map11.get(listYearPie.get(i).getType()) + Double.valueOf(listYearPie.get(i).getAmount()));
			} else {
				map11.put(listYearPie.get(i).getType(), Double.valueOf(listYearPie.get(i).getAmount()));
			}
		}

		for (String str : map11.keySet()) {
			PieChart.Data pd = new PieChart.Data(str, map11.get(str));
			pd.setName(str+"  "+df.format(pd.getPieValue()/totalyear));
			pieChartData11.add(pd);
		}


		pieChart11.setData(pieChartData11);
		initChartComboValue();
	}
}
