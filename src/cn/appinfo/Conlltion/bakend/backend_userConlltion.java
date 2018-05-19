package cn.appinfo.Conlltion.bakend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appinfo.Servlet.backend_userServlet;
import cn.appinfo.entity.BackendUser;

@Controller
public class backend_userConlltion {

	@Resource
	backend_userServlet backServlet;
	
	@RequestMapping("/login")
	private String BackUserLogin(Model model,@RequestParam(value="errer",required=false)String errer) { //��̨����ҳ��
		if(errer!=null) {
			model.addAttribute("error","�û������������");
		}
		return "backendlogin";
	}
	
	@RequestMapping("/backlogin")
	private String BackUserDenLu(HttpServletRequest request,@RequestParam(value="userCode",required=true)String userCode,@RequestParam(value="userPassword",required=true)String userPassword) {//��½��֤
		BackendUser backUser = null;
		backUser = backServlet.BackuserShow(userCode, userPassword);
		if(backUser!=null) {
			request.setAttribute("userSession", backUser);
			return "backend/main";
		}else {
			return "redirect:/login?errer="+1;
		}
		
	}
	
	@RequestMapping("manager/logout")
	private String backOut() {
		return "redirect:/login";
	}
	
	/*@RequestMapping("/manager/backend/app/list");*/
}
