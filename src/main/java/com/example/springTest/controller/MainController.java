package com.example.springTest.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.springTest.model.PointDTO;
import com.example.springTest.model.ProductDTO;

@Controller // 컨트롤러 bean으로 등록
public class MainController {
	// http://localhost/springTest/
	
	@RequestMapping("/") // url과 method를 매핑
	public String main(Model model) { // Model : 데이터 저장소
		model.addAttribute("message", "welcome!"); // key, value
		return "main"; // main.jsp로 포워드 
		// return "main" => return "/WEB-INF/views/main.jsp"
	}
	
	@RequestMapping("multi_table.do")
	public String gugu() {
		return "test/multi_table"; // view/test/multi_table.jsp
	} // gugu
	
	@RequestMapping("table_result.do")
	public String gugu_result(
			@RequestParam(defaultValue = "3") int num, Model model) {
		String result = "";
		for(int i = 1; i <= 9; i++) {
			result += num+"x"+i+"="+num*i+"<br>";
		}
		model.addAttribute("result", result);
		return "test/table_result";
	} // gugu_result
	
	@RequestMapping("point.do")
	public String point() {
		return "test/point";
	} // point
	
	// @RequestParam : 개별변수
	// @ModelAttribute : 객체
	@RequestMapping("point_result.do")
	public String point_result(@ModelAttribute PointDTO dto, Model model) {
		dto.setTotal(dto.getKor()+dto.getEng()+dto.getMat());
		String avg = String.format("%.2f", dto.getTotal()/3.0);
		dto.setAverage(Double.parseDouble(avg));
		model.addAttribute("dto", dto);
		return "test/point_result";
	} // point_result
	
	@RequestMapping("move.do")
	public String move() throws Exception {
		// encode(문자열, 문자셋)
		String name = URLEncoder.encode("박꿀꿀", "utf-8");
		return "redirect:/result.do?name="+name+"&age=20";
	} // move
	
	@RequestMapping("result.do")
	public String result(Model model,
			@RequestParam(defaultValue = "noname") String name,
			@RequestParam(defaultValue = "10") int age) throws Exception {
		name = URLDecoder.decode(name, "utf-8");
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "test/result";
	} // result
	
	// ModelAndView : 데이터도 저장할 수 있고 출력 페이지도 지정할 수 있음! url과 데이터를 같이 지정할 수 있음
	// Model : 데이터 저장소
	// View : 출력 페이지	
	@RequestMapping("mav.do")
	public ModelAndView mav() {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", new ProductDTO("pen", 1000));
		// new ModelAndView(view(이 페이지로 이동), key(변수명), value(값))
		return new ModelAndView("test/mav_result", "map", map);
	}
	 
	@RequestMapping("ajax.do")
	public String ajax() {
		return "test/ajax"; // ajax.jsp로 포워드
	} // ajax
	
	// @ResponseBody : 데이터 자체를 리턴 => json 형식(pom.xml에 라이브러리 추가해야함)
	// {"name":TV, "price":500000}
	@RequestMapping("background.do")
	public @ResponseBody ProductDTO background() {	
		ProductDTO dto = new ProductDTO("TV", 500000);
		return dto; // 데이터 자체를 리턴
	} // background
	
	@RequestMapping("login.do")
	public String login() {
		return "test/login";
	} // login
	
	@RequestMapping("login_result.do")
	public String login_result(@RequestParam String id, String pw, Model model) {
		String result = "";
		if(id.equals("pig")&&pw.equals("1234")) {
			result = "환영합니다";
		}else {
			result = "아이디 또는 비밀번호가 틀렸습니다.";
		}
		model.addAttribute("result", result);
		return "test/login_result";
	} //  login_result
	
	
} // class
