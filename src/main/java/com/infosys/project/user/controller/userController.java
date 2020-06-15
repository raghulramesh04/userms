package com.infosys.project.user.controller;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.project.user.dto.BuyerDTO;
import com.infosys.project.user.dto.CartDTO;
import com.infosys.project.user.dto.LoginDTO;

import com.infosys.project.user.dto.ProductDTO;
import com.infosys.project.user.dto.SellerDTO;
import com.infosys.project.user.dto.WishlistDTO;
import com.infosys.project.user.service.BuyerService;
import com.infosys.project.user.service.SellerService;



@RestController
@CrossOrigin

public class userController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	SellerService sellerService;
	@Autowired
	RestTemplate template;
	
//	@PostMapping(value = "/api/buyer/register",consumes = MediaType.APPLICATION_JSON_VALUE)
//	public String buyer(@RequestBody BuyerDTO buyerDto) throws Exception {
//		try {
//			System.out.println("inside method");
//		buyerService.register(buyerDto);
//			return "Registered successfully";
//		}catch (Exception e) {
//			
//			return "Registration not successful";
//		}
//		
//		
//	}
	
	@GetMapping(value="/view/products/{productname}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> viewProducts(@PathVariable String productname) {
			logger.info("Add products to wishlist request {}", productname);
			System.out.println("before template");
			List<ProductDTO> prodDTO=template.getForObject("http://PRODUCTMS"+"/products/name/"+productname,List.class);
			System.out.println(prodDTO);			
			return prodDTO;
		
		
		}
	@GetMapping(value="/view/category/{category}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> viewProductsonCategory(@PathVariable String category) {
			logger.info("Add products to wishlist request {}", category);
			System.out.println("before template");
			List<ProductDTO> prodDTO=template.getForObject("http://PRODUCTMS"+"/products/category/"+category,List.class);
			System.out.println(prodDTO);			
			return prodDTO;
		
		
		}

	@PostMapping(value="/add/wishlist/{prodid}/{productname}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addtowishlist(@PathVariable Integer prodid,@PathVariable String productname) {
		try {
			logger.info("Add products to wishlist request {}", productname);
			ProductDTO prodDTO=template.getForObject("http://PRODUCTMS"+"/products/prodid/"+prodid,ProductDTO.class);
			buyerService.addtowishlist(prodDTO);
			return "Added successfully";
		
		}catch(Exception e) {
			return "Unable to add";
		}
		}
	@DeleteMapping(value="/delete/{buyerId}")
	public String delete(@PathVariable Integer buyerId) {
		try {
			buyerService.delete(buyerId);
			return "deleted successfully";
		}catch(Exception e) {
			return "Unable to delete";
		}
		
	}
	@PostMapping(value="buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody BuyerDTO buyerDTO) {
		try {
		logger.info("Registration request for user {}", buyerDTO.getEmail(), buyerDTO.getPassword());
		buyerService.registerUser(buyerDTO);
		return "Successful";
	}catch(Exception e) {
		return("Not sucessful");
	}}
	
	@PostMapping(value="seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerSeller(@RequestBody SellerDTO sellerDTO) {
		try {
		logger.info("Registration request for seller {}", sellerDTO);
		sellerService.registerSeller(sellerDTO);
		return "Successful";
	}catch(Exception e) {
		return("Not sucessful");
	}}


	@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String Buyerlogin(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
		if(buyerService.Buyerlogin(loginDTO)) {
			return "Logged in successfully!";
		}
		return "Incorrect emailid or password!";
	}
	

	@PostMapping(value = "/seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String Sellerlogin(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
		if(sellerService.Sellerlogin(loginDTO)) {
			return "Logged in successfully!";
		}
		return "Incorrect emailid or password!";
	}
	


	@DeleteMapping(value="/removebuyer/{buyerid}", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String removebuyer(@PathVariable Integer buyerid) {
		logger.info("Request for order removal by buyer {}", buyerid);
		buyerService.removebuyer(buyerid);
		return "Deleted successfully";
		
	}
	@DeleteMapping(value="/removeseller/{sellerid}", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String removeseller(@PathVariable Integer sellerid) {
		logger.info("Request for order removal by seller {}", sellerid);
		sellerService.removeseller(sellerid);
		return "Deleted successfully";
		
		
	} 
	@PostMapping(value="/add/products/{prodid}/{quantity}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String order(@PathVariable Integer prodid,@PathVariable Integer quantity) {
		try {
			logger.info("Add products to Order request {}", prodid);
			
			String orderDTO=template.getForObject("http://ORDERMS"+"/add/products/"+prodid+"/"+quantity,String.class);
			return "Added successfully";
		
		}catch(Exception e) {
			System.out.println("in exception");
			return "Unable to add";
		}
		
	}
	@PostMapping(value="/add/cart/{prodid}/{productname}/{quantity}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addtocart(@PathVariable Integer prodid,@PathVariable String productname, @PathVariable Integer quantity) {
		try {
			logger.info("Add products to order request {}", productname);
			ProductDTO prodDTO=template.getForObject("http://PRODUCTMS"+"/products/prodid/"+prodid,ProductDTO.class);
			CartDTO cart=new CartDTO();
			cart.setProdid(prodDTO.getProdid());
			cart.setQuantity(quantity);
			buyerService.addtocart(cart);
			return "Added successfully";
		
		}catch(Exception e) {
			return "Unable to add";
		}
		}
	
	
}




	
	
	


