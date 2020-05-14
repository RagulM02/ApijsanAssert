package com.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.*;

import com.pojo.Ad;
import com.pojo.Data;
import com.pojo.Root;

public class TestMain {
	@Test
	public void Array() throws FileNotFoundException, IOException, ParseException {
		JSONParser jp = new JSONParser();
		Object parse = jp
				.parse(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\JSON\\url.json"));
		JSONObject jo = (JSONObject) parse;
		Object objectData = jo.get("data");
		Object objectAd = jo.get("ad");

		// Data set
		List<Data> d = new ArrayList<Data>();
		JSONArray a = (JSONArray) objectData;
		for (Object eachData : a) {

			Data da = new Data();
			JSONObject j = (JSONObject) eachData;
			da.setId(Integer.parseInt(String.valueOf(j.get("id"))));
			da.setEmail(j.get("email").toString());
			da.setFirst_name(j.get("first_name").toString());
			da.setLast_name(j.get("last_name").toString());
			da.setAvatar(j.get("avatar").toString());
			d.add(da);
		}
		JSONObject ja = (JSONObject) objectAd;
		// Ad set
		Ad a1 = new Ad();
		a1.setUrl(ja.get("url").toString());
		a1.setCompany(ja.get("company").toString());
		a1.setText(ja.get("text").toString());
		// Root set
		Root r = new Root();
		r.setPage(Integer.parseInt(String.valueOf(jo.get("page"))));
		r.setPer_page(Integer.parseInt(String.valueOf(jo.get("per_page"))));
		r.setTotal(Integer.parseInt(String.valueOf(jo.get("total"))));
		r.setTotal_pages(Integer.parseInt(String.valueOf(jo.get("total_pages"))));
		r.setAd(a1);
		r.setData(d);

		// for assertion
		String company = r.getAd().getCompany();
		String text = r.getAd().getText();
		String url = r.getAd().getUrl();
		int page = r.getPage();
		int perpage = r.getPer_page();
		int total = r.getTotal();
		int totalpages = r.getTotal_pages();
		// Get of root
		System.out.println("page: " + r.getPage());
		System.out.println("Per page: " + r.getPer_page());
		System.out.println("Total: " + r.getTotal());
		System.out.println("Total page: " + r.getTotal_pages());
		System.out.println("**Data*********");
		// for assertion
		List<Integer> id = new ArrayList<Integer>();
		List<String> email = new ArrayList<String>();
		List<String> fname = new ArrayList<String>();
		List<String> lname = new ArrayList<String>();
		List<String> avatar = new ArrayList<String>();
		// Get of Data
		List<Data> data = r.getData();
		for (Data root : data) {
			id.add(root.getId());
			email.add(root.getEmail());
			fname.add(root.getFirst_name());
			lname.add(root.getLast_name());
			avatar.add(root.getAvatar());
			System.out.println(root.getId());
			System.out.println(root.getFirst_name());
			System.out.println(root.getLast_name());
			System.out.println(root.getEmail());
			System.out.println(root.getAvatar());
		}
		System.out.println("**Ad*********");
		// Get of Ad
		System.out.println(r.getAd().getUrl());
		System.out.println(r.getAd().getCompany());
		System.out.println(r.getAd().getText());

		// Assertion part
		List<Integer> lid = new ArrayList<Integer>();
		lid.add(7);
		lid.add(8);
		lid.add(9);
		lid.add(10);
		lid.add(11);
		lid.add(12);

		Assert.assertTrue("Verify the id", id.equals(lid));
		Assert.assertTrue("Verify the email", email.contains("lindsay.ferguson@reqres.in"));
		Assert.assertTrue("Verify the first_name", fname.contains("Michael"));
		Assert.assertTrue("Verify the last_name", lname.contains("Howell"));
		Assert.assertTrue("Verify the avatar",
				avatar.contains("https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg"));
		Assert.assertTrue("Verify the Companyname", company.contains("StatusCode Weekly"));
		Assert.assertTrue("Verify the url", url.contains("http://statuscode.org/"));
		Assert.assertTrue("Verify the text", text.contains(
				"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things."));
		Assert.assertEquals("verift the page", 2, page);
		Assert.assertEquals("verift the per_page", 6, perpage);
		Assert.assertEquals("verift the Total", 12, total);
		Assert.assertEquals("verift the Total_page", 2, totalpages);
	}

}
