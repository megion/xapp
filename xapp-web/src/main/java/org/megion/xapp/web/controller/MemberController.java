package org.megion.xapp.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.megion.xapp.core.entity.Member;
import org.megion.xapp.core.repository.MemberRepository;
import org.megion.xapp.core.service.MemberService;
import org.megion.xapp.soapserver.core.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MemberController {
	
	private static final Logger LOG = 
	        Logger.getLogger(MemberController.class.getName());
	
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
	private HelloWorldService helloWorldService;
    
    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers(Model model) {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberRepository.findAllOrderedByName());
        //memberDao.test1();
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
            	memberService.register(newMember);
            	
            	String msg = helloWorldService.sayHi("ilya");
            	System.out.println("message " + msg);
            	 
                return "redirect:/";
            } catch (Exception e) {
            	LOG.log(Level.SEVERE, e.getMessage(), e.getCause());
                model.addAttribute("members", memberRepository.findAllOrderedByName());
                model.addAttribute("error", e.getCause().getCause());
                return "index";
            }
        } else {
            model.addAttribute("members", memberRepository.findAllOrderedByName());
            return "index";
        }
    }
}
