package com.grupo1.gestoreventos.paypal_configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.OrderDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.EventoService;
import com.grupo1.gestoreventos.service.UsuarioService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PaypalController {

	@Autowired
	PaypalService paypalService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	EventoService eventoService;
	
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";
	
	@GetMapping("/user/usuarios/{idUsuario}/eventos/{idEvento}/pay")
	public String payment(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idEvento") Long idEvento, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId, HttpServletResponse response) {
		//Comprobamos que el usuario pueda realizar esta acción
		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO = usuarioService.findById(usuarioDTO);
		
		if(usuarioDTO.getCookie().equals(String.valueOf(sessionId))) {
			//Recogemos el id del evento y lo buscamos
			EventoDTO eventoDTO = new EventoDTO(idEvento);
			eventoDTO = eventoService.findById(eventoDTO);
			//A partir de este evento creamos una orden
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setCurrency("EUR");
			orderDTO.setDescription("Payment of the event: "+eventoDTO.getNombre());
			orderDTO.setIntent("Sale");
			orderDTO.setMethod("paypal");
			orderDTO.setPrice(Double.valueOf(eventoDTO.getPrecio()));
			//Se hace el pago o se intenta
			try {
				Payment payment = paypalService.createPayment(orderDTO.getPrice(), orderDTO.getCurrency(), orderDTO.getMethod(),
						orderDTO.getIntent(), orderDTO.getDescription(), "http://localhost:8888/" + CANCEL_URL,
						"http://localhost:8888/" + SUCCESS_URL);
				for(Links link:payment.getLinks()) {
					if(link.getRel().equals("approval_url")) {
						eventoService.pagar(eventoDTO);
						return "redirect:"+link.getHref();
					}
				}
				
			} catch (PayPalRESTException e) {
			
				e.printStackTrace();
			}
			return "redirect:/user";
			
		}else {
			//Se devuelve malas credenciales y un error 403
			return "";
		}
		
		
	}
	
	//Cambiar por ModelAndView
	 @GetMapping(value = CANCEL_URL)
	    public ModelAndView cancelPay(@CookieValue(value = "idEvento") String idEvento, @CookieValue(value = "idUsuario") String idUsuario) {
	     UsuarioDTO usuarioDTO = new UsuarioDTO();
	     EventoDTO eventoDTO = new EventoDTO();
	     usuarioDTO.setId(Long.valueOf(idUsuario));
	     eventoDTO.setId(Long.valueOf(idEvento));
		 ModelAndView mav = new ModelAndView("paypal/cancel");
		 mav.addObject("usuarioDTO", usuarioDTO);
		 mav.addObject("eventoDTO", eventoDTO);
		 return mav;
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request) {
	    	ModelAndView mav = new ModelAndView("app/user");
	    	try {
	            Payment payment = paypalService.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	            	//Cambiamos la pantalla de visualización
	            	mav.setViewName("paypal/succes");
	                return mav;
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return mav;
	    }
	
}
