package teralco.sedeelectronica.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.model.Medio;
import teralco.sedeelectronica.service.LicitacionService;

@Controller
public class LicitacionController {

	private LicitacionService licitacionService;
	
	@Autowired
	public LicitacionController(LicitacionService licitacionService) {
		this.licitacionService=licitacionService;
	}
	
	@RequestMapping(value="/licitaciones",produces = "text/html;charset=UTF-8" )
	public String licitaciones(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("licitaciones", licitacionService.list());
		return "licitaciones/licitaciones";
	}
	
	@RequestMapping("/licitaciones/create")
	public String create(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("licitacion", new Licitacion());
		model.addAttribute("medios", Medio.values());
		return "licitaciones/formLicitacion";
	}
	
	@RequestMapping("/licitaciones/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("licitacion", licitacionService.get(id));
		model.addAttribute("medios", Medio.values());
    	return "licitaciones/formLicitacion";
	}
	
	@RequestMapping("/licitaciones/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		licitacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La licitacion "+ id +" ha sido borrada.");
		return "redirect:/licitaciones";
	}
	
	@PostMapping(value="/licitaciones/save")
    public String save(@Valid @ModelAttribute("licitacion") Licitacion lici,
    		BindingResult bindingResult, Model model, RedirectAttributes redir) {

        if( bindingResult.hasErrors() ) {
    		model.addAttribute("medios", Medio.values());
        	return "licitaciones/formLicitacion";
		} else {	
			
			licitacionService.save(lici);
			
        	return "redirect:/licitaciones";
		}
    }
	
}
