package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.ItemSaleDTO;
import cortefacil.unifor.models.DTOs.SaleDTO;
import cortefacil.unifor.models.entities.Item;
import cortefacil.unifor.models.entities.ItemSale;
import cortefacil.unifor.models.entities.Sale;
import cortefacil.unifor.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private SaleRepository saleRepository;

    @Autowired
    private ItemService itemService;
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleDTO insert(SaleDTO dto){
        Sale sale = new Sale();

        for(ItemSaleDTO itemSaleDTO : dto.getItemSaleDTOList()){
            Item item = itemService.findEntityById(itemSaleDTO.getId());
            if(item.getQuantity() < itemSaleDTO.getQuantity()){
                throw new IllegalArgumentException("Estoque insuficiente para o item: " + item.getName());
            }
            ItemSale itemSale = new ItemSale(itemSaleDTO.getQuantity(),sale,item);
            sale.getItemSaleList().add(itemSale);
            item.setQuantity(item.getQuantity() - itemSaleDTO.getQuantity());

            itemService.updateStockQuantity(item);

        }
        sale.calculateTotal();
        saleRepository.save(sale);
        return new SaleDTO(sale);

    }

    public List<SaleDTO> findAll(){
        List<Sale> sales = saleRepository.findAll();
        List<SaleDTO> saleDTOList = sales.stream().map(obj-> new SaleDTO(obj)).toList();
        return saleDTOList;
    }
}
