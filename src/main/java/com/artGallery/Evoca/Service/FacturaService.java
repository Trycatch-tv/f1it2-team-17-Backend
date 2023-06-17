package com.artGallery.Evoca.Service;

import com.artGallery.Evoca.Model.FacturaModel;
import com.artGallery.Evoca.Repository.IFacturaRepository;
import com.artGallery.Evoca.Repository.IuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FacturaService {

    private final IFacturaRepository IFacturaRepor;
    private final IuserRepository iuserRepository;

    @Autowired
    public FacturaService(IFacturaRepository IFacturaRepor, IuserRepository iuserRepository) {
        this.IFacturaRepor = IFacturaRepor;
        this.iuserRepository = iuserRepository;
    }

    public FacturaModel insertFactura(FacturaModel factura) {
        final FacturaModel result = IFacturaRepor.saveAndFlush(factura);
        result.setUsuario(iuserRepository.getReferenceById(factura.getUsuario().getIdUsuario()));
        return result;
    }

    public List<FacturaModel> ListarFactura() {
        return IFacturaRepor.findAll();

    }

    public FacturaModel findById(Long id) {
        return IFacturaRepor.findById(id).orElse(null);
    }


    public void delete(FacturaModel factura) {
        IFacturaRepor.delete(factura);

    }
}
