package teralco.sedeelectronica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.service.AdjudicacionService;
import teralco.sedeelectronica.service.AperturaService;
import teralco.sedeelectronica.service.AvisoService;
import teralco.sedeelectronica.service.DocumentacionService;
import teralco.sedeelectronica.service.LicitacionService;
import teralco.sedeelectronica.service.ModeloService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class PerfilContratanteController {

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	private LicitacionService licitacionService;

	@Autowired
	private AperturaService aperturaService;

	@Autowired
	private AdjudicacionService adjudicacionService;

	@Autowired
	private DocumentacionService documentacionService;

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private AvisoService avisoService;

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		return "contratante/perfil-del-contratante";
	}

	@RequestMapping("/licitaciones")
	public String licitaciones(Model model, @PageableDefault(value = 10) Pageable pageable) {

		Page<Licitacion> pages = this.licitacionService.listAllByPage(pageable);
		model.addAttribute("licitaciones", pages);
		PageWrapper<Licitacion> page = new PageWrapper<>(pages, "/licitaciones");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return "contratante/licitaciones";
	}

	@RequestMapping("/aperturas")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Apertura> pages = this.aperturaService.listAllByPage(pageable);
		model.addAttribute("aperturas", pages);

		PageWrapper<Apertura> page = new PageWrapper<>(pages, "/aperturas");

		model.addAttribute("page", page);
		model.addAttribute("encrypt", this.encryptUtils);

		return "contratante/aperturas";
	}

	@RequestMapping("/adjudicaciones")
	public String adjudicaciones(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Adjudicacion> pages = this.adjudicacionService.listAllByPage(pageable);
		model.addAttribute("adjudicaciones", pages);

		PageWrapper<Adjudicacion> page = new PageWrapper<>(pages, "/adjudicaciones");

		model.addAttribute("page", page);
		model.addAttribute("encrypt", this.encryptUtils);

		return "contratante/adjudicaciones";
	}

	@RequestMapping("/documentos")
	public String documentos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Documentacion> pages = this.documentacionService.listAllByPage(pageable);
		model.addAttribute("documentos", pages);

		PageWrapper<Documentacion> page = new PageWrapper<>(pages, "/documentos");

		model.addAttribute("page", page);
		model.addAttribute("encrypt", this.encryptUtils);

		return "contratante/documentos";
	}

	@RequestMapping("/modelos")
	public String modelos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Modelo> pages = this.modeloService.listAllByPage(pageable);
		model.addAttribute("modelos", pages);

		PageWrapper<Modelo> page = new PageWrapper<>(pages, "/modelos");

		model.addAttribute("page", page);
		model.addAttribute("encrypt", this.encryptUtils);

		return "contratante/modelos";
	}

	@RequestMapping("/avisos")
	public String avisos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Aviso> pages = this.avisoService.listAllByPage(pageable);
		model.addAttribute("avisos", pages);

		PageWrapper<Aviso> page = new PageWrapper<>(pages, "/avisos");

		model.addAttribute("page", page);
		model.addAttribute("encrypt", this.encryptUtils);
		return "contratante/avisos";
	}

}
