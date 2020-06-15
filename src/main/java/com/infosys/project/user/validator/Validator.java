package com.infosys.project.user.validator;

import com.infosys.project.user.dto.BuyerDTO;
import com.infosys.project.user.dto.SellerDTO;

public class Validator {
	public static void buyerValidate(BuyerDTO buyerDto) throws Exception {
		System.out.println("in validate");
		if(!(name(buyerDto.getName()))) {
			System.out.println("in name");
			throw new Exception("Name is not valid");
		}
		else if(!(emailid(buyerDto.getEmail()))) {
			System.out.println("in email");
			throw new Exception("EmailID is not valid");
		}
		else if(!(contactno(buyerDto.getPhoneNumber()))) {
			System.out.println("in phno");
			throw new Exception("Phone Number is not valid");
		}
		else if(!(password(buyerDto.getPassword()))) {
			System.out.println("in paasword");
			throw new Exception("Phone Number is not valid");
			}
		System.out.println("validate end");
		}
	
		public static boolean name(String name) {
			if(name.matches("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")) {
				return true;
			}
			else {
				return false;
			}}
			
			
		
		public static boolean emailid(String mail) {
			if(mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
					"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				return true;
			}else {
				return false;
			}}
			
		
		public static boolean contactno(String number) {
			//String a=Long.toString(number);
			if(number.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$")){
				return true;
			}else {
				return false;
			}
		}
		
		public static boolean password(String password) {
			if(password.matches("^(?=.*[0-9])"
	                + "(?=.*[a-z])(?=.*[A-Z])"
	                + "(?=.*[@#$%^&+=])"
	                + "(?=\\S+$).{7,20}$")) {
				return true;
			}else {
				return false;
			}
			
		
	}}
