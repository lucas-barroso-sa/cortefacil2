package cortefacil.unifor.models.DTOs;

import cortefacil.unifor.models.entities.ServiceItem;

public class ServiceItemDTO {

    private Long serviceId;
    private String serviceName;
    private Double price;

    public ServiceItemDTO() {}

    public ServiceItemDTO(ServiceItem entity) {
        if (entity.getService() != null) {
            this.serviceId = entity.getService().getId();
            this.serviceName = entity.getService().getName();
        }
        this.price = entity.getPrice();
    }

    // Getters e Setters
    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}