/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.MunicipioDAO;
import dao.ProvinciaDAO;
import java.io.Serializable;
import modelo.Municipio;
import modelo.Provincia;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author desenvolvimento
 */
@ManagedBean(name = "municipioBean")
@SessionScoped
public class MunicipioBean implements Serializable {

    private ProvinciaDAO provinciaDAO;
    private MunicipioDAO municipioDAO;

    private Municipio municipio;
    private Provincia provincia;
    private List<Municipio> municipios;
    private List<Provincia> provincias;

    /**
     * Creates a new instance of MunicipioBean
     */
    public MunicipioBean() {
       
    }

    @PostConstruct
    public void init() {
     
        municipio = new Municipio();
        provincia = new Provincia();
        municipios = new ArrayList<>();
        provincias = new ArrayList<>();
        municipioDAO = new MunicipioDAO();
        provinciaDAO = new ProvinciaDAO();
           provincias = provinciaDAO.findAll();
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    /**
     * Carregar a lista de provincias
     *
     * @return
     */
    public List<Provincia> getProvincias() {

        return provincias;
    }

    public void loadMunicipios() {
   
       municipios = municipioDAO.findByIdProvincia2(provincia);
}
    
    public void processValueChange(ValueChangeEvent e) {

        System.out.println("Valor obtido:\t" + e.getNewValue());

    }

    public void carregaMunicipiosDaProvincia(ValueChangeEvent event) {
         Provincia p = (Provincia)event.getNewValue();
         Integer id = p.getIdProvincia();
        System.out.print("Sigla>>>>>>" + event.getNewValue().toString());
        municipios = municipioDAO.findByIdProvincia(id);

    }

    /**
     *
     * @param event - carrega os municipios da proncia seleccionada
     */
    public void listaMunicipiosDaProvincia(AjaxBehaviorEvent event) {

        String dueDate = (String) ((UIOutput) event.getSource()).getValue();

        System.out.println("Provincia <<<<<=====" + dueDate);
        //  municipios = municipioDAO.findByIdProvincia(provincia);
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

}
